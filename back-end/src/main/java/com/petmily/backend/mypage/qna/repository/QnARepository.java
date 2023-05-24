package com.petmily.backend.mypage.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petmily.backend.mypage.qna.domain.QnABoard;

public interface QnARepository extends JpaRepository<QnABoard, Long> {

}
