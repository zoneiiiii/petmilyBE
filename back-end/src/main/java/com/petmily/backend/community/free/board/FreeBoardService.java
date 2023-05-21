package com.petmily.backend.community.free.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;

@Service
public class FreeBoardService {

	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Autowired
    private MemberService memberService;
	
	// 전체 게시글 조회
	@Transactional
	public List<FreeBoardList> getFreeBoardList() {
		return freeBoardRepository.getFreeBoards();
	}
	
	// 게시글 상세조회
	@Transactional
	public FreeBoardDetail getFreeBoard(Long bardNum) {
		FreeBoardDetail freeBoard = freeBoardRepository.findFreeBoardDetail(bardNum);
		freeBoardRepository.updateBoardCount(bardNum);
		return freeBoard;
	}
	
	// 게시글 작성
	 public FreeBoardDto createFreeBoard(FreeBoardDto freeBoardDto, String memberId){
	        Member member = memberService.getMember(memberId);
	        FreeBoard freeBoard = new FreeBoard();

	        freeBoard.setMemberNum(member.getMemberNum());
	        freeBoard.setBoardId("free");
	        freeBoard.setFreeSubject(freeBoardDto.getFreeSubject());
	        freeBoard.setFreeContent(freeBoardDto.getFreeContent());
	        freeBoard.setFreeCount(0);
	        freeBoard.setFreeDate(freeBoardDto.getFreeDate());
	        freeBoard.setImgThumbnail(freeBoardDto.getImgThumbnail());

	        freeBoardRepository.save(freeBoard);

	        return convertToDto(freeBoard);
	    }
	 
		private FreeBoardDto convertToDto(FreeBoard freeBoard){
	 
	 		FreeBoardDto freeBoardDto = new FreeBoardDto();
	 		freeBoardDto.setBoardNum(freeBoard.getBoardNum());
	 		freeBoardDto.setMemberNum(freeBoard.getMemberNum());
	 		freeBoardDto.setBoardId(freeBoard.getBoardId());
	 		freeBoardDto.setFreeSubject(freeBoard.getFreeSubject());
	 		freeBoardDto.setFreeContent(freeBoard.getFreeContent());
	 		freeBoardDto.setFreeCount(freeBoard.getFreeCount());
	 		freeBoardDto.setFreeDate(freeBoard.getFreeDate());
	 		freeBoardDto.setImgThumbnail(freeBoard.getImgThumbnail());
	 
	         return freeBoardDto;
	     }
}
	
	/* --------------------------------------------------------------------------------------------------------------- */
//	public List<FreeBoardDto> getAllFreeBoards() {
//		List<FreeBoard> freeBoards = freeBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "freeDate"));
//	
//		return freeBoards.stream()
//				.map(this::convertToDto)
//				.collect(Collectors.toList());
//	}
//	
//	public FreeBoardDto getFreeBoardById(Long boardNum) {
//		FreeBoard freeBoard = freeBoardRepository.findById(boardNum)
//				.orElseThrow(() -> new NoSuchElementException("No freeBoard found with boardNum: " + boardNum));
//		
//		return convertToDto(freeBoard);
//	}
//	
//	public void increaseViewCount(Long boardNum) {
//		FreeBoard freeBoard = freeBoardRepository.findById(boardNum)
//				.orElseThrow(() -> new NoSuchElementException("No freeBoard found with boardNum: " + boardNum));
//		freeBoard.setFreeCount(freeBoard.getFreeCount() + 1);
//		freeBoardRepository.save(freeBoard);
//	}
//	
//	
//	private FreeBoardDto convertToDto(FreeBoard freeBoard){
//
//		FreeBoardDto freeBoardDto = new FreeBoardDto();
//		freeBoardDto.setBoardNum(freeBoard.getBoardNum());
//		freeBoardDto.setMemberNum(freeBoard.getMemberNum());
//		freeBoardDto.setBoardId(freeBoard.getBoardId());
//		freeBoardDto.setFreeSubject(freeBoard.getFreeSubject());
//		freeBoardDto.setFreeContent(freeBoard.getFreeContent());
//		freeBoardDto.setFreeCount(freeBoard.getFreeCount());
//		freeBoardDto.setFreeDate(freeBoard.getFreeDate());
//		freeBoardDto.setImgThumbnail(freeBoard.getImgThumbnail());
//
//        return freeBoardDto;
//    }

