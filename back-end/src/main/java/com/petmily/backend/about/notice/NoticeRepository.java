package com.petmily.backend.about.notice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petmily.backend.about.domain.Notice;
import com.petmily.backend.about.dto.NoticeForm;
import com.petmily.backend.about.dto.NoticeView;

import jakarta.transaction.Transactional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
	Page<Notice> findBySubjectContaining(@Param("keyword")String keyword, Pageable pageable);
	@Query(value = "SELECT * FROM Notice WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			countQuery = "SELECT * FROM Notice WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			nativeQuery = true)
	Page<Notice> findByContentContaining(@Param("keyword")String keyword, Pageable pageable);	
	@Query(value = "SELECT * FROM Notice WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR SUBJECT REGEXP :keyword", 
			countQuery = "SELECT * FROM Notice WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR SUBJECT REGEXP :keyword", 
			nativeQuery = true)
	Page<Notice> findBySubjectContainingOrContentContaining(@Param("keyword")String keyword, Pageable pageable);
	Page<Notice> findAll(Pageable pageable);
	Optional<Notice> findByBoardNum(Long BoardNum );
	
	@Modifying
	@Query(value="update Notice n set n.subject = :#{#noticeForm.subject}, n.content = :#{#noticeForm.content} where n.boardNum = :#{#noticeForm.no}", nativeQuery = true) 
	int updateNotice(@Param("noticeForm")NoticeForm noticeForm);
	
	@Modifying
	@Query(value="update Notice n set n.count = n.count + 1 where n.boardNum = :boardNum", nativeQuery = true) 
	int updateViews(@Param("boardNum") Long boardNum);
	
	@Query(value = "SELECT "
			+ "n.boardNum as no, m.memberImg as imgSrc, m.memberNickname as nickname, n.subject, "
			+ "n.content, n.count, n.postDate, n.prevNo, n.prevSub, n.nextNo, n.nextSub FROM "
			+ "( SELECT *, "
			+ "		LAG(boardNum, 1, 0) OVER(ORDER BY boardNum ASC) AS prevNo, "
			+ "    	LAG(subject, 1, '이전글이 없습니다') OVER (ORDER BY boardNum) AS prevSub, "
			+ "    	LEAD(boardNum, 1, 0) OVER(ORDER BY boardNum ASC) AS nextNO, "
			+ "		LEAD(subject, 1, '다음글이 없습니다') OVER (ORDER BY boardNum) AS nextsub "
			+ "FROM notice "
			+ ") n, Member m where m.memberNum=n.memberNum and boardNum=:boardNum "
			+ "ORDER BY boardNum;", nativeQuery = true)
	Optional<NoticeView> getNoticeView(@Param("boardNum") Long num);
	
	long count();
}