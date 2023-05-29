package com.petmily.backend.pet;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="pet")
public class Pet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petNum;

    @Column
    private Long memberNum;
    
    @Column
    private String petName;
    
    @Column
    private String petAge;
    
    @Column
    private String petImg;
    
    @Column
    private String petSpecies;
    
    @Column
    private String shelterName;
    
    @Column
    private String shelterTel;
    
    @Column
    private String shelterAddr;

    @Column
    private LocalDateTime shelterDate;

}
