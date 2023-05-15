package com.petmily.backend.support.volunteerReview.repository;

import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerReviewRepository extends JpaRepository<VolunteerReview, Long> {
}
