package com.petmily.backend.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmily.backend.adopt.adoptReview.ReviewBoard;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	
	Pet findByPetName(String petName);
	
	void deleteAllByPetName(String petName);

}
