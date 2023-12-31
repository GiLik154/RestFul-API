package com.api.project.domain.jobhistory.service;

import com.api.project.domain.jobhistory.domain.JobHistory;
import com.api.project.domain.jobhistory.domain.JobHistoryRepository;
import com.api.project.exception.NotFoundEmployeeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobHistoryService implements EmployeeJobHistoryFinder {
    private final JobHistoryRepository repository;

    @Override
    public List<JobHistory> findByEmployeeId(Long employeeId) {
        List<JobHistory> jobHistories = repository.findByEmployeeId(employeeId);

        valid(jobHistories);

        return jobHistories;
    }

    private void valid(List<JobHistory> jobHistories){
        if (jobHistories.isEmpty()) throw new NotFoundEmployeeException("Not found employee by ID");
    }
}