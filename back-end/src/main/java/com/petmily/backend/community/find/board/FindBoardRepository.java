package com.petmily.backend.community.find.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FindBoardRepository extends JpaRepository<FindBoard, Long> {

	// 게시글 전체 조회
	@Query(
			nativeQuery=true,
			value="SELECT fb.boardNum, fb.boardId, fb.boardSubject, fb.boardCount, fb.boardDate, fb.imgThumbnail, m.memberNickname "
					+ "FROM findboard fb "
					+ "JOIN member m ON fb.memberNum = m.memberNum")
	List<FindBoardList> getFindBoards();
	
	// 게시글 조회
	@Query(
			nativeQuery=true,
			value="SELECT fb.*, m.memberNickName, m.memberImg "
			+ "FROM findboard fb "
			+ "JOIN member m ON fb.memberNum = m.memberNum WHERE fb.boardNum = :boardNum")
	FindBoardDetail findFindBoardDetail(@Param("boardNum") Long boardNum);
	
	// 조회수 증가
    @Modifying
    @Query(
    		nativeQuery=true,
    		value="UPDATE findBoard fb SET fb.boardCount = fb.boardCount + 1 WHERE fb.boardNum = :boardNum")
    void updateBoardCount(Long boardNum);
    
  //마이페이지 쓴 글 목록(목격제보게시판)
    @Query(
    		nativeQuery=true,
    		value="SELECT fb.boardNum, fb.boardId, fb.boardSubject, fb.boardCount, fb.boardDate, fb.imgThumbnail, m.memberNickname " +
            "FROM findboard fb " +
            "JOIN member m ON fb.memberNum = m.memberNum " +
            "WHERE m.memberNum = :memberNum")
    Page<FindBoardList> findFindBoardByMemberNum(@Param("memberNum") Long memberNum, Pageable pageable);

	long count();
}
