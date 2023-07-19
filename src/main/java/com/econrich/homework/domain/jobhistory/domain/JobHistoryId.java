package com.econrich.homework.domain.jobhistory.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class JobHistoryId implements Serializable {
    @Column(name = "employee_id")
    protected Long employeeId;

    @Column(name = "job_id")
    protected String jobId;

    protected JobHistoryId() {}
}
