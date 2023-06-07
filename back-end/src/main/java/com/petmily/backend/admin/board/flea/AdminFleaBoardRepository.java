package com.petmily.backend.admin.board.flea;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petmily.backend.community.flea.board.FleaBoard;

@Repository
public interface AdminFleaBoardRepository extends JpaRepository<FleaBoard, Long> {

	// 게시글검색
	@Query(value="select b.boardNum, m.memberNum, m.memberId, "
			+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
			+ "b.boardCount as count, b.boardDate as postDate "
			+ "from fleaBoard b join member m on b.memberNum = m.memberNum", nativeQuery=true)
	Page<AdminFleaBoard> findAllAdminFleaBoard(Pageable pageable);
	
	Page<AdminFleaBoard> findByBoardSubjectContaining(@Param("keyword")String keyword, Pageable pageable);
	@Query(value = "select b.boardNum, m.memberNum, m.memberId, "
			+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
			+ "b.boardCount as count, b.boardDate as postDate "
			+ "from fleaBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			countQuery = "select b.boardNum, m.memberNum, m.memberId, "
					+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
					+ "b.boardCount as count, b.boardDate as postDate"
					+ "from fleaBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
			nativeQuery = true)
	Page<AdminFleaBoard> findByBoardContentContaining(@Param("keyword")String keyword, Pageable pageable);	
	@Query(value = "select b.boardNum, m.memberNum, m.memberId, "
			+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
			+ "b.boardCount as count, b.boardDate as postDate "
			+ "from fleaBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
			countQuery = "select b.boardNum, m.memberNum, m.memberId, "
					+ "b.boardId, b.boardSubject as subject, b.boardContent as content, "
					+ "b.boardCount as count, b.boardDate as postDate "
					+ "from fleaBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
			nativeQuery = true)
	Page<AdminFleaBoard>findByBoardSubjectContainingOrBoardContentContaining(@Param("keyword")String keyword, Pageable pageable);
}
