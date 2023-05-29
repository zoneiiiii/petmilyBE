package com.petmily.backend.community.free.board;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
	@Transactional
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
	 
	// 게시글 삭제
	@Transactional
	public void deleteFreeBoardById(Long boardNum, String loggedInUserId) {
		 Member member = memberService.getMember(loggedInUserId); // 로그인한 사용자 정보 가져오기
		 FreeBoard freeBoard = freeBoardRepository.findById(boardNum)
				 .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
		 if (!member.getMemberNum().equals(freeBoard.getMemberNum())) {
			 throw new AccessDeniedException("해당 게시글을 삭제할 권한이 없습니다.");
		 }
		 freeBoardRepository.delete(freeBoard);
	 }
	 
	// 게시글 수정
	@Transactional
	public FreeBoardDto updateFreeBoard(Long boardNum, FreeBoardDto freeBoardDto, String loggedInUserId){
	        Member member = memberService.getMember(loggedInUserId);
	        FreeBoard freeBoard = freeBoardRepository.findById(boardNum)
	                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
	        if(!member.getMemberNum().equals(freeBoard.getMemberNum())){
	            throw new AccessDeniedException("해당 게시글을 수정할 권한이 없습니다.");
	        }
	        System.out.println(freeBoardDto);
	        freeBoard.setFreeSubject(freeBoardDto.getFreeSubject());
	        freeBoard.setFreeContent(freeBoardDto.getFreeContent());
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