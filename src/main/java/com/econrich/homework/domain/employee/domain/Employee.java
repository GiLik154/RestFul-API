package com.econrich.homework.domain.employee.domain;

import com.econrich.homework.domain.department.domain.Department;
import com.econrich.homework.domain.job.domain.Job;
import com.econrich.homework.exception.InvalidPercentageException;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Entity
@Table(name = "employees")
@Getter
public class Employee {
    @Id
    @Column(name = "employee_id", columnDefinition = "int")
    private Long id;

    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private Date hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(nullable = false)
    private BigDecimal salary;

    private BigDecimal commissionPct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    protected Employee() {}

    public Employee(Long id, String lastName, String email, Date hireDate, BigDecimal salary) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public void updateSalary(BigDecimal percentage) {
        valid(percentage);
        this.salary = salary.multiply(percentage).setScale(2, RoundingMode.HALF_UP);
    }

    private void valid(BigDecimal percentage) {
        BigDecimal min = new BigDecimal(0);
        BigDecimal max = new BigDecimal(2);

        if (percentage.compareTo(min) < 0 || percentage.compareTo(max) > 0) {
            throw new InvalidPercentageException("Percentage must be between 0 and 2 (inclusive)");
        }
    }
}
