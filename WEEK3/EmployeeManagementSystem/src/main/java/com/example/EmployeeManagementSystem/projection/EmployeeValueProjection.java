package com.example.employeemanagementsystem.projection;

import org.springframework.beans.factory.annotation.Value;

public interface StaffValueProjection {
    @Value("#{target.employeeId}")
    Long retrieveEmployeeId();

    @Value("#{target.fullName}")
    String retrieveFullName();

    @Value("#{target.emailAddress}")
    String retrieveEmailAddress();
}
