package com.econrich.homework.domain.department.service;

import com.econrich.homework.domain.department.domain.Department;
import com.econrich.homework.exception.NotFoundDepartmentException;

public interface DepartmentProfileFinder {

    /**
     * 부서 ID를 기반으로 특정 부서 정보를 조회합니다.
     *
     * @param id 조회할 부서의 고유 식별자 (ID)
     * @return 부서 정보 (Department 객체)
     * @throws NotFoundDepartmentException 부서를 찾을 수 없는 경우 발생하는 예외
     */
    Department findById(Long id);
}
