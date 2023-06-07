package com.petmily.backend.admin.board.volunteer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petmily.backend.support.volunteer.domain.Volunteer;



@Repository
public interface AdminVolunteerRepository extends JpaRepository<Volunteer, Long> {
	
	// 게시글검색
	@Query(value="SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
			+ "r.volunteerSubject as subject, r.volunteerContent as content, "
			+ "r.imgThumbnail as thumbnail, r.volunteerCount as count, r.volunteerDate as postDate "
			+ "from volunteer r join member m on r.memberNum = m.memberNum", nativeQuery=true)
	Page<AdminVolunteer> findAllAdminVolunteer(Pageable pageable);
	
	Page<AdminVolunteer> findByVolunteerSubjectContaining(@Param("keyword")String keyword, Pageable pageable);
	@Query(value = "SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
			+ "r.volunteerSubject as subject, r.volunteerContent as content, "
			+ "r.imgThumbnail as thumbnail, r.volunteerCount as count, r.volunteerDate as postDate "
			+ "from volunteer r join member m on r.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			countQuery = "SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
					+ "r.volunteerSubject as subject, r.volunteerContent as content, "
					+ "r.imgThumbnail as thumbnail, r.volunteerCount as count, r.volunteerDate as postDate "
					+ "from volunteer r join member m on r.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(Content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			nativeQuery = true)
	Page<AdminVolunteer> findByVolunteerContentContaining(@Param("keyword")String keyword, Pageable pageable);	
	@Query(value = "SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
			+ "r.volunteerSubject as subject, r.volunteerContent as content, "
			+ "r.imgThumbnail as thumbnail, r.volunteerCount as count, r.volunteerDate as postDate "
			+ "from volunteer r join member m on r.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
			countQuery = "SELECT r.boardNum, r.memberNum, m.memberId, r.boardId, "
					+ "r.volunteerSubject as subject, r.volunteerContent as content, "
					+ "r.imgThumbnail as thumbnail, r.volunteerCount as count, r.volunteerDate as postDate "
					+ "from volunteer r join member m on r.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
			nativeQuery = true)
	Page<AdminVolunteer>findByVolunteerSubjectContainingOrVolunteerContentContaining(@Param("keyword")String keyword, Pageable pageable);

}
