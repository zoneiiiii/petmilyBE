package com.petmily.backend.mypage.board;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.community.free.board.FreeBoardList;
import com.petmily.backend.community.missing.board.MissingBoardList;
import com.petmily.backend.support.volunteerReview.dto.VolunteerReviewDto;

@RestController
@RequestMapping("/mypage")
public class MypageBoardController {
	private final MypageBoardService mypageService;
	
	public MypageBoardController (MypageBoardService mypageService) {
		this.mypageService = mypageService;
	}
	
	@GetMapping("/missing/{memberNum}/{boardId}")
    public List<MissingBoardList> getMissingByMemberNum(@PathVariable Long memberNum, String boardId) {
        return mypageService.getMissingBoardByMemberNum(memberNum);
    }
	
	@GetMapping("/free/{memberNum}/{boardId}")
	public List<FreeBoardList> getFreeByMemberNum(@PathVariable Long memberNum, String boardId) {
		return mypageService.getFreeByMemberNum(memberNum);
	}
	
	@GetMapping("/review/{memberNum}/{boardId}")
	public List<VolunteerReviewDto> getReviewByMemberNum(@PathVariable Long memberNum, String boardId) {
		return mypageService.getReviewByMemberNum(memberNum);
	}
}
