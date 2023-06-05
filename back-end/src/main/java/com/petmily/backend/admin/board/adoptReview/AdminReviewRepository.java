package com.petmily.backend.admin.board.adoptReview;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petmily.backend.adopt.adoptReview.ReviewBoard;


@Repository
public interface AdminReviewRepository extends JpaRepository<ReviewBoard, Long> {
	
	// 게시글검색
	@Query(value="SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
			+ "r.reviewSubject as subject, r.reviewContent as content, "
			+ "r.imgThumbnail as thumbnail, r.reviewCount as count, r.reviewDate as postDate "
			+ "from reviewBoard r join member m on r.memberNum = m.memberNum", nativeQuery=true)
	Page<AdminAdoptReview> findAllAdminAdoptReview(Pageable pageable);
	
	Page<AdminAdoptReview> findByReviewSubjectContaining(@Param("keyword")String keyword, Pageable pageable);
	@Query(value = "SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
			+ "r.reviewSubject as subject, r.reviewContent as content, "
			+ "r.imgThumbnail as thumbnail, r.reviewCount as count, r.reviewDate as postDate "
			+ "from reviewBoard r join member m on r.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			countQuery = "SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
					+ "r.reviewSubject as subject, r.reviewContent as content, "
					+ "r.imgThumbnail as thumbnail, r.reviewCount as count, r.reviewDate as postDate "
					+ "from reviewBoard r join member m on r.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(reviewContent, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			nativeQuery = true)
	Page<AdminAdoptReview> findByReviewContentContaining(@Param("keyword")String keyword, Pageable pageable);	
	@Query(value = "SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
			+ "r.reviewSubject as subject, r.reviewContent as content, "
			+ "r.imgThumbnail as thumbnail, r.reviewCount as count, r.reviewDate as postDate "
			+ "from reviewBoard r join member m on r.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
			countQuery = "SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
					+ "r.reviewSubject as subject, r.reviewContent as content, "
					+ "r.imgThumbnail as thumbnail, r.reviewCount as count, r.reviewDate as postDate "
					+ "from reviewBoard r join member m on r.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
			nativeQuery = true)
	Page<AdminAdoptReview>findByReviewSubjectContainingOrReviewContentContaining(@Param("keyword")String keyword, Pageable pageable);

}
