package com.petmily.backend.support.volunteerReview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;

@Repository
public interface VolunteerReviewRepository extends JpaRepository<VolunteerReview, Long> {
}
