package com.petmily.backend.mypage.adoptlist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmily.backend.adopt.adoptInfo.Adopt;

@Repository
public interface AdoptListRepository extends JpaRepository<Adopt, Long> {
	
	List<Adopt> findAllByMemberNumAndAdoptStateNotOrderByAdoptDateAsc(Long memberNum, String adoptState);
}
