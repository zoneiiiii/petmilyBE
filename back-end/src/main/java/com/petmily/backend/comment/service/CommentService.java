package com.petmily.backend.comment.service;

import com.petmily.backend.adopt.adoptReview.ReviewRepository;
import com.petmily.backend.comment.domain.Comment;
import com.petmily.backend.comment.dto.CommentDto;
import com.petmily.backend.comment.repository.CommentRepository;
import com.petmily.backend.community.find.board.FindBoardRepository;
import com.petmily.backend.community.flea.board.FleaBoardRepository;
import com.petmily.backend.community.free.board.FreeBoardRepository;
import com.petmily.backend.community.missing.board.MissingBoardRepository;
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
    private final MissingBoardRepository missingBoardRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final FindBoardRepository findBoardRepository;
    private final FleaBoardRepository fleaBoardRepository;
    private final ReviewRepository reviewRepository;


    public CommentService(CommentRepository commentRepository, MemberService memberService,
    VolunteerRepository volunteerRepository, VolunteerReviewRepository volunteerReviewRepository,
    MissingBoardRepository missingBoardRepository, FleaBoardRepository fleaBoardRepository,
    FreeBoardRepository freeBoardRepository, ReviewRepository reviewRepository, FindBoardRepository findBoardRepository){
        this.commentRepository = commentRepository;
        this.memberService = memberService;
        this.volunteerRepository = volunteerRepository;
        this.volunteerReviewRepository = volunteerReviewRepository;
        this.missingBoardRepository = missingBoardRepository;
        this.findBoardRepository = findBoardRepository;
        this.fleaBoardRepository = fleaBoardRepository;
        this.freeBoardRepository = freeBoardRepository;
        this.reviewRepository = reviewRepository;

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
            Long originalCommenterId = null; //원 댓글 작성자 Num
            String originalCommenter = null;

            switch (commentDto.getBoardId()){
                case "volunteer":
                    postCreatorId = volunteerRepository.findById(commentDto.getBoardNum()).orElseThrow().getMemberNum();
                    postCreator = memberService.getMemberByNum(postCreatorId).getMemberId();
                    break;
                case "volunteerReview":
                    postCreatorId = volunteerReviewRepository.findById(commentDto.getBoardNum()).orElseThrow().getMemberNum();
                    postCreator = memberService.getMemberByNum(postCreatorId).getMemberId();
                    break;
                case "missing":
                    postCreatorId = missingBoardRepository.findById(commentDto.getBoardNum()).orElseThrow().getMemberNum();
                    postCreator = memberService.getMemberByNum(postCreatorId).getMemberId();
                    break;
                case "find":
                    postCreatorId = findBoardRepository.findById(commentDto.getBoardNum()).orElseThrow().getMemberNum();
                    postCreator = memberService.getMemberByNum(postCreatorId).getMemberId();
                    break;
                case "free":
                    postCreatorId = freeBoardRepository.findById(commentDto.getBoardNum()).orElseThrow().getMemberNum();
                    postCreator = memberService.getMemberByNum(postCreatorId).getMemberId();
                    break;
                case "flea":
                    postCreatorId = fleaBoardRepository.findById(commentDto.getBoardNum()).orElseThrow().getMemberNum();
                    postCreator = memberService.getMemberByNum(postCreatorId).getMemberId();
                    break;
                case "review":
                    postCreatorId = reviewRepository.findById(commentDto.getBoardNum()).orElseThrow().getMemberNum();
                    postCreator = memberService.getMemberByNum(postCreatorId).getMemberId();
                    break;
            }

            if(comment.getCommentPnum() != null) {
                originalCommenterId = commentRepository.findById(comment.getCommentPnum()).orElseThrow().getMember().getMemberNum();
                originalCommenter = memberService.getMemberByNum(originalCommenterId).getMemberId();
            }

            if(loggedInUserId == null
                    || (!loggedInUserId.equals(postCreator)
                    && !loggedInUserId.equals(comment.getMember().getMemberId())
                    && (originalCommenter == null || !loggedInUserId.equals(originalCommenter)))) {
                commentDto.setCommentContent("비밀 댓글입니다.");
            }
        }
        // 비밀댓글 처리 Dto 설정 끝-->

        return commentDto;
    }
}
