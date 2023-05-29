package com.petmily.backend.about.notice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petmily.backend.about.domain.Notice;
import com.petmily.backend.about.dto.NoticeView;

import jakarta.transaction.Transactional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
	Page<Notice> findBySubjectContaining(String subject, Pageable pageable);
	Page<Notice> findByContentContaining(String content, Pageable pageable);
	Page<Notice> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query(value="update Notice n set n.count = n.count + 1 where n.boardNum = :boardNum", nativeQuery = true) 
	int updateViews(@Param("boardNum") Long boardNum);
	
	@Query(value = "SELECT "
			+ "n.boardNum as no, m.memberImg as img, m.memberNickname as nickname, n.subject, "
			+ "n.content, n.count, n.postDate, n.prevNo, n.prevSub, n.nextNo, n.nextSub FROM "
			+ "( SELECT *, "
			+ "		LAG(boardNum, 1, 0) OVER(ORDER BY boardNum ASC) AS prevNo, "
			+ "    	LAG(subject, 1, '이전글이 없습니다') OVER (ORDER BY boardNum) AS prevSub, "
			+ "    	LEAD(boardNum, 1, 0) OVER(ORDER BY boardNum ASC) AS nextNO, "
			+ "		LEAD(subject, 1, '다음글이 없습니다') OVER (ORDER BY boardNum) AS nextsub "
			+ "FROM notice "
			+ ") n, Member m where boardNum=:boardNum "
			+ "ORDER BY boardNum;", nativeQuery = true)
	Optional<NoticeView> getNoticeView(@Param("boardNum") Long num);
}