package com.petmily.backend.shop.service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.shop.domain.Orderlist;
import com.petmily.backend.shop.domain.Orders;
import com.petmily.backend.shop.dto.OrderlistDto;
import com.petmily.backend.shop.dto.OrdersDto;
import com.petmily.backend.shop.dto.ProductDetail;
import com.petmily.backend.shop.repository.CartRepository;
import com.petmily.backend.shop.repository.OrderlistRepository;
import com.petmily.backend.shop.repository.OrdersRepository;
import com.petmily.backend.support.donate.domain.Payment;
import com.petmily.backend.support.donate.dto.PaymentDto;
import com.petmily.backend.support.donate.repository.PaymentRepository;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final IamportClient iamportClient;
    private final OrdersRepository ordersRepository;
    private final OrderlistRepository orderlistRepository;
    private final PaymentRepository paymentRepository;
    private final MemberService memberService;
    private final ProductService productService;
    private final CartRepository cartRepository;

    public OrderService(IamportClient iamportClient, OrdersRepository ordersRepository, PaymentRepository paymentRepository, OrderlistRepository orderlistRepository, MemberService memberService, ProductService productService, CartRepository cartRepository){
        this.iamportClient = iamportClient;
        this.orderlistRepository = orderlistRepository;
        this.ordersRepository = ordersRepository;
        this.paymentRepository = paymentRepository;
        this.memberService = memberService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    @Transactional // 주문 저장
    public OrdersDto saveOrder(OrdersDto ordersDto, PaymentDto paymentDto, List<OrderlistDto> orderlistDtos, List<Long> cartNums,String loggedInUserId){

        Orders orders = new Orders(
                memberService.getMember(loggedInUserId),
                ordersDto.getOrderDate(),
                ordersDto.getOrderState(),
                ordersDto.getAddress(),
                ordersDto.getAddressDetail(),
                ordersDto.getPostal(),
                ordersDto.getNote(),
                ordersDto.getRecipient(),
                ordersDto.getRecipientTel(),
                ordersDto.getTotalCost()
        );

        Orders savedOrders = ordersRepository.save(orders);
        //결제 저장
        Payment payment = new Payment(
                savedOrders.getOrderNum(),
                paymentDto.getMerchantUid(),
                paymentDto.getImpUid(),
                paymentDto.getPaymentState(),
                paymentDto.getAmount(),
                paymentDto.getPaymentDate()

        );
        // 로그인 된 사용자 Num 가져오기
        Long loggedInUserNum = null;
        if(loggedInUserId != null) {
            Member member = memberService.getMember(loggedInUserId);
            loggedInUserNum = member.getMemberNum();
        }
        // 아임포트 결제 검증
        IamportResponse<com.siot.IamportRestClient.response.Payment> paymentResponse;
        try {
            paymentResponse = iamportClient.paymentByImpUid(paymentDto.getImpUid());

            if (paymentResponse == null
                    || !paymentResponse.getResponse().getAmount().equals(paymentDto.getAmount())) {
                // import Uid와 Client Uid 비교 아임포트쪽 금액과 클라이언트 입력 금액 다를때, ImpUid가 Null일때 결제 검증 실패
                throw new RuntimeException("결제 검증 실패!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("결제 검증 도중 오류 발생!");
        }

        Payment savedPayment = paymentRepository.save(payment);

        //주문 리스트 정보 저장
        for(OrderlistDto orderlistDto : orderlistDtos) {
            ProductDetail productDetail = productService.getProduct(orderlistDto.getBoardNum());
            Orderlist orderlist = new Orderlist(
                    savedOrders,
                    productService.getProducts(orderlistDto.getBoardNum()),
                    orderlistDto.getQuantity(),
                    orderlistDto.getCost()
            );
            orderlistRepository.save(orderlist);
        }

        Member member = memberService.getMember(loggedInUserId);
        cartRepository.deleteByMemberAndCartNumIn(member, cartNums);

        ordersDto.setOrderNum(savedOrders.getOrderNum());
        return ordersDto;
    }

    public List<OrdersDto> getAllOrders() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<OrdersDto> getAllOrdersASC() {
        List<Orders> orders = ordersRepository.findAll(Sort.by(Sort.Direction.ASC, "orderDate"));
        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<OrdersDto> getAllOrdersDESC() {
        List<Orders> orders = ordersRepository.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));
        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Page<OrdersDto> getOrders(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("orderDate").descending());
        Page<Orders> orders = ordersRepository.findAll(pageable);
        return orders.map(this::convertToDto);
    }

    public long getTotalOrders(){
        return ordersRepository.count();
    }

    public List<OrdersDto> getAllOrders(String loggedInUserId) {
        Member member = memberService.getMember(loggedInUserId);
        List<Orders> orders = ordersRepository.findByMember(member);
        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<OrderlistDto> getAllOrderlist(){
        List<Orderlist> orderlists = orderlistRepository.findAll();
        return orderlists.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<OrderlistDto> getOrderlistByOrderNum(Long orderNum) {
        Orders orders = ordersRepository.findById(orderNum).orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. orderNum: " + orderNum));
        List<Orderlist> orderlists = orderlistRepository.findByOrders(orders);

        return orderlists.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void updateOrderState(Long orderNum, String orderState) {
        Orders order = ordersRepository.findById(orderNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. orderNum: " + orderNum));
        order.setOrderState(orderState);
    }

    public double calculateTotalCost(){
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
                .mapToDouble(Orders::getTotalCost)
                .sum();
    }

    private OrdersDto convertToDto(Orders orders) {
        OrdersDto dto = new OrdersDto();
        dto.setOrderNum(orders.getOrderNum());
        dto.setMemberNum(orders.getMember().getMemberNum());
        dto.setMemberId(orders.getMember().getMemberId());
        dto.setOrderDate(orders.getOrderDate());
        dto.setOrderState(orders.getOrderState());
        dto.setAddress(orders.getAddress());
        dto.setAddressDetail(orders.getAddressDetail());
        dto.setPostal(orders.getPostal());
        dto.setNote(orders.getNote());
        dto.setRecipient(orders.getRecipient());
        dto.setRecipientTel(orders.getRecipientTel());
        dto.setTotalCost(orders.getTotalCost());
        dto.setOrderlists(getOrderlistByOrderNum(orders.getOrderNum()));
        return dto;
    }

    private OrderlistDto convertToDto(Orderlist orderlist) {
        OrderlistDto dto = new OrderlistDto();
        dto.setOrderlistNum(orderlist.getOrderlistNum());
        dto.setOrderNum(orderlist.getOrders().getOrderNum());
        dto.setBoardNum(orderlist.getProduct().getBoardNum());
        dto.setQuantity(orderlist.getQuantity());
        dto.setProductName(orderlist.getProduct().getProductName());
        dto.setCost(orderlist.getCost());
        return dto;
    }

}
