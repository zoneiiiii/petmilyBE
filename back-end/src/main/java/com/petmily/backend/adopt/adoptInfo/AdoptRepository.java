package com.petmily.backend.adopt.adoptInfo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petmily.backend.adopt.adoptReview.ReviewBoard;
import com.petmily.backend.adopt.adoptReview.ReviewBoardList;


@Repository
public interface AdoptRepository extends JpaRepository<Adopt, Long> {
	List<Adopt> findAllByOrderByAdoptDateDesc();
	
	Long countByAdoptState(String adoptState);
	
	Adopt findByAdoptNum(Long adoptNum);

	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Adopt a SET a.adoptState = :adoptState, a.approvedDate = :approvedDate WHERE a.adoptNum = :adoptNum")
	void updateAdopt(Long adoptNum, LocalDateTime approvedDate,String adoptState);




}
