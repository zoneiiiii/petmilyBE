package com.petmily.backend.adopt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.petmily.backend.adopt.domain.ReviewBoard;
import com.petmily.backend.adopt.repository.ReviewRepository;
import com.petmily.backend.community.missing.board.MissingBoard;

import jakarta.transaction.Transactional;


@Service
public class ReviewService {
	@Autowired
	private final ReviewRepository repository;

	public ReviewService(ReviewRepository repository){
        this.repository = repository;
     
    }
	@Modifying
	@Transactional
	public void writeReview(ReviewBoard review) {
	    repository.save(review);
	}

	//게시글 리스트 처리
	@Transactional
    public List<ReviewBoard> reviewList(){
    	
        return repository.findAllByOrderByBoardNumDesc();
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

}
