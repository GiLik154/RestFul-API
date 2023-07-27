package com.api.project.domain.employee.service;

import com.api.project.domain.department.domain.DepartmentRepository;
import com.api.project.domain.employee.domain.Employee;
import com.api.project.domain.employee.domain.EmployeeRepository;
import com.api.project.exception.NotFoundDepartmentException;
import com.api.project.exception.NotFoundEmployeeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService implements EmployeeProfileFinder, EmployeeSalaryUpdater {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new NotFoundEmployeeException("Not found employee by ID"));
    }

    @Override
    public void update(Long departmentId, int percent) {
        valid(departmentId);

        BigDecimal baseValue = BigDecimal.ONE;
        BigDecimal percentage = BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100));

        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);

        employees.forEach(employee -> employee.updateSalary(baseValue.add(percentage)));
    }

    private void valid(Long departmentId){
        if(!departmentRepository.existsById(departmentId)) throw new NotFoundDepartmentException("Not found department by id");
    }
}