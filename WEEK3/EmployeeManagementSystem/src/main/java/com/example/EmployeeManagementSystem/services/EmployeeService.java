package com.example.employeemanagementsystem.services;

import com.example.employeemanagementsystem.model.Staff;
import com.example.employeemanagementsystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public Page<Staff> getStaff(int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return staffRepository.findAll(pageable);
    }

    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id, Staff staffDetails) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff member not found"));

        staff.setFullName(staffDetails.getFullName());
        staff.setEmailAddress(staffDetails.getEmailAddress());
        staff.setDepartment(staffDetails.getDepartment());

        return staffRepository.save(staff);
    }

    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    public Page<Staff> getStaffByEmail(String email, int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return staffRepository.findByEmailAddress(email, pageable);
    }

    public Page<Staff> getStaffByDepartmentId(Long departmentId, int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return staffRepository.findStaffByDepartmentId(departmentId, pageable);
    }

    public Page<Staff> getStaffByDepartmentName(String departmentName, int page, int size, String sortBy, String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        return staffRepository.findStaffByDepartmentName(departmentName, pageable);
    }
}
