package com.petmily.backend.shop.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.petmily.backend.shop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.shop.domain.Product;
import com.petmily.backend.shop.dto.ProductAddCart;
import com.petmily.backend.shop.dto.ProductDetail;
import com.petmily.backend.shop.dto.ProductDto;
import com.petmily.backend.shop.dto.ProductList;
import com.petmily.backend.shop.repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
    private MemberService memberService;

	@Transactional
	public List<ProductList> getProductList() {
		return productRepository.getProducts();
	}

	@Transactional
	public ProductDetail getProduct(Long boardNum) {
		ProductDetail product = productRepository.findProductDetail(boardNum);
		return product;
	}

	@Transactional // utm 추가-230603
	public Product getProducts(Long boardNum) {
		return productRepository.findProductByBoardNum(boardNum);
	}

	public void addCart(ProductAddCart addCart) {
		long productCheck = productRepository.productCheck(addCart.getMemberId(), addCart.getBoardNum());

		if (productCheck > 0) {
			System.out.println("if");
			productRepository.updateQuantity(addCart.getQuantity(), addCart.getMemberId(), addCart.getBoardNum());
		} else {
			System.out.println("else");
			productRepository.addCart(addCart.getBoardNum(), addCart.getMemberId(), addCart.getProductName(),
					addCart.getProductCost(), addCart.getQuantity());
		}
	}
	
	public Page<Product> getProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }
	
    private ProductDto convertToDto(Product product) {
    	ProductDto prodDto = new ProductDto();
    	prodDto.setBoardNum(product.getBoardNum());
    	prodDto.setBoardId(product.getBoardId());
    	prodDto.setProductName(product.getProductName());
    	prodDto.setProductCost(product.getProductCost());
    	prodDto.setProductContent(product.getProductContent());
    	prodDto.setProductImg(product.getProductImg());
    	prodDto.setProductAmount(product.getProductAmount());
    	prodDto.setImgThumbnail(product.getImgThumbnail());
    	prodDto.setMemberNum(product.getMemberNum());
    	prodDto.setProductCategory(product.getProductCategory());
        return prodDto;
    }

    public ProductDto createProduct(ProductDto prodDto, String memberId){
        Product product = new Product();
        Member member = memberService.getMember(memberId); 
        product.setBoardId("shop");
        product.setProductName(prodDto.getProductName());
        product.setProductCost(prodDto.getProductCost());
        product.setProductContent(prodDto.getProductContent());
        product.setProductImg(prodDto.getProductImg());
        product.setProductAmount(prodDto.getProductAmount());
        product.setImgThumbnail(prodDto.getImgThumbnail());
        product.setMemberNum(member.getMemberNum());
        product.setProductCategory(prodDto.getProductCategory());
        productRepository.save(product);

        return convertToDto(product);
    }
    
    public ProductDto updateProduct (Long boardNum, ProductDto prodDto, String loggedInUserId){
    	Member member = memberService.getMember(loggedInUserId);
        Product product = productRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
        if(!member.getMemberNum().equals(product.getMemberNum())){
            throw new AccessDeniedException("해당 게시글을 수정할 권한이 없습니다.");
        }
        product.setBoardId("shop");
        product.setProductName(prodDto.getProductName());
        product.setProductCost(prodDto.getProductCost());
        product.setProductContent(prodDto.getProductContent());
        product.setProductImg(prodDto.getProductImg());
        product.setProductAmount(prodDto.getProductAmount());
        product.setImgThumbnail(prodDto.getImgThumbnail());
        product.setMemberNum(member.getMemberNum());
        product.setProductCategory(prodDto.getProductCategory());

        productRepository.save(product);
        return convertToDto(product);
    }
    
    public void deleteProduct(Long boardNum){
    	Product product = productRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));

    	productRepository.delete(product);
    }
	
}
