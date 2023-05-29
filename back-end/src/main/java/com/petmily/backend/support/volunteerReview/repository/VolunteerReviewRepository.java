package com.petmily.backend.support.volunteerReview.repository;

import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petmily.backend.support.volunteerReview.domain.VolunteerReview;

@Repository
public interface VolunteerReviewRepository extends JpaRepository<VolunteerReview, Long> {
    @NotNull
    Page<VolunteerReview> findAll(@NotNull Pageable pageable);
}
