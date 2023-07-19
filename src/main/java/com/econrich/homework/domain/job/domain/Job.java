package com.econrich.homework.domain.job.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "jobs")
@Getter
public class Job {
    @Id
    @Column(length = 10)
    private String jobId;

    @Column(nullable = false)
    private String jobTitle;

    private BigDecimal minSalary;

    private BigDecimal maxSalary;

    protected Job() {}
}
