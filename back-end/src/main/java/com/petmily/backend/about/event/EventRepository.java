package com.petmily.backend.about.event;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petmily.backend.about.domain.EventBoard;
import com.petmily.backend.about.dto.EventForm;
import com.petmily.backend.about.dto.EventView;

import jakarta.transaction.Transactional;

public interface EventRepository extends JpaRepository<EventBoard, Long> {
	Page<EventBoard> findBySubjectContaining(@Param("keyword")String keyword, Pageable pageable);
	@Query(value = "SELECT * FROM EventBoard WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			countQuery = "SELECT * FROM EventBoard WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			nativeQuery = true)
	Page<EventBoard> findByContentContaining(@Param("keyword")String keyword, Pageable pageable);	
	@Query(value = "SELECT * FROM EventBoard WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR SUBJECT REGEXP :keyword", 
			countQuery = "SELECT * FROM EventBoard WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR SUBJECT REGEXP :keyword", 
			nativeQuery = true)
	Page<EventBoard> findBySubjectContainingOrContentContaining(@Param("keyword")String keyword, Pageable pageable);
	Page<EventBoard> findAll(Pageable pageable);
	Optional<EventBoard> findByBoardNum(Long BoardNum );
	
//	Page<EventBoard> findBySubjectContainingBetweenStartDateAndEndDate(@Param("keyword")String keyword, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate, Pageable pageable);
//	@Query(value = "SELECT * FROM EventBoard WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
//			countQuery = "SELECT * FROM EventBoard WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
//			nativeQuery = true)
//	Page<EventBoard> findByContentContainingBetweenStartDateAndEndDate(@Param("keyword")String keyword, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate, Pageable pageable);	
//	@Query(value = "SELECT * FROM EventBoard WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR SUBJECT REGEXP :keyword", 
//			countQuery = "SELECT * FROM EventBoard WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR SUBJECT REGEXP :keyword", 
//			nativeQuery = true)
//	Page<EventBoard> findBySubjectContainingOrContentContainingBetweenStartDateAndEndDate(@Param("keyword")String keyword, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate, Pageable pageable);
//	Page<EventBoard> findAllBetweenStartDateAndEndDate(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate, Pageable pageable);
//	Optional<EventBoard> findByBoardNumBetweenStartDateAndEndDate(Long BoardNum, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate );
	
	@Transactional
	@Modifying
	@Query(value="update EventBoard e set e.subject = :#{#eventForm.subject}, e.content = :#{#eventForm.content}, e.thumbnail = :#{#eventForm.thumbnail}, e.startDate = :#{#eventForm.startDate}, e.endDate= :#{#eventForm.endDate} where e.boardNum = :#{#eventForm.no}", nativeQuery = true) 
	int updateEvent(@Param("eventForm")EventForm eventForm);
	
	@Transactional
	@Modifying
	@Query(value="update EventBoard e set e.count = e.count + 1 where e.boardNum = :boardNum", nativeQuery = true) 
	int updateViews(@Param("boardNum") Long boardNum);
	
	@Query(value = "SELECT "
			+ "e.boardNum as no, m.memberImg as img, m.memberNickname as nickname, e.subject, "
			+ "e.content, e.thumbnail, e.count, e.postDate, e.startDate, e.endDate, e.prevNo, e.prevSub, e.nextNo, e.nextSub FROM "
			+ "( SELECT *, "
			+ "		LAG(boardNum, 1, 0) OVER(ORDER BY boardNum ASC) AS prevNo, "
			+ "    	LAG(subject, 1, '이전글이 없습니다') OVER (ORDER BY boardNum) AS prevSub, "
			+ "    	LEAD(boardNum, 1, 0) OVER(ORDER BY boardNum ASC) AS nextNO, "
			+ "		LEAD(subject, 1, '다음글이 없습니다') OVER (ORDER BY boardNum) AS nextsub "
			+ "FROM EventBoard "
			+ ") e, Member m where m.memberNum=e.memberNum and boardNum=:boardNum "
			+ "ORDER BY boardNum;", nativeQuery = true)
	Optional<EventView> getEventView(@Param("boardNum") Long num);
	
	long count();
}
