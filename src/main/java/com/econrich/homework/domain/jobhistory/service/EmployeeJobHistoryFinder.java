package com.econrich.homework.domain.jobhistory.service;

import com.econrich.homework.domain.jobhistory.domain.JobHistory;
import com.econrich.homework.exception.NotFoundEmployeeException;

import java.util.List;

public interface EmployeeJobHistoryFinder {

    /**
     * 직원 ID를 기반으로 특정 직원의 직무 이력을 조회합니다.
     *
     * @param employeeId 조회할 직원의 고유 식별자 (ID)
     * @return 직무 이력 목록 (JobHistory 객체의 리스트)
     * @throws NotFoundEmployeeException 직원의 직무 이력을 찾을 수 없는 경우 발생하는 예외
     */
    List<JobHistory> findByEmployeeId(Long employeeId);
}
