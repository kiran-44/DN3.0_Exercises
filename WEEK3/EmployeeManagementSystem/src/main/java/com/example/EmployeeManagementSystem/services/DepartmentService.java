package com.example.employeemanagementsystem.services;

import com.example.employeemanagementsystem.model.Dept;
import com.example.employeemanagementsystem.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeptService {

    @Autowired
    private DeptRepository deptRepository;

    public List<Dept> getAllDepartments() {
        return deptRepository.findAll();
    }

    public Optional<Dept> getDepartmentById(Long id) {
        return deptRepository.findById(id);
    }

    public Dept saveDepartment(Dept department) {
        return deptRepository.save(department);
    }

    public Dept updateDepartment(Long id, Dept departmentDetails) {
        Dept department = deptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

       
        department.setDeptName(departmentDetails.getDeptName());

        return deptRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        deptRepository.deleteById(id);
    }
}
