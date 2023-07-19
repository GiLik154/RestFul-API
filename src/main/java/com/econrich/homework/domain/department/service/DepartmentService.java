package com.econrich.homework.domain.department.service;

import com.econrich.homework.domain.department.domain.Department;
import com.econrich.homework.domain.department.domain.DepartmentRepository;
import com.econrich.homework.exception.NotFoundDepartmentException;
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