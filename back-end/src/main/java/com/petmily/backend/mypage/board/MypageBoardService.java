package com.petmily.backend.mypage.board;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmily.backend.community.free.board.FreeBoardList;
import com.petmily.backend.community.free.board.FreeBoardRepository;
import com.petmily.backend.community.missing.board.MissingBoardList;
import com.petmily.backend.community.missing.board.MissingBoardRepository;
import com.petmily.backend.support.volunteerReview.dto.VolunteerReviewDto;
import com.petmily.backend.support.volunteerReview.repository.VolunteerReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class MypageBoardService {
	@Autowired
    private MissingBoardRepository missingBoardRepository;
    
    @Autowired
    private FreeBoardRepository freeRepository;
    
    @Autowired
    private VolunteerReviewRepository reviewRepository;
    
    @Transactional
    public List<MissingBoardList> getMissingBoardByMemberNum(Long memberNum) {
        return missingBoardRepository.findMissingBoardByMemberNum(memberNum);
//        List<MissingBoardDto> boards = new ArrayList<>();
//
//        for (Object[] result : results) {
//        	MissingBoardDto missing = new MissingBoardDto();
//            missing.setBoardNum((Long) result[0]);
//            missing.setBoardId((String) result[1]);
//            missing.setBoardSubject((String) result[2]);
//            missing.setBoardCount((Integer) result[3]);
//            //missing.setBoardDate((LocalDateTime) result[4]);
//            missing.setBoardStatus((Boolean) result[4]);
//            missing.setImgThumbnail((String) result[5]);
//            //missing.setMemberNickName((String) result[7]);
//            missing.setMemberNum((Long) result[6]);
//            boards.add(missing);
//        }

//        return boards;
    }
    
    @Transactional
    public List<FreeBoardList> getFreeByMemberNum(Long memberNum) {
    	return freeRepository.findFreeBoardByMemberNum(memberNum);
    }
    
    @Transactional
    public List<VolunteerReviewDto> getReviewByMemberNum(Long memberNum){
    	List<Object[]> results = reviewRepository.findReviewByMemberNum(memberNum);
    	List<VolunteerReviewDto> reviews = new ArrayList<>();

        for (Object[] result : results) {
        	VolunteerReviewDto review = new VolunteerReviewDto();
        	review.setBoardNum((Long) result[0]);
        	review.setBoardId((String) result[1]);
        	review.setReviewSubject((String) result[2]);
        	review.setReviewContent((String) result[3]);
        	review.setReviewCount((Integer) result[4]);
        	review.setReviewDate(((Timestamp) result[5]).toLocalDateTime());
        	review.setImgThumbnail((String) result[6]);
        	review.setMemberNum((Long) result[7]);
        	reviews.add(review);
        }

        return reviews;
    }
    

}
