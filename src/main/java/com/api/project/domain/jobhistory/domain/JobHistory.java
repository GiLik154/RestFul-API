package com.api.project.domain.jobhistory.domain;

import com.api.project.domain.department.domain.Department;
import com.api.project.domain.employee.domain.Employee;
import com.api.project.domain.job.domain.Job;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job_history")
@Getter
public class JobHistory {
    @EmbeddedId
    private JobHistoryId id;

    @MapsId("employeeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", columnDefinition = "int")
    private Employee employee;

    @MapsId("jobId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    protected JobHistory() {}
}
