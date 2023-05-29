package com.petmily.backend.mypage.qna.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.mypage.qna.domain.QnABoard;
import com.petmily.backend.mypage.qna.dto.QnADto;
import com.petmily.backend.mypage.qna.repository.QnARepository;


@Service
public class QnAService {
	@Autowired
	private QnARepository qnaRepository;

    @Autowired
    private MemberService memberService;

    public List<QnADto> getQnAList(){
        List<QnABoard> qnaList = qnaRepository.findAll(Sort.by(Sort.Direction.DESC, "boardNum"));

        return qnaList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public QnADto getQnAById(Long boardNum) {
        QnABoard qna = qnaRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("No QnA found with boardNum: " + boardNum));

        return convertToDto(qna);
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
        qnaRepository.save(qna);

        return convertToDto(qna);
    }
    
    
    public void deleteQnaById(Long boardNum){
    	QnABoard qna = qnaRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));

    	qnaRepository.delete(qna);
    }
}