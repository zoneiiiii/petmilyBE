package com.petmily.backend.mypage.qna.service;

import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petmily.backend.adopt.adoptInfo.Adopt;
import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.mypage.qna.domain.QnABoard;
import com.petmily.backend.mypage.qna.dto.QnADto;
import com.petmily.backend.mypage.qna.repository.QnARepository;
import jakarta.transaction.Transactional;


@Service
public class QnAService {
	@Autowired
	private QnARepository qnaRepository;

    @Autowired
    private MemberService memberService;

    public Page<QnADto> getAllQnA(int page){
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "boardNum");
        Page<QnABoard> qna = qnaRepository.findAll(pageable);

        return qna.map(this::convertToDto);
    }

    public QnADto getQnAById(Long boardNum) {
        QnABoard qna = qnaRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("No QnA found with boardNum: " + boardNum));

        return convertToDto(qna);
    }
    
    @Transactional
    public Page<QnADto> getQnAByMemberNum(int page, Long memberNum) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "boardNum");
        Page<QnABoard> qna = qnaRepository.findQnAByMemberNum(memberNum,pageable);

        return qna.map(this::convertToDto);
    }   
         
    private QnADto convertToDto(QnABoard qna) {
    	QnADto qnaDto = new QnADto();
    	qnaDto.setBoardNum(qna.getBoardNum());
    	qnaDto.setMemberNum(qna.getMemberNum());
    	qnaDto.setBoardId(qna.getBoardId());
    	qnaDto.setQnaSubject(qna.getQnaSubject());
    	qnaDto.setQnaContent(qna.getQnaContent());
    	qnaDto.setQnaStatus(qna.getQnaStatus());
    	qnaDto.setQnaImg(qna.getQnaImg());
    	qnaDto.setQnaDate(qna.getQnaDate());
    	qnaDto.setAdminAnswer(qna.getAdminAnswer());

        return qnaDto;
    }

    public QnADto createQnaPost(QnADto qnaDto, String memberId){
        QnABoard qna = new QnABoard();
        Member member = memberService.getMember(memberId);

        qna.setMemberNum(member.getMemberNum());
        qna.setBoardId("qna");
        qna.setQnaSubject(qnaDto.getQnaSubject());
        qna.setQnaContent(qnaDto.getQnaContent());
        qna.setQnaImg(qnaDto.getQnaImg());
        qna.setQnaDate(qnaDto.getQnaDate());
        qna.setQnaStatus(qnaDto.getQnaStatus());
        qna.setAdminAnswer(qnaDto.getAdminAnswer());
        qnaRepository.save(qna);

        return convertToDto(qna);
    }
    
    public void deleteQnaById(Long boardNum){
    	QnABoard qna = qnaRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));

    	qnaRepository.delete(qna);
    }
    
    @Transactional
	public void updateQna(Long boardNum, QnABoard qna) {
    	qnaRepository.updateQna(boardNum,qna.getQnaStatus(),qna.getAdminAnswer());
		
	}
}