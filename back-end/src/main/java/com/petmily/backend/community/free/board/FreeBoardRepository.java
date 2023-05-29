package com.petmily.backend.community.free.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

	// 게시글 전체 조회
	@Query(
			nativeQuery=true,
			value="SELECT f.boardNum, f.freeSubject, f.freeCount, f.freeDate, m.memberNickName "
					+ "FROM freeboard f "
					+ "JOIN member m ON f.memberNum = m.memberNum")
	List<FreeBoardList> getFreeBoards();
	
	// 게시글 조회(Read)
	@Query(
			nativeQuery=true,
			value="SELECT f.*, m.memberNickName "
			+ "FROM freeboard f "
			+ "JOIN member m ON f.memberNum = m.memberNum WHERE f.boardNum = :boardNum")
	FreeBoardDetail findFreeBoardDetail(@Param("boardNum") Long boardNum);
	
	// 조회수 증가
    @Modifying
    @Query(
    		nativeQuery=true,
    		value="UPDATE freeboard f SET f.freeCount = f.freeCount + 1 WHERE f.boardNum = :boardNum")
    void updateBoardCount(Long boardNum);
    
    // 게시글 생성
//    @Modifying
//    @Query(
//    		nativeQuery=true,
//    		value="insert into freeboard(boardId, freeSubject, freeContent, freeCount, freeDate, imgThumbnail, memberNum)"
//    				+ "values("
//    		)
    
	
}
