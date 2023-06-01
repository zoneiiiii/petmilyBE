package com.petmily.backend.adopt.adoptReview;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<ReviewBoard, Long> {
	List<ReviewBoard> findAllByOrderByBoardNumDesc();


	void deleteAllByBoardNum(Long boardNum);
	
	ReviewBoard findByBoardNum(Long boardNum);

	@Query(
			nativeQuery=true,
			value="SELECT r.boardNum, r.reviewSubject, r.reviewCount, r.imgThumbnail, r.reviewDate, m.memberNickName "
					+ "FROM reviewboard r "
					+ "JOIN member m ON r.memberNum = m.memberNum ORDER BY r.reviewDate DESC")
	List<ReviewBoardList> getReviewBoards();
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE ReviewBoard r SET r.reviewSubject = :reviewSubject, r.reviewContent = :reviewContent, r.imgThumbnail = :imgThumbnail WHERE r.boardNum = :boardNum")
	void updateReview(Long boardNum, String reviewSubject, String reviewContent, String imgThumbnail);
	
    @Modifying
    @Query("UPDATE ReviewBoard m SET m.reviewCount = m.reviewCount + 1 WHERE m.boardNum = :boardNum")
    int updateBoardCount(@Param("boardNum") Long boardNum);
    
//    List<ReviewBoardList> findByTitleContaining(String keyword);

  //마이페이지 쓴 글 목록(입양후기)
    @Query(
    		nativeQuery=true,
    		value="SELECT r.boardNum, r.reviewSubject, r.reviewCount, r.imgThumbnail, r.reviewDate, m.memberNickName "
					+ "FROM reviewboard r " +
            "JOIN member m ON r.memberNum = m.memberNum " +
            "WHERE m.memberNum = :memberNum")
    Page<ReviewBoardList> findAdoptReviewByMemberNum(@Param("memberNum") Long memberNum, Pageable pageable);


}
