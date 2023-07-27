package com.api.project.domain.employee.service;

import com.api.project.exception.NotFoundDepartmentException;

public interface EmployeeSalaryUpdater {

    /**
     * 특정 부서 직원들의 급여를 일괄적으로 업데이트합니다.
     *
     * @param departmentId 업데이트 대상 부서의 고유 식별자 (ID)
     * @param percent      급여 인상률을 백분율로 표현한 값
     * @throws NotFoundDepartmentException 부서를 찾을 수 없는 경우 발생하는 예외
     */
    void update(Long departmentId, int percent);
}
