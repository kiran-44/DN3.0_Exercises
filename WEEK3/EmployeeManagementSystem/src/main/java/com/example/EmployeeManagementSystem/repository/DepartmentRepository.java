package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Department;
import com.example.employeemanagementsystem.projection.DeptDTO;
import com.example.employeemanagementsystem.projection.DeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeptRepository extends JpaRepository<Department, Long> {

   
    List<Department> findDepartmentsByTitle(String title);

   
    @Query("SELECT d FROM Department d WHERE d.name = :title")
    List<Department> searchDepartmentsByTitle(@Param("title") String title);

    
    List<DeptProjection> findDepartmentsByTitleProjection(String title);

   
    @Query("SELECT new com.example.employeemanagementsystem.projection.DeptDTO(d.staffId, d.departmentName) " +
           "FROM Department d")
    List<DeptDTO> fetchAllDepartmentDTOs();
}
