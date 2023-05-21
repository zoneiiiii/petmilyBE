package com.petmily.backend.support.volunteer.repository;

import com.petmily.backend.support.volunteer.domain.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
