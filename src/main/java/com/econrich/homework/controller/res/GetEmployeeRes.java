package com.econrich.homework.controller.res;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class GetEmployeeRes {
    private final Long employeeId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final Date hireDate;
    private final String jobId;
    private final BigDecimal salary;
    private final BigDecimal commissionPct;
    private final Long managerId;
    private final Long departmentId;


    public GetEmployeeRes(Long employeeId, String firstName, String lastName, String email, String phoneNumber, Date hireDate, String jobId, BigDecimal salary, BigDecimal commissionPct, Long managerId, Long departmentId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.managerId = managerId;
        this.departmentId = departmentId;
    }
}
