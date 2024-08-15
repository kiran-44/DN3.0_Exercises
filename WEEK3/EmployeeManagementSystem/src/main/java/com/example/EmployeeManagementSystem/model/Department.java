package com.example.employeemanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "departments") // Table name remains the same
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId; // Renamed from id

    private String departmentName; // Renamed from name

    @OneToMany(mappedBy = "department") // Relationship mapping
    private List<Employee> employees; // Field name remains employees

    @CreatedDate
    @Column(name = "creation_timestamp", updatable = false) // Renamed column
    private LocalDateTime creationTimestamp; // Renamed from createdDate

    @LastModifiedDate
    @Column(name = "modification_timestamp") // Renamed column
    private LocalDateTime modificationTimestamp; // Renamed from lastModifiedDate

    @CreatedBy
    @Column(name = "creator_user") // Renamed column
    private String creatorUser; // Renamed from createdBy

    @LastModifiedBy
    @Column(name = "modifier_user") // Renamed column
    private String modifierUser; // Renamed from lastModifiedBy
}
