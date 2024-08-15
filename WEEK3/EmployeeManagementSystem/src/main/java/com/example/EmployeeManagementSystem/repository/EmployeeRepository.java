package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Staff;
import com.example.employeemanagementsystem.projection.StaffDTO;
import com.example.employeemanagementsystem.projection.StaffProjection;
import com.example.employeemanagementsystem.projection.StaffValueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    
    Page<Staff> findByEmailAddress(String emailAddress, Pageable pageable);

    
    @Query("SELECT s FROM Staff s WHERE s.department.staffId = :deptId")
    Page<Staff> findStaffByDepartmentId(@Param("deptId") Long departmentId, Pageable pageable);

    
    @Query(value = "SELECT * FROM staff s JOIN departments d ON s.department_id = d.id WHERE d.department_name = :deptTitle", nativeQuery = true)
    Page<Staff> findStaffByDepartmentTitle(@Param("deptTitle") String departmentName, Pageable pageable);

    
    List<StaffProjection> findProjectionsByDepartmentTitle(String departmentName);

    
    @Query("SELECT new com.example.employeemanagementsystem.projection.StaffDTO(s.staffId, s.fullName, s.emailAddress) " +
            "FROM Staff s WHERE s.department.name = :deptTitle")
    List<StaffDTO> findDTOsByDepartmentTitle(@Param("deptTitle") String departmentName);

    
    List<StaffValueProjection> findValueProjectionsByDepartmentTitle(String departmentName);
}
