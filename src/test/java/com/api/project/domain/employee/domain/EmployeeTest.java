package com.api.project.domain.employee.domain;

import com.api.project.exception.InvalidPercentageException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @ParameterizedTest
    @CsvSource({"0.01, 10", "1.99, 1990", "1.5, 1500"})
    void 급여_업데이트_정상작동(double testDouble, double resultDouble) {
        BigDecimal testBigDecimal = new BigDecimal(testDouble).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal resultBigDecimal = new BigDecimal(resultDouble).setScale(2, RoundingMode.HALF_EVEN);
        Date date = new Date();

        Employee employee = new Employee(100L, "testName", "testEmail", date, new BigDecimal(1000));

        employee.updateSalary(testBigDecimal);

        assertEquals(resultBigDecimal, employee.getSalary());
    }

    @Test
    void 급여_인상_퍼센트가_2배보다_크면_익셉션_발생() {
        Date date = new Date();

        Employee employee = new Employee(100L, "testName", "testEmail", date, new BigDecimal(1000));

        BigDecimal wrongPercentage = new BigDecimal("2.01");

        assertThrows(InvalidPercentageException.class, () -> employee.updateSalary(wrongPercentage));
    }

    @Test
    void 급여_인상_퍼센트가_0보다_작으면_익셉션_발생() {
        Date date = new Date();

        Employee employee = new Employee(100L, "testName", "testEmail", date, new BigDecimal(1000));

        BigDecimal wrongPercentage = new BigDecimal("-0.01");

        assertThrows(InvalidPercentageException.class, () -> employee.updateSalary(wrongPercentage));
    }
}