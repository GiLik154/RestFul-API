package com.api.project.domain.employee.service;

import com.api.project.domain.employee.domain.Employee;
import com.api.project.exception.NotFoundEmployeeException;

public interface EmployeeProfileFinder {

    /**
     * 직원 ID를 기반으로 특정 직원 정보를 조회합니다.
     *
     * @param id 조회할 직원의 고유 식별자 (ID)
     * @return 직원 정보 (Employee 객체)
     * @throws NotFoundEmployeeException 직원을 찾을 수 없는 경우 발생하는 예외
     */
    Employee findById(Long id);
}
