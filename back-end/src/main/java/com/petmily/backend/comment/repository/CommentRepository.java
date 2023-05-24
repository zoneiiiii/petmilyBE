package com.petmily.backend.comment.repository;

import com.petmily.backend.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBoardIdAndBoardNumOrderByCommentNumAsc(String boardId, Long boardNum);
}
