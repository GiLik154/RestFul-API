package com.econrich.homework.controller;

import com.econrich.homework.controller.res.GetDepartmentRes;
import com.econrich.homework.domain.department.domain.Department;
import com.econrich.homework.domain.department.service.DepartmentProfileFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentProfileFinder departmentProfileFinder;

    @GetMapping("/info/{departmentId}")
    @ResponseBody
    public GetDepartmentRes findByDepartment(@PathVariable Long departmentId) {
        Department department = departmentProfileFinder.findById(departmentId);

        return new GetDepartmentRes(department, department.getLocation());
    }
}
