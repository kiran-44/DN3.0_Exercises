package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.model.StaffMember; 
import com.example.employeemanagementsystem.services.StaffMemberService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/staff") // Updated endpoint to /api/staff
public class StaffMemberController {

    @Autowired
    private StaffMemberService staffMemberService; // Updated to StaffMemberService

    @GetMapping
    public Page<StaffMember> getAllStaffMembers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "staffId") String sortByField,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return staffMemberService.getStaffMembers(pageNumber, pageSize, sortByField, sortOrder);
    }

    @GetMapping("/{id}")
    public StaffMember getStaffMemberById(@PathVariable Long id) {
        return staffMemberService.getStaffMemberById(id)
                .orElseThrow(() -> new RuntimeException("Staff member not found"));
    }

    @GetMapping("/email/{emailAddress}")
    public Page<StaffMember> getStaffMembersByEmail(
            @PathVariable String emailAddress,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "staffId") String sortByField,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return staffMemberService.getStaffMembersByEmail(emailAddress, pageNumber, pageSize, sortByField, sortOrder);
    }

    @GetMapping("/team/{teamId}")
    public Page<StaffMember> getStaffMembersByTeamId(
            @PathVariable Long teamId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "staffId") String sortByField,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return staffMemberService.getStaffMembersByTeamId(teamId, pageNumber, pageSize, sortByField, sortOrder);
    }

    @GetMapping("/team/name/{teamName}")
    public Page<StaffMember> getStaffMembersByTeamName(
            @PathVariable String teamName,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "staffId") String sortByField,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return staffMemberService.getStaffMembersByTeamName(teamName, pageNumber, pageSize, sortByField, sortOrder);
    }

    @PostMapping
    public StaffMember createStaffMember(@RequestBody StaffMember staffMember) {
        return staffMemberService.saveStaffMember(staffMember);
    }

    @PutMapping("/{id}")
    public StaffMember updateStaffMember(@PathVariable Long id, @RequestBody StaffMember staffMemberDetails) {
        return staffMemberService.updateStaffMember(id, staffMemberDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteStaffMember(@PathVariable Long id) {
        staffMemberService.deleteStaffMember(id);
    }
}
