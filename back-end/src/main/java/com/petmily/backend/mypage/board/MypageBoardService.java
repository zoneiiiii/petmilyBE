package com.petmily.backend.mypage.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petmily.backend.adopt.adoptReview.ReviewBoardList;
import com.petmily.backend.adopt.adoptReview.ReviewRepository;
import com.petmily.backend.community.find.board.FindBoardList;
import com.petmily.backend.community.find.board.FindBoardRepository;
import com.petmily.backend.community.flea.board.FleaBoardList;
import com.petmily.backend.community.flea.board.FleaBoardRepository;
import com.petmily.backend.community.free.board.FreeBoardList;
import com.petmily.backend.community.free.board.FreeBoardRepository;
import com.petmily.backend.community.missing.board.MissingBoardList;
import com.petmily.backend.community.missing.board.MissingBoardRepository;
import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;
import com.petmily.backend.support.volunteerReview.dto.VolunteerReviewDto;
import com.petmily.backend.support.volunteerReview.repository.VolunteerReviewRepository;
import com.petmily.backend.support.volunteerReview.service.VolunteerReviewService;

import jakarta.transaction.Transactional;

@Service
public class MypageBoardService {
	private final MissingBoardRepository missingBoardRepository;
    private final FreeBoardRepository freeRepository;
    private final VolunteerReviewRepository reviewRepository;
    private final FindBoardRepository findRepository;
    private final FleaBoardRepository fleaRepository;
    private final ReviewRepository adoptReviewRepostiroy;
    private final MemberService memberService;

    @Autowired
    public MypageBoardService(MissingBoardRepository missingBoardRepository, FreeBoardRepository freeRepository,
                              VolunteerReviewRepository reviewRepository, FindBoardRepository findRepository,
                              FleaBoardRepository fleaRepository, ReviewRepository adoptReviewRepostiroy,
                              MemberService memberService, VolunteerReviewService reviewService) {
        this.missingBoardRepository = missingBoardRepository;
        this.freeRepository = freeRepository;
        this.reviewRepository = reviewRepository;
        this.findRepository = findRepository;
        this.fleaRepository = fleaRepository;
        this.adoptReviewRepostiroy = adoptReviewRepostiroy;
        this.memberService = memberService;
    }
    
    //실종 동물 게시판
    @Transactional
    public Page<MissingBoardList> getMissingBoardByMemberNum(int page, int limit, Long memberNum) {
    	List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("boardDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
        return missingBoardRepository.findMissingBoardByMemberNum(memberNum,pageable);
    }
    
    //목격 제보 게시판
    @Transactional
    public Page<FindBoardList> getFindBoardByMemberNum(int page, int limit, Long memberNum) {
    	List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("boardDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
        return findRepository.findFindBoardByMemberNum(memberNum,pageable);
    }
    
    //매매장터
    @Transactional
    public List<FleaBoardList> getFleaBoardByMemberNum(Long memberNum){
        return fleaRepository.findFleaBoardByMemberNum(memberNum);
    
    }
    //입양 후기
    @Transactional
    public Page<ReviewBoardList> getAdoptReviewByMemberNum(int page, int limit, Long memberNum) {
    	List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("reviewDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
        return adoptReviewRepostiroy.findAdoptReviewByMemberNum(memberNum,pageable);
    }
    
    //자유 게시판
    @Transactional
    public Page<FreeBoardList> getFreeByMemberNum(int page, int limit, Long memberNum) {
    	List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("freeDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
    	return freeRepository.findFreeBoardByMemberNum(memberNum,pageable);
    }
    
   
    //봉사 후기 게시판
//    
//    public Page<VolunteerReviewList> getReviewByMemberNum(int page, int limit, Long memberNum) {
//    	List<Sort.Order> sorts = new ArrayList<>();
//		sorts.add(Sort.Order.desc("reviewDate"));
//		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
//    	return reviewRepository.findReviewByMemberNum(memberNum,pageable);
//    }
    @Transactional
    public Page<VolunteerReviewDto> getReviewByMemberNum(int page, Long memberNum) {
        Pageable pageable = PageRequest.of(page, 6, Sort.Direction.DESC, "reviewDate");
        Page<VolunteerReview> volunteerReviews = reviewRepository.findReviewByMemberNum(memberNum,pageable);

        return volunteerReviews.map(this::convertToDto);
    }
    
    private VolunteerReviewDto convertToDto(VolunteerReview volunteerReview){
        Member member = memberService.getMemberByNum(volunteerReview.getMemberNum());
        VolunteerReviewDto volunteerReviewDto = new VolunteerReviewDto();
        volunteerReviewDto.setBoardNum(volunteerReview.getBoardNum());
        volunteerReviewDto.setMemberNum(volunteerReview.getMemberNum());
        volunteerReviewDto.setBoardId(volunteerReview.getBoardId());
        volunteerReviewDto.setReviewSubject(volunteerReview.getReviewSubject());
        volunteerReviewDto.setReviewContent(volunteerReview.getReviewContent());
        volunteerReviewDto.setReviewCount(volunteerReview.getReviewCount());
        volunteerReviewDto.setReviewDate(volunteerReview.getReviewDate());
        volunteerReviewDto.setImgThumbnail(volunteerReview.getImgThumbnail());
        volunteerReviewDto.setMemberImg(member.getMemberImg());
        volunteerReviewDto.setMemberNickname(member.getMemberNickname());

        return volunteerReviewDto;
    }
    


    

}
