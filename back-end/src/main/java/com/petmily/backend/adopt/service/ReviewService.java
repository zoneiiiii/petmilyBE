package com.petmily.backend.adopt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmily.backend.adopt.repository.ReviewRepository;
import com.petmily.backend.adopt.dto.ReviewDto;

import jakarta.transaction.Transactional;


@Service
public class ReviewService {
	@Autowired
	private final ReviewRepository repository;

	public ReviewService(ReviewRepository repository){
        this.repository = repository;
     
    }
	
	 @Transactional
	    public Long savePost(ReviewDto reviewDto) {
	        return repository.save(reviewDto.toEntity()).getBoardNum();
	    }

}
