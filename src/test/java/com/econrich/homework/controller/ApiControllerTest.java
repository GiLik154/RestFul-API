package com.econrich.homework.controller;

import com.econrich.homework.enums.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApiControllerTest {
    private final WebApplicationContext context;
    private MockMvc mvc;

    @Autowired
    public ApiControllerTest(WebApplicationContext context) {
        this.context = context;
    }

    @BeforeEach
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @Test
    void API_조회_정상작동() throws Exception {
        MockHttpServletRequestBuilder builder = get("/api")
                .param("nx", "60")
                .param("ny", "127");

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").isNotEmpty());
    }

    @Test
    void 입력에_오류가_있을경우_핸들링() throws Exception {
        MockHttpServletRequestBuilder builder = get("/api")
                .param("nx", "")
                .param("ny", "");


        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name("thymeleaf/error-page"))
                .andExpect(model().attribute("errorMsg", ErrorCode.METHOD_EXCEPTION.getMsg()));
    }
}