package com.petmily.backend.mypage.board;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.adopt.adoptReview.ReviewBoardList;
import com.petmily.backend.community.find.board.FindBoardList;
import com.petmily.backend.community.flea.board.FleaBoardList;
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
	//실종 동물 게시판
	@GetMapping("/missing/{memberNum}")
    public Page<MissingBoardList> getMissingByMemberNum(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="8") int limit, @PathVariable Long memberNum) {
        return mypageService.getMissingBoardByMemberNum(page, limit,memberNum);
    }
	
	//목격 제보 게시판
	@GetMapping("/find/{memberNum}")
	public Page<FindBoardList> getFindBoardByMemberNum(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="8") int limit, @PathVariable Long memberNum) {
        return mypageService.getFindBoardByMemberNum(page, limit,memberNum);
    }
	
	//매매장터
	@GetMapping("/flea/{memberNum}")
	public List<FleaBoardList> getFleaBoardByMemberNum(@PathVariable Long memberNum) {
        return mypageService.getFleaBoardByMemberNum(memberNum);
    }
	
	//입양후기
	@GetMapping("/adoptReview/{memberNum}")
	public Page<ReviewBoardList> getAdoptReviewByMemberNum(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="6") int limit, @PathVariable Long memberNum) {
        return mypageService.getAdoptReviewByMemberNum(page, limit,memberNum);
    }
	
	//자유 게시판
	@GetMapping("/free/{memberNum}")
	public Page<FreeBoardList> getFreeByMemberNum(@RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="limit", defaultValue="10") int limit, @PathVariable Long memberNum) {
		return mypageService.getFreeByMemberNum(page, limit,memberNum);
	}
	
	//봉사후기 게시판
	@GetMapping("/review/{memberNum}")
    public ResponseEntity<Page<VolunteerReviewDto>> getReviewByMemberNum(@RequestParam(value="page", defaultValue="0") int page, @PathVariable Long memberNum){
        Page<VolunteerReviewDto> volunteerReviews = mypageService.getReviewByMemberNum(page,memberNum);
        return ResponseEntity.ok(volunteerReviews);
    }
}
