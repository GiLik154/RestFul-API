package com.econrich.homework.domain.employee.service;

import com.econrich.homework.domain.employee.domain.Employee;
import com.econrich.homework.domain.employee.domain.EmployeeRepository;
import com.econrich.homework.exception.NotFoundEmployeeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeProfileFinderTest {
    private final EmployeeProfileFinder finder;
    private final EmployeeRepository repository;

    @Autowired
    public EmployeeProfileFinderTest(EmployeeProfileFinder finder, EmployeeRepository repository) {
        this.finder = finder;
        this.repository = repository;
    }

    @Test
    void 직원의_정보_찾기_정상작동() {
        Long findId = 100L;

        Employee result = finder.findById(findId);

        Employee employee = repository.findById(findId).get();

        assertEquals(employee.getId(), result.getId());
        assertEquals(employee.getLastName(), result.getLastName());
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getEmail(), result.getEmail());
        assertEquals(employee.getPhoneNumber(), result.getPhoneNumber());

    }

    @Test
    void 직원의_아이디가_다르면_익셉션_발생() {
        assertThrows(NotFoundEmployeeException.class, () -> finder.findById(88888L));
    }
}