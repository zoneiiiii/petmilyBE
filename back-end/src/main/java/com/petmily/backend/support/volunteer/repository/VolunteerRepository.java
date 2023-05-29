package com.petmily.backend.support.volunteer.repository;

import com.petmily.backend.support.volunteer.domain.Volunteer;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    @NotNull
    Page<Volunteer> findAll(@NotNull Pageable pageable);
}
