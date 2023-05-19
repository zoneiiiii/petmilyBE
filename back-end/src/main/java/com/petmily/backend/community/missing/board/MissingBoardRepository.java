package com.petmily.backend.community.missing.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingBoardRepository extends JpaRepository<MissingBoard, Long> {
	// 게시글 전체 조회(Read)
//	List<MissingBoard> findAllByOrderByBoardNumDesc(); 
	@Query(
			nativeQuery=true,
			value="SELECT mb.boardNum, mb.boardId, mb.boardSubject, mb.boardCount, mb.boardDate, mb.boardStatus, mb.imgThumbnail, m.memberNickname "
					+ "FROM missingboard mb "
					+ "JOIN member m ON mb.memberNum = m.memberNum")
	List<MissingBoardList> getMissingBoards();
	
	// 게시글 조회(Read)
//    MissingBoard findByBoardNum(Long boardNum);
	@Query(
			nativeQuery=true,
			value="SELECT mb.*, m.memberNickName "
			+ "FROM missingboard mb "
			+ "JOIN member m ON mb.memberNum = m.memberNum WHERE mb.boardNum = :boardNum")
	MissingBoardDetail findMissingBoardDetail(@Param("boardNum") Long boardNum);
	
    
    // 게시글 조회(Read)
    MissingBoard findByBoardId(String boardId); 
    
    MissingBoard findByBoardNumAndMemberNum(Long boardNum, Long memberNum);
    
    // 게시글 삭제(Delete)
    void deleteByBoardNum(Long boardNum); 
    
    // 게시글 생성
    MissingBoard save(MissingBoard missingBoard);
//    @Modifying
//    @Query("INSERT INTO MissingBoard(memberNum, boardId, boardSubject, boardContent, boardCount, boardDate, boardStatus, boardLocation, boardSpecies, boardAge, boardGender, imgThumbnail) "
//    		+ "VALUES :#{#missingBoard.toInsertQuery()}")
//    void createMissingBoard(@Param("missingBoard") MissingBoard missingBoard);	
    
    // 게시글 수정
    @Modifying
    @Query("UPDATE MissingBoard m SET m.boardContent = :boardContent, m.imgThumbnail = :imgThumbnail WHERE m.boardNum = :boardNum AND m.memberNum = :memberNum")
    void updateMissingBoard(Long boardNum, Long memberNum, String boardContent, String imgThumbnail);
    
    // 조회수 증가
    @Modifying
    @Query("UPDATE MissingBoard m SET m.boardCount = m.boardCount + 1 WHERE m.boardNum = :boardNum")
    void updateBoardCount(Long boardNum);	
}
