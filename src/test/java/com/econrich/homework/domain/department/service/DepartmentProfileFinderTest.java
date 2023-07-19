package com.econrich.homework.domain.department.service;

import com.econrich.homework.domain.department.domain.Department;
import com.econrich.homework.domain.department.domain.DepartmentRepository;
import com.econrich.homework.exception.NotFoundDepartmentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentProfileFinderTest {
    private final DepartmentRepository repository;
    private final DepartmentProfileFinder finder;

    @Autowired
    public DepartmentProfileFinderTest(DepartmentRepository repository, DepartmentProfileFinder finder) {
        this.repository = repository;
        this.finder = finder;
    }

    @Test
    void 부서_조회_정상_작동() {
        Long findId = 10L;

        Department result = finder.findById(10L);

        Department department = repository.findById(findId).get();

        assertEquals(department.getId(), result.getId());
        assertEquals(department.getName(), result.getName());
    }

    @Test
    void 부서의_아이디가_다르면_익셉션_발생() {
        assertThrows(NotFoundDepartmentException.class, () -> finder.findById(999999L));
    }
}