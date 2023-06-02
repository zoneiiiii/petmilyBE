package com.petmily.backend.community.find.board;

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
@RequestMapping("/board/find")
public class FindBoardController {

	private final FindBoardService findBoardService;
	private final HttpSession httpSession;
	
	public FindBoardController(FindBoardService findBoardService, HttpSession httpSession) {
		this.findBoardService = findBoardService;
		this.httpSession = httpSession;
	}
	
	// 전체글 조회
	@GetMapping
    public List<FindBoardList> getBoards() {
		List<FindBoardList> res=findBoardService.getFindBoardList();
		System.out.println(res.size());
        return findBoardService.getFindBoardList();
    }
	
	// 상세글 조회
    @GetMapping("/{boardNum}")
    public FindBoardDetail getBoard(@PathVariable Long boardNum) {
        return findBoardService.getFindBoard(boardNum);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getFindCount(){
        return ResponseEntity.ok(findBoardService.getFindCount());
    }
    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<FindBoardDto> createBoard(@RequestBody FindBoardDto findBoardDto, HttpSession session) {
    	System.out.println(session.getAttribute("id"));
    	String memberId = (String)session.getAttribute("id");
    	FindBoardDto createdFindBoard = findBoardService.createFindBoard(findBoardDto, memberId);
    	return ResponseEntity.ok(createdFindBoard);
    }
    
    // 게시글 삭제
    @DeleteMapping("/{boardNum}")
	public ResponseEntity<Void> deleteFindBoardById(@PathVariable Long boardNum){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        findBoardService.deleteFindBoardById(boardNum, loggedInUserId);
        log.info("사용자 {} boardNum {} 삭제 완료 ", loggedInUserId, boardNum);
        return ResponseEntity.ok().build();
    }
    
    //게시글 수정
  	@PutMapping("/{boardNum}") 
    public ResponseEntity<FindBoardDto> updateFindBoard(@PathVariable Long boardNum, @RequestBody FindBoardDto findBoardDto){
  		String loggedInUserId = (String) httpSession.getAttribute("id");
  		FindBoardDto updatedFindBoard = findBoardService.updateFindBoard(boardNum, findBoardDto, loggedInUserId);
  		log.info("사용자 {} boardNum {} 수정 완료 ", loggedInUserId, boardNum);
  		return  ResponseEntity.ok(updatedFindBoard);
      }
}
