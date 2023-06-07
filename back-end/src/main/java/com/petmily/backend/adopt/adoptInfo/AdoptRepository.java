package com.petmily.backend.adopt.adoptInfo;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AdoptRepository extends JpaRepository<Adopt, Long> {
	Page<Adopt> findAllByOrderByAdoptDateDesc(Pageable pageable);
	
	Long countByAdoptState(String adoptState);
	
	Adopt findByAdoptNum(Long adoptNum);

	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Adopt a SET a.adoptState = :adoptState, a.approvedDate = :approvedDate WHERE a.adoptNum = :adoptNum")
	void updateAdopt(Long adoptNum,@Param("approvedDate") LocalDateTime approvedDate,String adoptState);




}
