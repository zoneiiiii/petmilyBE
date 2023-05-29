package com.petmily.backend.community.missing.board;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;

import jakarta.transaction.Transactional;

@Service
public class MissingBoardService {
    @Autowired
    private MissingBoardRepository missingBoardRepository;
    
    @Autowired
    private MemberService memberService;

    // 전체 게시글 조회
    @Transactional //(readOnly = true)
    public List<MissingBoardList> getMissingBoardList() {
        return missingBoardRepository.getMissingBoards();
    }

    // 게시글 상세 조회
    @Transactional //(readOnly = true)
    public MissingBoardDetail getMissingBoard(Long boardNum) {
    	MissingBoardDetail missingBoard = missingBoardRepository.findMissingBoardDetail(boardNum);
        missingBoardRepository.updateBoardCount(boardNum);
        return missingBoard;
    }

    // 게시글 작성
    @Transactional
    public MissingBoardDto createMissingBoard(MissingBoardDto missingBoardDto, String memberId) {
    	Member member = memberService.getMember(memberId);
    	MissingBoard missingBoard = new MissingBoard();
    	
    	missingBoard.setMemberNum(member.getMemberNum());
    	missingBoard.setBoardId("missing");
    	missingBoard.setBoardSubject(missingBoardDto.getBoardSubject());
    	missingBoard.setBoardContent(missingBoardDto.getBoardContent());
    	missingBoard.setBoardCount(0);
    	missingBoard.setBoardDate(missingBoardDto.getBoardDate());
    	missingBoard.setImgThumbnail(missingBoardDto.getImgThumbnail());
    	missingBoard.setBoardLocation(missingBoardDto.getBoardLocation());
    	missingBoard.setBoardSpecies(missingBoardDto.getBoardSpecies());
    	missingBoard.setBoardGender(missingBoardDto.getBoardGender());
    	missingBoard.setBoardStatus(missingBoardDto.getBoardStatus());
    	missingBoard.setBoardName(missingBoardDto.getBoardName());
    	missingBoard.setBoardAge(missingBoardDto.getBoardAge());
    	
    	missingBoardRepository.save(missingBoard);

        return convertToDto(missingBoard);
    }
    
    // 게시글 삭제
    @Transactional
    public void deleteMissingBoardById(Long boardNum, String loggedInUserId) {
		 Member member = memberService.getMember(loggedInUserId); // 로그인한 사용자 정보 가져오기
		 MissingBoard missingBoard = missingBoardRepository.findById(boardNum)
				 .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
		 if (!member.getMemberNum().equals(missingBoard.getMemberNum())) {
			 throw new AccessDeniedException("해당 게시글을 삭제할 권한이 없습니다.");
		 }
		 missingBoardRepository.delete(missingBoard);
	 }
    
    // 게시글 수정
 	@Transactional
 	public MissingBoardDto updateMissingBoard(Long boardNum, MissingBoardDto missingBoardDto, String loggedInUserId){
 	        Member member = memberService.getMember(loggedInUserId);
 	       MissingBoard missingBoard = missingBoardRepository.findById(boardNum)
 	                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
 	        if(!member.getMemberNum().equals(missingBoard.getMemberNum())){
 	            throw new AccessDeniedException("해당 게시글을 수정할 권한이 없습니다.");
 	        }
 	        System.out.println(missingBoardDto);
 	    	missingBoard.setBoardSubject(missingBoardDto.getBoardSubject());
 	    	missingBoard.setBoardContent(missingBoardDto.getBoardContent());
 	    	missingBoard.setImgThumbnail(missingBoardDto.getImgThumbnail());
 	    	missingBoard.setBoardLocation(missingBoardDto.getBoardLocation());
 	    	missingBoard.setBoardSpecies(missingBoardDto.getBoardSpecies());
 	    	missingBoard.setBoardGender(missingBoardDto.getBoardGender());
 	    	missingBoard.setBoardStatus(missingBoardDto.getBoardStatus());
 	    	missingBoard.setBoardName(missingBoardDto.getBoardName());
 	    	missingBoard.setBoardAge(missingBoardDto.getBoardAge());
 	    	missingBoardRepository.save(missingBoard);

 	        return convertToDto(missingBoard);
 	}
    
    private MissingBoardDto convertToDto(MissingBoard missingBoard) {

    	MissingBoardDto missingBoardDto = new MissingBoardDto();
    	missingBoardDto.setBoardNum(missingBoard.getBoardNum());
    	missingBoardDto.setMemberNum(missingBoard.getMemberNum());
    	missingBoardDto.setBoardId(missingBoard.getBoardId());
    	missingBoardDto.setBoardSubject(missingBoard.getBoardSubject());
    	missingBoardDto.setBoardContent(missingBoard.getBoardContent());
    	missingBoardDto.setBoardCount(missingBoard.getBoardCount());
    	missingBoardDto.setBoardDate(missingBoard.getBoardDate());
    	missingBoardDto.setImgThumbnail(missingBoard.getImgThumbnail());
    	missingBoardDto.setBoardLocation(missingBoard.getBoardLocation());
    	missingBoardDto.setBoardSpecies(missingBoard.getBoardSpecies());
    	missingBoardDto.setBoardGender(missingBoard.getBoardGender());
    	missingBoardDto.setBoardStatus(missingBoard.getBoardStatus());
    	missingBoardDto.setBoardName(missingBoard.getBoardName());
    	missingBoardDto.setBoardAge(missingBoard.getBoardAge());

        return missingBoardDto;
    }

}