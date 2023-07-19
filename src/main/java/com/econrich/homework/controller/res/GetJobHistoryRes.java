package com.econrich.homework.controller.res;

import com.econrich.homework.domain.jobhistory.domain.JobHistory;
import lombok.Getter;

import java.util.Date;

@Getter
public class GetJobHistoryRes {
    private final Long employeeId;
    private final Date startDate;
    private final Date endDate;
    private final String jobId;
    private final Long departmentId;

    public GetJobHistoryRes(JobHistory jobHistory) {
        this.employeeId = jobHistory.getEmployee().getId();
        this.startDate = jobHistory.getStartDate();
        this.endDate = jobHistory.getEndDate();
        this.jobId = jobHistory.getJob().getJobId();
        this.departmentId = jobHistory.getDepartment().getId();
    }
}