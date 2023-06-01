package com.petmily.backend.community.missing.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingBoardRepository extends JpaRepository<MissingBoard, Long> {
	
	// 게시글 전체 조회
	@Query(
			nativeQuery=true,
			value="SELECT mb.boardNum, mb.boardId, mb.boardSubject, mb.boardCount, mb.boardDate, mb.boardStatus, mb.imgThumbnail, m.memberNickname "
					+ "FROM missingboard mb "
					+ "JOIN member m ON mb.memberNum = m.memberNum")
	List<MissingBoardList> getMissingBoards();
	
	// 게시글 조회
	@Query(
			nativeQuery=true,
			value="SELECT mb.*, m.memberNickName "
			+ "FROM missingboard mb "
			+ "JOIN member m ON mb.memberNum = m.memberNum WHERE mb.boardNum = :boardNum")
	MissingBoardDetail findMissingBoardDetail(@Param("boardNum") Long boardNum);
    
    // 게시글 수정
//    @Modifying
//    @Query("UPDATE MissingBoard m SET m.boardContent = :boardContent, m.imgThumbnail = :imgThumbnail WHERE m.boardNum = :boardNum AND m.memberNum = :memberNum")
//    void updateMissingBoard(Long boardNum, Long memberNum, String boardContent, String imgThumbnail);
    
    // 조회수 증가
    @Modifying
    @Query(
    		nativeQuery=true,
    		value="UPDATE MissingBoard mb SET mb.boardCount = mb.boardCount + 1 WHERE mb.boardNum = :boardNum")
    void updateBoardCount(Long boardNum);
    
    //마이페이지 쓴 글 목록(실종동물게시판)
    @Query(
    		nativeQuery=true,
    		value="SELECT b.boardNum, b.boardId, b.boardSubject, b.boardCount, b.boardDate, b.boardStatus,b.imgThumbnail, m.memberNickname " +
            "FROM missingboard b " +
            "JOIN member m ON b.memberNum = m.memberNum " +
            "WHERE m.memberNum = :memberNum")
    Page<MissingBoardList> findMissingBoardByMemberNum(@Param("memberNum") Long memberNum, Pageable pageable);
}