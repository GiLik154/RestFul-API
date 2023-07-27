package com.api.project.controller;

import com.api.project.domain.employee.domain.Employee;
import com.api.project.domain.employee.domain.EmployeeRepository;
import com.api.project.enums.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeUpdateControllerTest {
    private final EmployeeRepository repository;
    private final WebApplicationContext context;
    private MockMvc mvc;

    @Autowired
    public EmployeeUpdateControllerTest(EmployeeRepository repository, WebApplicationContext context) {
        this.repository = repository;
        this.context = context;
    }

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    void 직원의_급여_증가_정상작동() throws Exception {
        MockHttpServletRequestBuilder builder = put("/employee/update")
                .param("departmentId", "20")
                .param("percent", "50");

        mvc.perform(builder)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        List<Employee> employees = repository.findByDepartmentId(20L);

        assertEquals(BigDecimal.valueOf(19500).setScale(2, RoundingMode.HALF_UP), employees.get(0).getSalary());
        assertEquals(BigDecimal.valueOf(9000).setScale(2, RoundingMode.HALF_UP), employees.get(1).getSalary());
    }

    @Test
    void 직원의_급여_차감_정상작동() throws Exception {
        MockHttpServletRequestBuilder builder = put("/employee/update")
                .param("departmentId", "20")
                .param("percent", "-50");

        mvc.perform(builder)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        List<Employee> employees = repository.findByDepartmentId(20L);

        assertEquals(BigDecimal.valueOf(6500).setScale(2, RoundingMode.HALF_UP), employees.get(0).getSalary());
        assertEquals(BigDecimal.valueOf(3000).setScale(2, RoundingMode.HALF_UP), employees.get(1).getSalary());
    }

    @Test
    void 부서의_고유번호가_다르면_핸들링_됨() throws Exception {
        MockHttpServletRequestBuilder builder = put("/employee/update")
                .param("departmentId", "99999")
                .param("percent", "50");

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/error-page"))
                .andExpect(model().attribute("errorMsg", ErrorCode.NOT_FOUND_DEPARTMENT.getMsg()));

        List<Employee> employees = repository.findByDepartmentId(20L);

        assertEquals(BigDecimal.valueOf(13000).setScale(2, RoundingMode.HALF_UP), employees.get(0).getSalary());
        assertEquals(BigDecimal.valueOf(6000).setScale(2, RoundingMode.HALF_UP), employees.get(1).getSalary());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-101", "101"})
    void 직원의_급여_입력_오류가_있으면_핸들링_됨(String percent) throws Exception {
        MockHttpServletRequestBuilder builder = put("/employee/update")
                .param("departmentId", "20")
                .param("percent", percent);

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/error-page"))
                .andExpect(model().attribute("errorMsg", ErrorCode.INVALID_PERCENTAGE.getMsg()));

        List<Employee> employees = repository.findByDepartmentId(20L);

        assertEquals(BigDecimal.valueOf(13000).setScale(2, RoundingMode.HALF_UP), employees.get(0).getSalary());
        assertEquals(BigDecimal.valueOf(6000).setScale(2, RoundingMode.HALF_UP), employees.get(1).getSalary());
    }
}