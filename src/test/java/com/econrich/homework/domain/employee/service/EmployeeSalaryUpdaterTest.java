package com.econrich.homework.domain.employee.service;

import com.econrich.homework.domain.employee.domain.Employee;
import com.econrich.homework.domain.employee.domain.EmployeeRepository;
import com.econrich.homework.exception.InvalidPercentageException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeSalaryUpdaterTest {
    private final EmployeeSalaryUpdater employeeSalaryUpdater;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeSalaryUpdaterTest(EmployeeSalaryUpdater employeeSalaryUpdater, EmployeeRepository employeeRepository) {
        this.employeeSalaryUpdater = employeeSalaryUpdater;
        this.employeeRepository = employeeRepository;
    }

    @Test
    void 부서의_급여_증가_정상_증가() {
        employeeSalaryUpdater.update(20L, 50);

        List<Employee> employees = employeeRepository.findByDepartmentId(20L);

        assertEquals(BigDecimal.valueOf(19500).setScale(2, RoundingMode.HALF_UP), employees.get(0).getSalary());
        assertEquals(BigDecimal.valueOf(9000).setScale(2, RoundingMode.HALF_UP), employees.get(1).getSalary());
    }

    @Test
    void 부서의_급여_차감_정상_증가() {
        employeeSalaryUpdater.update(20L, -50);

        List<Employee> employees = employeeRepository.findByDepartmentId(20L);

        assertEquals(BigDecimal.valueOf(6500).setScale(2, RoundingMode.HALF_UP), employees.get(0).getSalary());
        assertEquals(BigDecimal.valueOf(3000).setScale(2, RoundingMode.HALF_UP), employees.get(1).getSalary());
    }

    @Test
    void 급여_인상률이_100보다_크면_익셉션_발생() {
        assertThrows(InvalidPercentageException.class, () -> employeeSalaryUpdater.update(20L, 101));
    }

    @Test
    void 급여_인상률이_마이너스100보다_작으면_익셉션_발생() {
        assertThrows(InvalidPercentageException.class, () -> employeeSalaryUpdater.update(20L, -101));
    }
}