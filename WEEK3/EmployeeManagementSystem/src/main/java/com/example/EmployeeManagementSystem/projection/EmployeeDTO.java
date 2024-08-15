package com.example.employeemanagementsystem.projection;

public class EmployeeDTO {
    private Long employeeId; 
    private String fullName; 
    private String emailAddress; 

    public EmployeeDTO(Long employeeId, String fullName, String emailAddress) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
    }

    // Getters and setters
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
