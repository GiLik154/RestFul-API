package com.econrich.homework.controller;

import com.econrich.homework.domain.department.domain.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final DepartmentRepository departmentRepository;

    @GetMapping
    public String main(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "thymeleaf/main";
    }
}