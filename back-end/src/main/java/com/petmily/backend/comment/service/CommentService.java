package com.petmily.backend.comment.service;

import com.petmily.backend.comment.domain.Comment;
import com.petmily.backend.comment.dto.CommentDto;
import com.petmily.backend.comment.repository.CommentRepository;
import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.support.volunteer.repository.VolunteerRepository;
import com.petmily.backend.support.volunteerReview.repository.VolunteerReviewRepository;
import org.springframework.data.domain.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final VolunteerRepository volunteerRepository;
    private final VolunteerReviewRepository volunteerReviewRepository;

    public CommentService(CommentRepository commentRepository, MemberService memberService,
    VolunteerRepository volunteerRepository, VolunteerReviewRepository volunteerReviewRepository){
        this.commentRepository = commentRepository;
        this.memberService = memberService;
        this.volunteerRepository = volunteerRepository;
        this.volunteerReviewRepository = volunteerReviewRepository;
        //TODO: 게시판 생성될 때마다 Repository 생성자 주입 필요
    }

public List<CommentDto> getCommentsByPost(String boardId, Long boardNum, String loggedInUserId) {
    List<Comment> comments = commentRepository.findByBoardIdAndBoardNumOrderByCommentNumAsc(boardId, boardNum);

    return comments.stream().map(comment -> convertToDto(comment, loggedInUserId)).collect(Collectors.toList());
}

//    public Page<CommentDto> getCommentsByPost(String boardId, Long boardNum, String loggedInUserId, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Comment> comments = commentRepository.findByBoardIdAndBoardNumOrderByCommentNumAsc(boardId, boardNum, pageable);
//
//        return comments.map(comment -> convertToDto(comment, loggedInUserId));
//    }

    public CommentDto createComment(CommentDto commentDto, String loggedInUserId, Long commentPnum){
        Member member = memberService.getMember(loggedInUserId);
        Comment comment = Comment.builder()
                .member(member)
                .boardId(commentDto.getBoardId())
                .boardNum(commentDto.getBoardNum())
                .commentContent(commentDto.getCommentContent())
                .commentIsSecret(commentDto.getCommentIsSecret())
                .commentPnum(commentPnum)
                .build();

        commentRepository.save(comment);

        return convertToDto(comment,loggedInUserId);

    }

    public void deleteCommentById(Long commentNum, String loggedInUserId){
        Member member = memberService.getMember(loggedInUserId);
        Comment comment = commentRepository.findById(commentNum)
                .orElseThrow(() -> new NoSuchElementException("해당 commentNum을 찾을 수 없습니다." + commentNum));
        if (!member.getMemberNum().equals(comment.getMember().getMemberNum())){
            throw new AccessDeniedException("해당 댓글을 삭제할 권한이 없습니다.");
        }
        commentRepository.delete(comment);
    }

    public CommentDto updateComment(Long commentNum, CommentDto commentDto, String loggedInUserId){
        Member member = memberService.getMember(loggedInUserId);
        Comment comment = commentRepository.findById(commentNum)
                .orElseThrow(()->new NoSuchElementException("해당 commentNum을 찾을 수 없습니다." + commentNum));
        if(!member.getMemberNum().equals(comment.getMember().getMemberNum())){
            throw new AccessDeniedException("해당 댓글을 수정할 권한이 없습니다.");
        }
        comment.updateCommentContent(commentDto.getCommentContent());
        comment.updateCommentIsSecret(commentDto.getCommentIsSecret());

        commentRepository.save(comment);

        return convertToDto(comment, loggedInUserId);

    }

    public CommentDto convertToDto(Comment comment, String loggedInUserId){
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentNum(comment.getCommentNum());
        commentDto.setMemberNum(comment.getMember().getMemberNum());
        commentDto.setBoardId(comment.getBoardId());
        commentDto.setBoardNum(comment.getBoardNum());
        commentDto.setCommentPnum(comment.getCommentPnum());
        commentDto.setCommentCreate(comment.getCommentCreate());
        commentDto.setCommentUpdate(comment.getCommentUpdate());
        commentDto.setCommentContent(comment.getCommentContent());
        commentDto.setCommentIsSecret(comment.getCommentIsSecret());
        //작성자의 이미지, 닉네임 데이터 가져오기
        commentDto.setMemberImg(comment.getMember().getMemberImg());
        commentDto.setMemberNickname(comment.getMember().getMemberNickname());

        //<-- 비밀댓글 처리 Dto 설정
        if (commentDto.getCommentIsSecret()) {
            Long postCreatorId = null; //게시글 작성자 Num
            String postCreator = null;
            //TODO: 게시판 생성될때마다 해당 게시판 boardID case 추가 필요
            switch (commentDto.getBoardId()){
                case "volunteer":
                    postCreatorId = volunteerRepository.findById(commentDto.getBoardNum()).orElseThrow().getMemberNum();
                    postCreator = memberService.getMemberByNum(postCreatorId).getMemberId();
                    break;
                case "volunteerReview":
                    postCreatorId = volunteerReviewRepository.findById(commentDto.getBoardNum()).orElseThrow().getMemberNum();
                    postCreator = memberService.getMemberByNum(postCreatorId).getMemberId();
                    break;
            }

            if(loggedInUserId == null
                    || (!loggedInUserId.equals(postCreator)
                    && !loggedInUserId.equals(comment.getMember().getMemberId()))) {
                commentDto.setCommentContent("비밀 댓글입니다.");
            }
        }
        // 비밀댓글 처리 Dto 설정 끝-->

        return commentDto;
    }
}
