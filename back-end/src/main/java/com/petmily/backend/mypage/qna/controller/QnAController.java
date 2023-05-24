package com.petmily.backend.mypage.qna.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.mypage.qna.dto.QnADto;
import com.petmily.backend.mypage.qna.service.QnAService;

@RestController
@RequestMapping("/board/qna")
public class QnAController {
	private final QnAService qnaService;
	
	public QnAController(QnAService qnaService){
        this.qnaService = qnaService; 
    }

    @GetMapping
    public ResponseEntity<List<QnADto>> getQnAList() {
        List<QnADto> qnaDto = qnaService.getQnAList();
        return ResponseEntity.ok(qnaDto);
    }
    
    @PostMapping("/write") //게시글 작성
    public ResponseEntity<QnADto> createQnaPost(@RequestBody QnADto qnaDto){
    	
        QnADto createdQna = qnaService.createQnaPost(qnaDto);
        return ResponseEntity.ok(createdQna);
    }
    
    @GetMapping("/{boardNum}") //게시글 상세
    public ResponseEntity<QnADto> getQnAById(@PathVariable Long boardNum) {
        QnADto qnaDto = qnaService.getQnAById(boardNum);
        return ResponseEntity.ok(qnaDto);
    }
    
    @DeleteMapping("/{boardNum}") //게시글 삭제
    public ResponseEntity<Void> deleteQnaById(@PathVariable Long boardNum){
        qnaService.deleteQnaById(boardNum);
        return ResponseEntity.ok().build();
    }
}