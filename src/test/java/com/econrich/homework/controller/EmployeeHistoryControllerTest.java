package com.econrich.homework.controller;

import com.econrich.homework.domain.jobhistory.domain.JobHistory;
import com.econrich.homework.domain.jobhistory.domain.JobHistoryRepository;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeHistoryControllerTest {
    private final JobHistoryRepository repository;
    private final WebApplicationContext context;
    private MockMvc mvc;

    @Autowired
    public EmployeeHistoryControllerTest(JobHistoryRepository repository, WebApplicationContext context) {
        this.repository = repository;
        this.context = context;
    }

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    void 직원_이력_찾기_정상_작동() throws Exception {
        Long findId = 101L;

        List<JobHistory> jobHistoryList = repository.findByEmployeeId(findId);

        MockHttpServletRequestBuilder builder = get("/employee/history/" + findId);

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].employeeId").value(findId))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].jobId").value(jobHistoryList.get(0).getJob().getJobId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentId").value(jobHistoryList.get(0).getDepartment().getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].employeeId").value(findId))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].jobId").value(jobHistoryList.get(1).getJob().getJobId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentId").value(jobHistoryList.get(1).getDepartment().getId()));
    }

    @Test
    void 직원의_아이디가_다르면_핸들링() throws Exception {
        Long findId = 99999L;

        MockHttpServletRequestBuilder builder = get("/employee/history/" + findId);

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/error-page"))
                .andExpect(model().attribute("errorMsg", ErrorCode.NOT_FOUND_EMPLOYEE.getMsg()));
    }
}