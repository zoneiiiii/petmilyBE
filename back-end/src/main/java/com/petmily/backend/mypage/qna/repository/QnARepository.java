package com.petmily.backend.mypage.qna.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petmily.backend.adopt.adoptInfo.Adopt;
import com.petmily.backend.mypage.qna.domain.QnABoard;

public interface QnARepository extends JpaRepository<QnABoard, Long> {
	@NotNull
    Page<QnABoard> findAll(@NotNull Pageable pageable);
	
    @Query(
    		nativeQuery=true,
    		value="SELECT q.boardNum, q.boardId, q.qnaSubject,q.qnaContent, q.qnaStatus, q.qnaImg, q.qnaDate, q.adminAnswer, m.memberNum " +
            "FROM qnaboard q " +
            "JOIN member m ON q.memberNum = m.memberNum " +
            "WHERE m.memberNum = :memberNum")
    Page<QnABoard> findQnAByMemberNum(@Param("memberNum") Long memberNum,Pageable pageable);
    
    QnABoard findByBoardNum(Long boardNum);

	
    @Modifying(clearAutomatically = true)
    @Query("UPDATE QnABoard q SET q.qnaStatus = :qnaStatus, q.adminAnswer = :adminAnswer WHERE q.boardNum = :boardNum")
    void updateQna(Long boardNum,Boolean qnaStatus, String adminAnswer);
}
