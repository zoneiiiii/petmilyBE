package com.petmily.backend.community.free.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/board/free")
public class FreeBoardController {

	private final FreeBoardService freeBoardService;
	
	@Autowired
	public FreeBoardController(FreeBoardService freeBoardService) {
		this.freeBoardService = freeBoardService;
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
//	public FreeBoard write(@RequestBody FreeBoard req) {
//		FreeBoard freeBoard = FreeBoard.builder()
//				.memberNum(req.getMemberNum())
//				.boardId(req.getBoardId())
//				.freeSubject(req.getFreeSubject())
//				.freeContent(req.getFreeContent())
//				.freeCount(req.getFreeCount())
//				.freeDate(req.getFreeDate())
//				.imgThumbnail(req.getImgThumbnail())
//				.build();
//		
//		return freeBoardService.insert(freeBoard);
//	}
	
//	@GetMapping
//	public ResponseEntity<List<FreeBoardDto>> getAllFreeBoards(){
//		List<FreeBoardDto> freeBoards = freeBoardService.getAllFreeBoards();
//		
//		return ResponseEntity.ok(freeBoards);
//	}
//	
//	@GetMapping("/{boardNum}")
//	public ResponseEntity<FreeBoardDto> getFreeBoardById(@PathVariable Long boardNum) {
//		FreeBoardDto freeBoard = freeBoardService.getFreeBoardById(boardNum);
//		
//		return ResponseEntity.ok(freeBoard);
//	}
//	
//	@PostMapping("/{boardNum}/increase-viewcount")
//	public ResponseEntity<Void> increaseViewCount(@PathVariable Long boardNum) {
//		freeBoardService.increaseViewCount(boardNum);
//		
//		return ResponseEntity.ok().build();
//	}
}
