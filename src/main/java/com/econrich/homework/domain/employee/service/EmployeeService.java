package com.econrich.homework.domain.employee.service;

import com.econrich.homework.domain.department.domain.DepartmentRepository;
import com.econrich.homework.domain.employee.domain.Employee;
import com.econrich.homework.domain.employee.domain.EmployeeRepository;
import com.econrich.homework.exception.NotFoundDepartmentException;
import com.econrich.homework.exception.NotFoundEmployeeException;
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