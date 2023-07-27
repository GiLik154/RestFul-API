package com.api.project.controller;

import com.api.project.controller.res.GetDepartmentRes;
import com.api.project.domain.department.domain.Department;
import com.api.project.domain.department.service.DepartmentProfileFinder;
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
