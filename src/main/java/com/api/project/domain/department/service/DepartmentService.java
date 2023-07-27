package com.api.project.domain.department.service;

import com.api.project.domain.department.domain.Department;
import com.api.project.domain.department.domain.DepartmentRepository;
import com.api.project.exception.NotFoundDepartmentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService implements DepartmentProfileFinder{
    private final DepartmentRepository repository;

    @Override
    public Department findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundDepartmentException("Not found department by id"));
    }
}