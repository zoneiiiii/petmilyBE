package com.petmily.backend.adopt.domain;



import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="reviewboard")
public class Review {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long boardNum;

	    @Column(nullable = false)
	    private Long memberNum;
	    
	    @Column(nullable = false)
	    private String boardId;
	    
	    @Column
	    private String reviewSubject;
	    
	    @Column
	    private String reviewContent;
	    
	    @Column
	    private String imgThumbnail;
	    
	    @Column
	    private int reviewCount;

	    @Column
	    private LocalDateTime reviewDate;
	
	    
	    @Builder
	    public Review(Long boardNum, Long memberNum, String boardId, String reviewSubject, String reviewContent, String imgThumbnail) {
	        this.boardNum = boardNum;
	        this.memberNum = memberNum;
	        this.boardId = boardId;
	        this.reviewSubject = reviewSubject;
	        this.reviewContent = reviewContent;
	        this.imgThumbnail = imgThumbnail;
	    }
	    
	   

	

	


	   


}
