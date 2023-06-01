package com.petmily.backend.community.free.board;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/board/free")
public class FreeBoardController {

	private final FreeBoardService freeBoardService;
	private final HttpSession httpSession;
	
	@Autowired
	public FreeBoardController(FreeBoardService freeBoardService, HttpSession httpSession) {
		this.freeBoardService = freeBoardService;
		this.httpSession = httpSession;
	}
	
	// 전체글 조회 기능
	@GetMapping
	public List<FreeBoardList> getFreeBoardList() {
		List<FreeBoardList> res = freeBoardService.getFreeBoardList();
		System.out.println(res.size());
		return freeBoardService.getFreeBoardList();
	}
	
	// 게시글 상세조회
	@GetMapping("/{boardNum}")
	public FreeBoardDetail getBoard(@PathVariable Long boardNum) {
		return freeBoardService.getFreeBoard(boardNum);
	}
	
	// 게시글 작성
	@PostMapping("/write")
    public ResponseEntity<FreeBoardDto> createFreeBoard(@RequestBody FreeBoardDto freeBoardDto, HttpSession session){
        System.out.println(session.getAttribute("id"));
        String memberId = (String)session.getAttribute("id");
        FreeBoardDto createdFreeBoard = freeBoardService.createFreeBoard(freeBoardDto, memberId);
        return ResponseEntity.ok(createdFreeBoard);
    }
	
	// 게시글 삭제
	@DeleteMapping("/{boardNum}")
	public ResponseEntity<Void> deleteFreeBoardById(@PathVariable Long boardNum){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        freeBoardService.deleteFreeBoardById(boardNum, loggedInUserId);
        log.info("사용자 {} boardNum {} 삭제 완료 ", loggedInUserId, boardNum);
        return ResponseEntity.ok().build();
    }
	
	//게시글 수정
	@PutMapping("/{boardNum}") 
    public ResponseEntity<FreeBoardDto> updateFreeBoard(@PathVariable Long boardNum, @RequestBody FreeBoardDto freeBoardDto){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        FreeBoardDto updatedFreeBoard = freeBoardService.updateFreeBoard(boardNum, freeBoardDto, loggedInUserId);
        log.info("사용자 {} boardNum {} 수정 완료 ", loggedInUserId, boardNum);
        return  ResponseEntity.ok(updatedFreeBoard);
    }
}