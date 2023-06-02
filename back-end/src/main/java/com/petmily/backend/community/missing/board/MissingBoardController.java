package com.petmily.backend.community.missing.board;

import java.util.List;

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
@RequestMapping("/board/missing")
public class MissingBoardController {

	private final MissingBoardService missingBoardService;
	private final HttpSession httpSession;
	
	public MissingBoardController(MissingBoardService missingBoardService, HttpSession httpSession) {
		this.missingBoardService = missingBoardService;
		this.httpSession = httpSession;
	}
	
	// 전체글 조회
	@GetMapping
    public List<MissingBoardList> getBoards() {
		System.out.println("aaa");
		List<MissingBoardList> res=missingBoardService.getMissingBoardList();
		System.out.println(res.size());
        return missingBoardService.getMissingBoardList();
    }

	// 상세글 조회
    @GetMapping("/{boardNum}")
    public MissingBoardDetail getBoard(@PathVariable Long boardNum) {
        return missingBoardService.getMissingBoard(boardNum);
    }

	@GetMapping("/count")
	public ResponseEntity<Long> getMissingCount(){
		return ResponseEntity.ok(missingBoardService.getMissingCount());
	}

    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<MissingBoardDto> createBoard(@RequestBody MissingBoardDto missingBoardDto, HttpSession session) {
    	System.out.println(session.getAttribute("id"));
    	String memberId = (String)session.getAttribute("id");
    	MissingBoardDto createdMissingBoard = missingBoardService.createMissingBoard(missingBoardDto, memberId);
    	return ResponseEntity.ok(createdMissingBoard);
    }
    
    // 게시글 삭제
    @DeleteMapping("/{boardNum}")
	public ResponseEntity<Void> deleteMissingBoardById(@PathVariable Long boardNum){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        missingBoardService.deleteMissingBoardById(boardNum, loggedInUserId);
        log.info("사용자 {} boardNum {} 삭제 완료 ", loggedInUserId, boardNum);
        return ResponseEntity.ok().build();
    }
    
    //게시글 수정
  	@PutMapping("/{boardNum}") 
    public ResponseEntity<MissingBoardDto> updateMissingBoard(@PathVariable Long boardNum, @RequestBody MissingBoardDto missingBoardDto){
  		String loggedInUserId = (String) httpSession.getAttribute("id");
  		MissingBoardDto updatedMissingBoard = missingBoardService.updateMissingBoard(boardNum, missingBoardDto, loggedInUserId);
  		log.info("사용자 {} boardNum {} 수정 완료 ", loggedInUserId, boardNum);
  		return  ResponseEntity.ok(updatedMissingBoard);
      }
}