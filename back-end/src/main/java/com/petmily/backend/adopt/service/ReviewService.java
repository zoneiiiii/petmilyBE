package com.petmily.backend.adopt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.petmily.backend.adopt.domain.ReviewBoard;
import com.petmily.backend.adopt.dto.ReviewBoardList;
import com.petmily.backend.adopt.dto.ReviewDto;
import com.petmily.backend.adopt.repository.ReviewRepository;
import com.petmily.backend.community.free.board.FreeBoard;
import com.petmily.backend.community.free.board.FreeBoardDto;
import com.petmily.backend.community.missing.board.MissingBoard;
import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.repository.MemberRepository;
import com.petmily.backend.member.login.service.MemberService;

import jakarta.transaction.Transactional;


@Service
public class ReviewService {
	@Autowired
	private final ReviewRepository repository;
	private final MemberRepository memberRepository;
	private final MemberService memberService;

	public ReviewService(ReviewRepository repository, MemberRepository memberRepository, MemberService memberService){
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
     
    }
//	@Modifying
//	@Transactional
//	public void writeReview(ReviewBoard review, Member member) {
//		
//	    repository.save(review);
//	}
	public ReviewDto writeReview(ReviewDto reviewDto, String memberId){
        Member member = memberService.getMember(memberId);
        ReviewBoard reviewBoard = new ReviewBoard();

        reviewBoard.setMemberNum(member.getMemberNum());
        reviewBoard.setBoardId("review");
        reviewBoard.setReviewSubject(reviewDto.getReviewSubject());
        reviewBoard.setReviewContent(reviewDto.getReviewContent());
        reviewBoard.setReviewCount(0);
        reviewBoard.setReviewDate(reviewDto.getReviewDate());
        reviewBoard.setImgThumbnail(reviewDto.getImgThumbnail());

        repository.save(reviewBoard);

        return convertToDto(reviewBoard);
    }
 

	//게시글 리스트 처리
	@Transactional
    public List<ReviewBoardList> reviewList(){
    	
        return repository.getReviewBoards();
    }
    
    @Transactional
    public void deleteAllByBoardNum(Long boardNum){
    	repository.deleteAllByBoardNum(boardNum);
    }
    
    @Transactional 
    public ReviewBoard getReviewBoard(Long boardNum) {
    	ReviewBoard getReviewDetail = repository.findByBoardNum(boardNum);
    	repository.updateBoardCount(boardNum);
        return getReviewDetail;
    }
   
    @Transactional
    public void updateReview(Long boardNum, ReviewBoard review) {
        ReviewBoard findReview = repository.findByBoardNum(boardNum);
        findReview.setReviewSubject(review.getReviewSubject());
        findReview.setReviewContent(review.getReviewContent());
        findReview.setImgThumbnail(review.getImgThumbnail());
    }
    
	private ReviewDto convertToDto(ReviewBoard reviewboard){
		 
		ReviewDto reviewdDto = new ReviewDto();
		reviewdDto.setBoardNum(reviewboard.getBoardNum());
		reviewdDto.setMemberNum(reviewboard.getMemberNum());
		reviewdDto.setBoardId(reviewboard.getBoardId());
		reviewdDto.setReviewSubject(reviewboard.getReviewSubject());
		reviewdDto.setReviewContent(reviewboard.getReviewContent());
		reviewdDto.setReviewCount(reviewboard.getReviewCount());
		reviewdDto.setReviewDate(reviewboard.getReviewDate());
		reviewdDto.setImgThumbnail(reviewboard.getImgThumbnail());
 
         return reviewdDto;
     }

}
