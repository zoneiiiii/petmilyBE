package com.petmily.backend.support.volunteerReview.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;
import com.petmily.backend.support.volunteerReview.dto.VolunteerReviewDto;
import com.petmily.backend.support.volunteerReview.repository.VolunteerReviewRepository;

@Service
public class VolunteerReviewService {

    @Autowired
    private VolunteerReviewRepository volunteerReviewRepository;

    public List<VolunteerReviewDto> getAllVolunteerReviews(){
        List<VolunteerReview> volunteerReviews = volunteerReviewRepository.findAll(Sort.by(Sort.Direction.DESC, "reviewDate"));

        return volunteerReviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
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

    private VolunteerReviewDto convertToDto(VolunteerReview volunteerReview){

        VolunteerReviewDto volunteerReviewDto = new VolunteerReviewDto();
        volunteerReviewDto.setBoardNum(volunteerReview.getBoardNum());
        volunteerReviewDto.setMemberNum(volunteerReview.getMemberNum());
        volunteerReviewDto.setBoardId(volunteerReview.getBoardId());
        volunteerReviewDto.setReviewSubject(volunteerReview.getReviewSubject());
        volunteerReviewDto.setReviewContent(volunteerReview.getReviewContent());
        volunteerReviewDto.setReviewCount(volunteerReview.getReviewCount());
        volunteerReviewDto.setReviewDate(volunteerReview.getReviewDate());
        volunteerReviewDto.setImgThumbnail(volunteerReview.getImgThumbnail());

        return volunteerReviewDto;
    }
}
