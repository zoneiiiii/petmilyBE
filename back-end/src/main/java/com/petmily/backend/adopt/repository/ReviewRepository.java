package com.petmily.backend.adopt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petmily.backend.adopt.domain.ReviewBoard;
import com.petmily.backend.community.missing.board.MissingBoardDetail;

import jakarta.transaction.Transactional;


@Repository
public interface ReviewRepository extends JpaRepository<ReviewBoard, Long> {
	List<ReviewBoard> findAllByOrderByBoardNumDesc();


	void deleteAllByBoardNum(Long boardNum);
	
	ReviewBoard findByBoardNum(Long boardNum);


	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE ReviewBoard r SET r.reviewSubject = :reviewSubject, r.reviewContent = :reviewContent, r.imgThumbnail = :imgThumbnail WHERE r.boardNum = :boardNum")
	void updateReview(Long boardNum, String reviewSubject, String reviewContent, String imgThumbnail);
	
    @Modifying
    @Query("UPDATE ReviewBoard m SET m.reviewCount = m.reviewCount + 1 WHERE m.boardNum = :boardNum")
    int updateBoardCount(@Param("boardNum") Long boardNum);	

}
