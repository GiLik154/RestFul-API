package com.econrich.homework.controller;

import com.econrich.homework.domain.employee.domain.Employee;
import com.econrich.homework.domain.employee.domain.EmployeeRepository;
import com.econrich.homework.enums.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeInfoControllerTest {
    private final EmployeeRepository repository;
    private final WebApplicationContext context;
    private MockMvc mvc;

    @Autowired
    public EmployeeInfoControllerTest(EmployeeRepository repository, WebApplicationContext context) {
        this.repository = repository;
        this.context = context;
    }

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    void 직원_찾기_정상_작동() throws Exception {
        Long findId = 100L;

        Employee employee = repository.findById(findId).get();

        MockHttpServletRequestBuilder builder = get("/employee/info/" + findId);

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("employeeId").value(employee.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value(employee.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("lastName").value(employee.getLastName()));
    }

    @Test
    void 직원의_아이디가_다르면_핸들링() throws Exception {
        Long findId = 99999L;

        MockHttpServletRequestBuilder builder = get("/employee/info/" + findId);

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/error-page"))
                .andExpect(model().attribute("errorMsg", ErrorCode.NOT_FOUND_EMPLOYEE.getMsg()));
    }
}