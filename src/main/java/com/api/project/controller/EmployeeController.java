package com.api.project.controller;

import com.api.project.controller.res.GetJobHistoryRes;
import com.api.project.domain.department.domain.Department;
import com.api.project.domain.employee.domain.Employee;
import com.api.project.domain.employee.service.EmployeeProfileFinder;
import com.api.project.controller.res.GetEmployeeRes;
import com.api.project.domain.employee.service.EmployeeSalaryUpdater;
import com.api.project.domain.job.domain.Job;
import com.api.project.domain.jobhistory.domain.JobHistory;
import com.api.project.domain.jobhistory.service.EmployeeJobHistoryFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeProfileFinder employeeProfileFinder;
    private final EmployeeJobHistoryFinder employeeJobHistoryFinder;
    private final EmployeeSalaryUpdater employeeSalaryUpdater;

    @GetMapping("/info/{employeeId}")
    @ResponseBody
    public GetEmployeeRes findByEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeProfileFinder.findById(employeeId);

        Optional<Job> job = Optional.ofNullable(employee.getJob());
        Optional<Employee> manager = Optional.ofNullable(employee.getManager());
        Optional<Department> department = Optional.ofNullable(employee.getDepartment());

        String jobId = job.map(Job::getJobId).orElse(null);
        Long mangerId = manager.map(Employee::getId).orElse(null);
        Long departmentId = department.map(Department::getId).orElse(null);

        return new GetEmployeeRes(employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getEmail(), employee.getPhoneNumber(),
                employee.getHireDate(), jobId, employee.getSalary(),
                employee.getCommissionPct(), mangerId, departmentId);
    }

    @GetMapping("/history/{employeeId}")
    @ResponseBody
    public List<GetJobHistoryRes> findByJobHistory(@PathVariable Long employeeId) {
        List<JobHistory> jobHistories = employeeJobHistoryFinder.findByEmployeeId(employeeId);

        return jobHistories.stream()
                .map(GetJobHistoryRes::new)
                .collect(Collectors.toList());
    }

    @PutMapping("/update")
    public String update(@Param("departmentId") Long departmentId, @Param("percent") int percent) {
        employeeSalaryUpdater.update(departmentId, percent);

        return "redirect:/";
    }
}