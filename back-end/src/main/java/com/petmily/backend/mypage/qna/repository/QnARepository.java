package com.petmily.backend.mypage.qna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petmily.backend.mypage.qna.domain.QnABoard;

public interface QnARepository extends JpaRepository<QnABoard, Long> {
	
    @Query(
    		nativeQuery=true,
    		value="SELECT q.boardNum, q.boardId, q.qnaSubject,q.qnaContent, q.qnaStatus, q.qnaImg, q.qnaDate, m.memberNum " +
            "FROM qnaboard q " +
            "JOIN member m ON q.memberNum = m.memberNum " +
            "WHERE m.memberNum = :memberNum ORDER BY q.qnaDate DESC")
    List<Object[]> findQnAByMemberNum(@Param("memberNum") Long memberNum);
}
