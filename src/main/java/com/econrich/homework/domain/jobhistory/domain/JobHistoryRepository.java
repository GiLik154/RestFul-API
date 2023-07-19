package com.econrich.homework.domain.jobhistory.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryId> {
    List<JobHistory> findByEmployeeId(Long employeeId);
}
