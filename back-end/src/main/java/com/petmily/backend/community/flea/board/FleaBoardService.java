package com.petmily.backend.community.flea.board;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;

import jakarta.transaction.Transactional;

@Service
public class FleaBoardService {
	@Autowired
    private FleaBoardRepository fleaBoardRepository;
    
    @Autowired
    private MemberService memberService;
    
    // 전체 게시글 조회
    @Transactional //(readOnly = true)
    public List<FleaBoardList> getFleaBoardList() {
        return fleaBoardRepository.getFleaBoards();
    }
    
    // 게시글 상세 조회
    @Transactional //(readOnly = true)
    public FleaBoardDetail getFleaBoard(Long boardNum) {
    	FleaBoardDetail fleaBoard = fleaBoardRepository.findFleaBoardDetail(boardNum);
    	fleaBoardRepository.updateBoardCount(boardNum);
        return fleaBoard;
    }

	public long getFleaCount(){
		return fleaBoardRepository.count();
	}
    
    // 게시글 작성
    @Transactional
    public FleaBoardDto createFleaBoard(FleaBoardDto fleaBoardDto, String memberId) {
    	Member member = memberService.getMember(memberId);
    	FleaBoard fleaBoard = new FleaBoard();
    	
    	fleaBoard.setMemberNum(member.getMemberNum());
    	fleaBoard.setBoardId("flea");
    	fleaBoard.setBoardSubject(fleaBoardDto.getBoardSubject());
    	fleaBoard.setBoardContent(fleaBoardDto.getBoardContent());
    	fleaBoard.setBoardCount(0);
    	fleaBoard.setBoardDate(fleaBoardDto.getBoardDate());
    	fleaBoard.setImgThumbnail(fleaBoardDto.getImgThumbnail());
    	fleaBoard.setBoardCost(fleaBoardDto.getBoardCost());
    	fleaBoard.setBoardCategory(fleaBoardDto.getBoardCategory());
    	fleaBoard.setBoardStatus(fleaBoardDto.getBoardStatus());
    	
    	fleaBoardRepository.save(fleaBoard);

        return convertToDto(fleaBoard);
    }
    
    // 게시글 삭제
    @Transactional
    public void deleteFleaBoardById(Long boardNum, String loggedInUserId) {
		 Member member = memberService.getMember(loggedInUserId); // 로그인한 사용자 정보 가져오기
		 FleaBoard fleaBoard = fleaBoardRepository.findById(boardNum)
				 .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
		 if (!member.getMemberNum().equals(fleaBoard.getMemberNum())) {
			 throw new AccessDeniedException("해당 게시글을 삭제할 권한이 없습니다.");
		 }
		 fleaBoardRepository.delete(fleaBoard);
	 }
    
    // 게시글 수정
  	@Transactional
  	public FleaBoardDto updateFleaBoard(Long boardNum, FleaBoardDto fleaBoardDto, String loggedInUserId){
  		Member member = memberService.getMember(loggedInUserId);
  		FleaBoard fleaBoard = fleaBoardRepository.findById(boardNum)
  				.orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
        if(!member.getMemberNum().equals(fleaBoard.getMemberNum())){
            throw new AccessDeniedException("해당 게시글을 수정할 권한이 없습니다.");
    	}
//        System.out.println(fleaBoardDto);
        fleaBoard.setBoardSubject(fleaBoardDto.getBoardSubject());
  	    fleaBoard.setBoardContent(fleaBoardDto.getBoardContent());
  	    fleaBoard.setImgThumbnail(fleaBoardDto.getImgThumbnail());
  	    fleaBoard.setBoardCost(fleaBoardDto.getBoardCost());
  	    fleaBoard.setBoardCategory(fleaBoardDto.getBoardCategory());
	  	fleaBoard.setBoardStatus(fleaBoardDto.getBoardStatus());
	  	fleaBoardRepository.save(fleaBoard);

	  	return convertToDto(fleaBoard);
  	}
    
    private FleaBoardDto convertToDto(FleaBoard fleaBoard) {

    	FleaBoardDto fleaBoardDto = new FleaBoardDto();
    	fleaBoardDto.setBoardNum(fleaBoard.getBoardNum());
    	fleaBoardDto.setMemberNum(fleaBoard.getMemberNum());
    	fleaBoardDto.setBoardId(fleaBoard.getBoardId());
    	fleaBoardDto.setBoardSubject(fleaBoard.getBoardSubject());
    	fleaBoardDto.setBoardContent(fleaBoard.getBoardContent());
    	fleaBoardDto.setBoardCount(fleaBoard.getBoardCount());
    	fleaBoardDto.setBoardDate(fleaBoard.getBoardDate());
    	fleaBoardDto.setImgThumbnail(fleaBoard.getImgThumbnail());
    	fleaBoardDto.setBoardCost(fleaBoard.getBoardCost());
    	fleaBoardDto.setBoardCategory(fleaBoard.getBoardCategory());
    	fleaBoardDto.setBoardStatus(fleaBoard.getBoardStatus());

        return fleaBoardDto;
    }
}
