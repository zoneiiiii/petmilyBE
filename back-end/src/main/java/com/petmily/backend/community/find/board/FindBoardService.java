package com.petmily.backend.community.find.board;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;

import jakarta.transaction.Transactional;

@Service
public class FindBoardService {

	 @Autowired
    private FindBoardRepository findBoardRepository;
	    
    @Autowired
    private MemberService memberService;
    
    // 전체 게시글 조회
    @Transactional //(readOnly = true)
    public List<FindBoardList> getFindBoardList() {
        return findBoardRepository.getFindBoards();
    }
    
    // 게시글 상세 조회
    @Transactional //(readOnly = true)
    public FindBoardDetail getFindBoard(Long boardNum) {
    	FindBoardDetail findBoard = findBoardRepository.findFindBoardDetail(boardNum);
    	findBoardRepository.updateBoardCount(boardNum);
        return findBoard;
    }

	public Long getFindCount(){
		return findBoardRepository.count();
	}
    
    // 게시글 작성
    @Transactional
    public FindBoardDto createFindBoard(FindBoardDto findBoardDto, String memberId) {
    	Member member = memberService.getMember(memberId);
    	FindBoard findBoard = new FindBoard();
    	
    	findBoard.setMemberNum(member.getMemberNum());
    	findBoard.setBoardId("find");
    	findBoard.setBoardSubject(findBoardDto.getBoardSubject());
    	findBoard.setBoardContent(findBoardDto.getBoardContent());
    	findBoard.setBoardCount(0);
    	findBoard.setBoardDate(findBoardDto.getBoardDate());
    	findBoard.setImgThumbnail(findBoardDto.getImgThumbnail());
    	findBoard.setBoardLocation(findBoardDto.getBoardLocation());
    	findBoard.setBoardSpecies(findBoardDto.getBoardSpecies());
    	findBoard.setBoardGender(findBoardDto.getBoardGender());
    	findBoard.setBoardAge(findBoardDto.getBoardAge());
    	
    	findBoardRepository.save(findBoard);

        return convertToDto(findBoard);
    }
    
    // 게시글 삭제
    @Transactional
    public void deleteFindBoardById(Long boardNum, String loggedInUserId) {
		 Member member = memberService.getMember(loggedInUserId); // 로그인한 사용자 정보 가져오기
		 FindBoard findBoard = findBoardRepository.findById(boardNum)
				 .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
		 if (!member.getMemberNum().equals(findBoard.getMemberNum())) {
			 throw new AccessDeniedException("해당 게시글을 삭제할 권한이 없습니다.");
		 }
		 findBoardRepository.delete(findBoard);
	 }
    
    // 게시글 수정
  	@Transactional
  	public FindBoardDto updateFindBoard(Long boardNum, FindBoardDto findBoardDto, String loggedInUserId){
	    Member member = memberService.getMember(loggedInUserId);
	    FindBoard findBoard = findBoardRepository.findById(boardNum)
	            .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
	    if(!member.getMemberNum().equals(findBoard.getMemberNum())){
	        throw new AccessDeniedException("해당 게시글을 수정할 권한이 없습니다.");
	    }
	    System.out.println(findBoardDto);
	    findBoard.setBoardSubject(findBoardDto.getBoardSubject());
	    findBoard.setBoardContent(findBoardDto.getBoardContent());
	    findBoard.setImgThumbnail(findBoardDto.getImgThumbnail());
	  	findBoard.setBoardLocation(findBoardDto.getBoardLocation());
	  	findBoard.setBoardSpecies(findBoardDto.getBoardSpecies());
	  	findBoard.setBoardGender(findBoardDto.getBoardGender());
	  	findBoard.setBoardAge(findBoardDto.getBoardAge());
	  	findBoardRepository.save(findBoard);
	
	    return convertToDto(findBoard);
  	}

    
    private FindBoardDto convertToDto(FindBoard FindBoard) {

    	FindBoardDto findBoardDto = new FindBoardDto();
    	findBoardDto.setBoardNum(FindBoard.getBoardNum());
    	findBoardDto.setMemberNum(FindBoard.getMemberNum());
    	findBoardDto.setBoardId(FindBoard.getBoardId());
    	findBoardDto.setBoardSubject(FindBoard.getBoardSubject());
    	findBoardDto.setBoardContent(FindBoard.getBoardContent());
    	findBoardDto.setBoardCount(FindBoard.getBoardCount());
    	findBoardDto.setBoardDate(FindBoard.getBoardDate());
    	findBoardDto.setImgThumbnail(FindBoard.getImgThumbnail());
    	findBoardDto.setBoardLocation(FindBoard.getBoardLocation());
    	findBoardDto.setBoardSpecies(FindBoard.getBoardSpecies());
    	findBoardDto.setBoardAge(FindBoard.getBoardAge());

        return findBoardDto;
    }

}
