package com.petmily.backend.support.volunteerReview.repository;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;

@Repository
public interface VolunteerReviewRepository extends JpaRepository<VolunteerReview, Long> {
	 @NotNull
	    Page<VolunteerReview> findAll(@NotNull Pageable pageable);
	 
	//마이페이지 쓴 글 목록(실종동물게시판)
    @Query(
    		nativeQuery=true,
    		value="SELECT r.boardNum, r.boardId, r.reviewSubject, r.reviewContent, r.reviewCount, r.reviewDate, r.imgThumbnail, m.memberNum " +
            "FROM volunteerreview r " +
            "JOIN member m ON r.memberNum = m.memberNum " +
            "WHERE m.memberNum = :memberNum")
    List<Object[]> findReviewByMemberNum(@Param("memberNum") Long memberNum);

}
