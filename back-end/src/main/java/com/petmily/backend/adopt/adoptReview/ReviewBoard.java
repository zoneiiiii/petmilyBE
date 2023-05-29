package com.petmily.backend.adopt.adoptReview;



import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petmily.backend.member.login.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="reviewboard")
public class ReviewBoard {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long boardNum;

	    @Column(nullable = false)
	    private Long memberNum;
		
//		@ManyToOne(fetch = FetchType.EAGER)
//		@JoinColumn(name = "memberNum")
//		private Member member;
	    
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
	
	    
//	    @Builder
//	    public ReviewBoard(Long boardNum, Long memberNum, String boardId, String reviewSubject, String reviewContent, String imgThumbnail) {
//	        this.boardNum = boardNum;
//	        this.memberNum = memberNum;
//	        this.boardId = boardId;
//	        this.reviewSubject = reviewSubject;
//	        this.reviewContent = reviewContent;
//	        this.imgThumbnail = imgThumbnail;
//	    }
	    
	   

	

	


	   


}
