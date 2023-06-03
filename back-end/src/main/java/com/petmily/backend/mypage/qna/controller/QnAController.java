package com.petmily.backend.mypage.qna.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.mypage.qna.domain.QnABoard;
import com.petmily.backend.mypage.qna.dto.QnADto;
import com.petmily.backend.mypage.qna.service.QnAService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/board/qna")
public class QnAController {

	private final QnAService qnaService;
	private final HttpSession httpSession;
	
	public QnAController(QnAService qnaService, HttpSession httpSession){
        this.qnaService = qnaService; 
        this.httpSession = httpSession;
    }

    @GetMapping
    public ResponseEntity<Page<QnADto>> getAllQnA(@RequestParam(value="page", defaultValue="0")int page) {//모든 게시글 리스트
        Page<QnADto> qnaDto = qnaService.getAllQnA(page);
        return ResponseEntity.ok(qnaDto);
    }
    
    @GetMapping("/list/{memberNum}")
    public ResponseEntity<Page<QnADto>> getQnAByMemberNum(@RequestParam(value="page", defaultValue="0") int page, @PathVariable Long memberNum) {
    	Page<QnADto> qna = qnaService.getQnAByMemberNum(page,memberNum);
        return ResponseEntity.ok(qna);
    }
	
	
    @PostMapping("/write") //게시글 작성
    public ResponseEntity<QnADto> createQnaPost(@RequestBody QnADto qnaDto, HttpSession session){
    	String memberId = (String)session.getAttribute("id");
        QnADto createdQna = qnaService.createQnaPost(qnaDto, memberId);
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
    
    @PutMapping("/{boardNum}")//관리자 답변 업데이트
	public void updateQna(@PathVariable Long boardNum, @RequestBody QnABoard qnaboard) {
    	qnaService.updateQna(boardNum, qnaboard);
	}
}