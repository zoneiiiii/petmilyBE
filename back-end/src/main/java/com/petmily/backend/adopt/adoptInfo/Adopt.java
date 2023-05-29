package com.petmily.backend.adopt.adoptInfo;



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
@Table(name="adopt")
public class Adopt {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long adoptNum;

	    @Column
	    private Long memberNum;
	
	    @Column
	    private String petName;
	    
	    @Column
	    private String petImg;
	    
	    @Column
	    private String adopterName;
	    
	    @Column
	    private String adopterBirth;
	    
	    @Column
	    private String adopterTel;
	    
	    @Column
	    private String adopterAddr;
	    
	    @Column
	    private String adopterEmail;

	    @Column
	    private LocalDateTime adoptDate;
	    
	    @Column
	    private String adoptState;
	
	    

	    
	   

	

	


	   


}
