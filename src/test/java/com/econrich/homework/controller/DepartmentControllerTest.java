package com.econrich.homework.controller;

import com.econrich.homework.domain.department.domain.Department;
import com.econrich.homework.domain.department.domain.DepartmentRepository;
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
class DepartmentControllerTest {
    private final DepartmentRepository repository;
    private final WebApplicationContext context;
    private MockMvc mvc;

    @Autowired
    public DepartmentControllerTest(DepartmentRepository repository, WebApplicationContext context) {
        this.repository = repository;
        this.context = context;
    }

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    void 부서_정보_검색_정상_작동() throws Exception {
        Long findId = 10L;

        Department department = repository.findById(findId).get();

        MockHttpServletRequestBuilder builder = get("/department/info/" + findId);

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("departmentId").value(findId))
                .andExpect(MockMvcResultMatchers.jsonPath("departmentName").value(department.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("locationId").value(department.getLocation().getLocationId()));
    }

    @Test
    void 부서의_아이디가_다르면_핸들링() throws Exception {
        Long findId = 99999L;

        MockHttpServletRequestBuilder builder = get("/department/info/" + findId);

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/error-page"))
                .andExpect(model().attribute("errorMsg", ErrorCode.NOT_FOUND_DEPARTMENT.getMsg()));
    }
}