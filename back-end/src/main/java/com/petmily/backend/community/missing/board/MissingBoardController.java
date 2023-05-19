package com.petmily.backend.community.missing.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/missing")
public class MissingBoardController {

	private final MissingBoardService missingBoardService;
	
	@Autowired
	public MissingBoardController(MissingBoardService missingBoardService) {
		this.missingBoardService = missingBoardService;
	}
	
	@GetMapping
    public List<MissingBoardList> getBoards() {
		System.out.println("aaa");
		List<MissingBoardList> res=missingBoardService.getMissingBoardList();
		System.out.println(res.size());
        return missingBoardService.getMissingBoardList();
    }

    @GetMapping("/{boardNum}")
    public MissingBoardDetail getBoard(@PathVariable Long boardNum) {
        return missingBoardService.getMissingBoard(boardNum);
    }

    @PostMapping("/write")
    public void createBoard(@RequestBody MissingBoard missingBoard) {
        missingBoardService.createMissingBoard(missingBoard);
    }

    @PutMapping("/{boardNum}")
    public void updateBoard(@PathVariable Long boardId, @RequestBody MissingBoard missingBoard) {
        missingBoardService.updateMissingBoard(boardId, missingBoard);
    }

    @DeleteMapping("/{boardNum}")
    public void deleteBoard(@PathVariable Long boardNum) {
        missingBoardService.deleteMissingBoard(boardNum);
    }
}
