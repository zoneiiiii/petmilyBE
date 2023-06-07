package com.petmily.backend.admin.board.free;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petmily.backend.community.free.board.FreeBoard;

@Repository
public interface AdminFreeBoardRepository extends JpaRepository<FreeBoard, Long> {

	// 게시글검색
		@Query(value="select b.boardNum, m.memberNum, m.memberId, "
				+ "b.boardId, b.freeSubject as subject, b.freeContent as content, "
				+ "b.freeCount as count, b.freeDate as postDate "
				+ "from freeBoard b join member m on b.memberNum = m.memberNum", nativeQuery=true)
		Page<AdminFreeBoard> findAllAdminFreeBoard(Pageable pageable);
		
		Page<AdminFreeBoard> findByFreeSubjectContaining(@Param("keyword")String keyword, Pageable pageable);
		@Query(value = "select b.boardNum, m.memberNum, m.memberId, "
				+ "b.boardId, b.freeSubject as subject, b.freeContent as content, "
				+ "b.freeCount as count, b.freeDate as postDate "
				+ "from freeBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
				countQuery = "select b.boardNum, m.memberNum, m.memberId, "
						+ "b.boardId, b.freeSubject as subject, b.freeContent as content, "
						+ "b.freeCount as count, b.freeDate as postDate"
						+ "from freeBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword", 
				nativeQuery = true)
		Page<AdminFreeBoard> findByFreeContentContaining(@Param("keyword")String keyword, Pageable pageable);	
		@Query(value = "select b.boardNum, m.memberNum, m.memberId, "
				+ "b.boardId, b.freeSubject as subject, b.freeContent as content, "
				+ "b.freeCount as count, b.freeDate as postDate "
				+ "from freeBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
				countQuery = "select b.boardNum, m.memberNum, m.memberId, "
						+ "b.boardId, b.freeSubject as subject, b.freeContent as content, "
						+ "b.freeCount as count, b.freeDate as postDate "
						+ "from freeBoard b join member m on b.memberNum = m.memberNum WHERE REGEXP_REPLACE(REGEXP_REPLACE(content, CONCAT('&[^', cast(CHAR(59) as char), ']+', cast(CHAR(59) as char)), ''), '<[^>]+>', '') REGEXP :keyword OR subject REGEXP :keyword", 
				nativeQuery = true)
		Page<AdminFreeBoard>findByFreeSubjectContainingOrFreeContentContaining(@Param("keyword")String keyword, Pageable pageable);
}