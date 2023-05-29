package com.petmily.backend.support.volunteerReview.service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;
import com.petmily.backend.support.volunteerReview.dto.VolunteerReviewDto;
import com.petmily.backend.support.volunteerReview.repository.VolunteerReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VolunteerReviewService {

    private final VolunteerReviewRepository volunteerReviewRepository;
    private final MemberService memberService;

    public VolunteerReviewService (VolunteerReviewRepository volunteerReviewRepository, MemberService memberService){
        this.volunteerReviewRepository = volunteerReviewRepository;
        this.memberService = memberService;
    }

//    public List<VolunteerReviewDto> getAllVolunteerReviews(){
//        List<VolunteerReview> volunteerReviews = volunteerReviewRepository.findAll(Sort.by(Sort.Direction.DESC, "reviewDate"));
//
//        return volunteerReviews.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }

    public Page<VolunteerReviewDto> getAllVolunteerReviews(int page){
        Pageable pageable = PageRequest.of(page, 12, Sort.Direction.DESC, "reviewDate");
        Page<VolunteerReview> volunteerReviews = volunteerReviewRepository.findAll(pageable);

        return volunteerReviews.map(this::convertToDto);
    }

    public VolunteerReviewDto getVolunteerReviewById(Long boardNum){
        VolunteerReview volunteerReview = volunteerReviewRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("No volunteerReview found with boardNum: " + boardNum));

        return convertToDto(volunteerReview);
    }

    public void increaseViewCount(Long boardNum){
        VolunteerReview volunteerReview = volunteerReviewRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("No volunteerReview found with boardNum:" + boardNum));
        volunteerReview.setReviewCount(volunteerReview.getReviewCount() + 1);
        volunteerReviewRepository.save(volunteerReview);
    }

    @Transactional
    public VolunteerReviewDto createVolunteerReview(VolunteerReviewDto volunteerReviewDto, String memberId){
        Member member = memberService.getMember(memberId);
        VolunteerReview volunteerReview = new VolunteerReview();

        volunteerReview.setMemberNum(member.getMemberNum());
        volunteerReview.setBoardId("volunteerReview");
        volunteerReview.setReviewSubject(volunteerReviewDto.getReviewSubject());
        volunteerReview.setReviewContent(volunteerReviewDto.getReviewContent());
        volunteerReview.setReviewCount(0);
        volunteerReview.setReviewDate(volunteerReviewDto.getReviewDate());
        volunteerReview.setImgThumbnail(volunteerReviewDto.getImgThumbnail());

        volunteerReviewRepository.save(volunteerReview);

        return convertToDto(volunteerReview);
    }

    @Transactional
    public void deleteVolunteerReviewById(Long boardNum, String loggedInUserId){
        Member member = memberService.getMember(loggedInUserId);
        VolunteerReview volunteerReview = volunteerReviewRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
        if(!member.getMemberNum().equals(volunteerReview.getMemberNum())) {
            throw new AccessDeniedException("해당 게시글을 삭제할 권한이 없습니다.");
        }
            volunteerReviewRepository.delete(volunteerReview);
        }

    @Transactional
    public VolunteerReviewDto updateVolunteerReview(Long boardNum, VolunteerReviewDto volunteerReviewDto, String loggedInUserId){
        Member member = memberService.getMember(loggedInUserId);
        VolunteerReview volunteerReview = volunteerReviewRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
        if(!member.getMemberNum().equals(volunteerReview.getMemberNum())){
            throw new AccessDeniedException("해당 게시글을 수정할 권한이 없습니다.");
        }
        System.out.println(volunteerReviewDto);
        volunteerReview.setReviewSubject(volunteerReviewDto.getReviewSubject());
        volunteerReview.setReviewContent(volunteerReviewDto.getReviewContent());
        volunteerReview.setImgThumbnail(volunteerReviewDto.getImgThumbnail());

        volunteerReviewRepository.save(volunteerReview);

        return convertToDto(volunteerReview);
    }

    private VolunteerReviewDto convertToDto(VolunteerReview volunteerReview){
        Member member = memberService.getMemberByNum(volunteerReview.getMemberNum());
        VolunteerReviewDto volunteerReviewDto = new VolunteerReviewDto();
        volunteerReviewDto.setBoardNum(volunteerReview.getBoardNum());
        volunteerReviewDto.setMemberNum(volunteerReview.getMemberNum());
        volunteerReviewDto.setBoardId(volunteerReview.getBoardId());
        volunteerReviewDto.setReviewSubject(volunteerReview.getReviewSubject());
        volunteerReviewDto.setReviewContent(volunteerReview.getReviewContent());
        volunteerReviewDto.setReviewCount(volunteerReview.getReviewCount());
        volunteerReviewDto.setReviewDate(volunteerReview.getReviewDate());
        volunteerReviewDto.setImgThumbnail(volunteerReview.getImgThumbnail());
        volunteerReviewDto.setMemberImg(member.getMemberImg());
        volunteerReviewDto.setMemberNickname(member.getMemberNickname());

        return volunteerReviewDto;
    }
}
