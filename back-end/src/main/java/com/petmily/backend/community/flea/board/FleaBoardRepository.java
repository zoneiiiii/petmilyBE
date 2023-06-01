package com.petmily.backend.community.flea.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FleaBoardRepository extends JpaRepository<FleaBoard, Long> {

	// 게시글 전체 조회
	@Query(
			nativeQuery=true,
			value="SELECT fb.boardNum, fb.boardId, fb.boardSubject, fb.boardCost, fb.boardCount, fb.imgThumbnail, fb.boardStatus, m.memberNickname "
					+ "FROM fleaboard fb "
					+ "JOIN member m ON fb.memberNum = m.memberNum")
	List<FleaBoardList> getFleaBoards();
	
	// 게시글 조회
	@Query(
			nativeQuery=true,
			value="SELECT fb.*, m.memberNickName, m.memberImg "
			+ "FROM fleaboard fb "
			+ "JOIN member m ON fb.memberNum = m.memberNum WHERE fb.boardNum = :boardNum")
	FleaBoardDetail findFleaBoardDetail(@Param("boardNum") Long boardNum);
	
	// 조회수 증가
	@Modifying
    @Query(
    		nativeQuery=true,
    		value="UPDATE FleaBoard fb SET fb.boardCount = fb.boardCount + 1 WHERE fb.boardNum = :boardNum")
    void updateBoardCount(Long boardNum);
	
	//마이페이지 쓴 글 목록(매매장터)
    @Query(
    		nativeQuery=true,
    		value="SELECT fb.boardNum, fb.boardId, fb.boardCount, fb.boardDate, fb.boardSubject, fb.boardCost, fb.boardCategory, fb.boardStatus,fb.imgThumbnail, m.memberNickname " +
            "FROM fleaboard fb " +
            "JOIN member m ON fb.memberNum = m.memberNum " +
            "WHERE m.memberNum = :memberNum ORDER BY fb.boardDate DESC")
    List<FleaBoardList> findFleaBoardByMemberNum(@Param("memberNum") Long memberNum);
}
