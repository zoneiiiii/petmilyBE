package com.petmily.backend.adopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmily.backend.adopt.domain.Review;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
