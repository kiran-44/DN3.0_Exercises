package com.example.employeemanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff") 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId; 

    private String fullName; 

    private String emailAddress; 

    @ManyToOne
    @JoinColumn(name = "department_id") 
    private Department department;

    @CreatedDate
    @Column(name = "creation_time", updatable = false) 
    private LocalDateTime creationTime;

    @LastModifiedDate
    @Column(name = "modification_time") 
    private LocalDateTime modificationTime;

    @CreatedBy
    @Column(name = "creator_name") 
    private String creatorName;

    @LastModifiedBy
    @Column(name = "modifier_name") 
    private String modifierName;
}
