package com.petmily.backend.comment.controller;

import com.petmily.backend.comment.dto.CommentDto;
import com.petmily.backend.comment.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/comment")
public class CommentController {

    private final CommentService commentService;
    private final HttpSession httpSession;

    public CommentController(CommentService commentService, HttpSession httpSession){
        this.commentService = commentService;
        this.httpSession = httpSession;
    }

    @GetMapping("/{boardId}/{boardNum}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable String boardId, @PathVariable Long boardNum) {
        String loggedInUserId = (String)httpSession.getAttribute("id");
        List<CommentDto> comments = commentService.getCommentsByPost(boardId, boardNum, loggedInUserId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/write") //댓글 작성
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        String loggedInUserId = (String)httpSession.getAttribute("id");
        Integer commentPnum = commentDto.getCommentPnum();
        CommentDto createdComment = commentService.createComment(commentDto, loggedInUserId, commentPnum);
        return ResponseEntity.ok(createdComment);
    }

    @DeleteMapping("/{commentNum}") //댓글 삭제
    public ResponseEntity<Void> deleteCommentById(@PathVariable Long commentNum){
        String loggedInUserId = (String)httpSession.getAttribute("id");
        commentService.deleteCommentById(commentNum, loggedInUserId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{commentNum}") // 댓글 수정
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentNum, @RequestBody CommentDto commentDto){
        String loggedInUserId = (String)httpSession.getAttribute("id");
        CommentDto updatedComment = commentService.updateComment(commentNum, commentDto, loggedInUserId);
        return ResponseEntity.ok(updatedComment);
    }

}
