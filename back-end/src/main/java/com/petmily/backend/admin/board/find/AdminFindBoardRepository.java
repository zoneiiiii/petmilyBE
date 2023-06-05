package com.petmily.backend.admin.board.find;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petmily.backend.community.find.board.FindBoard;

@Repository
public interface AdminFindBoardRepository extends JpaRepository<FindBoard, Long> {

	// 게시글검색
		@Query(value="select b.boardNum, m.memberNum, m.memberId, "
				+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
				+ "b.boardCount as count, b.boardDate as postDate "
				+ "from findBoard b join member m on b.memberNum = m.memberNum", nativeQuery=true)
		Page<AdminFindBoard> findAllAdminFindBoard(Pageable pageable);
		
		Page<AdminFindBoard> findByBoardSubjectContaining(@Param("keyword")String keyword, Pageable pageable);
		@Query(value = "select b.boardNum, m.memberNum, m.memberId, "
				+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
				+ "b.boardCount as count, b.boardDate as postDate "
				+ "from findBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
				countQuery = "select b.boardNum, m.memberNum, m.memberId, "
						+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
						+ "b.boardCount as count, b.boardDate as postDate"
						+ "from findBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
				nativeQuery = true)
		Page<AdminFindBoard> findByBoardContentContaining(@Param("keyword")String keyword, Pageable pageable);	
		@Query(value = "select b.boardNum, m.memberNum, m.memberId, "
				+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
				+ "b.boardCount as count, b.boardDate as postDate "
				+ "from findBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
				countQuery = "select b.boardNum, m.memberNum, m.memberId, "
						+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
						+ "b.boardCount as count, b.boardDate as postDate "
						+ "from findBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(boardContent, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
				nativeQuery = true)
		Page<AdminFindBoard>findByBoardSubjectContainingOrBoardContentContaining(@Param("keyword")String keyword, Pageable pageable);
}
