package com.petmily.backend.community.flea.board;

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
@RequestMapping("/board/flea")
public class FleaBoardController {

	private final FleaBoardService fleaBoardService;
	private final HttpSession httpSession;
	
	public FleaBoardController(FleaBoardService fleaBoardService, HttpSession httpSession) {
		this.fleaBoardService = fleaBoardService;
		this.httpSession = httpSession;
	}
	
	// 전체글 조회
	@GetMapping
    public List<FleaBoardList> getBoards() {
		List<FleaBoardList> res=fleaBoardService.getFleaBoardList();
		System.out.println(res.size());
        return fleaBoardService.getFleaBoardList();
    }
	
	// 상세글 조회
    @GetMapping("/{boardNum}")
    public FleaBoardDetail getBoard(@PathVariable Long boardNum) {
        return fleaBoardService.getFleaBoard(boardNum);
    }
    
    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<FleaBoardDto> createBoard(@RequestBody FleaBoardDto fleaBoardDto, HttpSession session) {
    	System.out.println(session.getAttribute("id"));
    	String memberId = (String)session.getAttribute("id");
    	FleaBoardDto createdFleaBoard = fleaBoardService.createFleaBoard(fleaBoardDto, memberId);
    	return ResponseEntity.ok(createdFleaBoard);
    }
    
    // 게시글 삭제
    @DeleteMapping("/{boardNum}")
	public ResponseEntity<Void> deleteFleaBoardById(@PathVariable Long boardNum){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        fleaBoardService.deleteFleaBoardById(boardNum, loggedInUserId);
        log.info("사용자 {} boardNum {} 삭제 완료 ", loggedInUserId, boardNum);
        return ResponseEntity.ok().build();
    }
    
    //게시글 수정
  	@PutMapping("/{boardNum}") 
    public ResponseEntity<FleaBoardDto> updateFleaBoard(@PathVariable Long boardNum, @RequestBody FleaBoardDto fleaBoardDto){
  		String loggedInUserId = (String) httpSession.getAttribute("id");
  		FleaBoardDto updatedFleaBoard = fleaBoardService.updateFleaBoard(boardNum, fleaBoardDto, loggedInUserId);
  		log.info("사용자 {} boardNum {} 수정 완료 ", loggedInUserId, boardNum);
  		return  ResponseEntity.ok(updatedFleaBoard);
  	}
}
