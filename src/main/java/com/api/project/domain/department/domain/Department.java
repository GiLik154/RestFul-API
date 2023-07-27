package com.api.project.domain.department.domain;

import com.api.project.domain.employee.domain.Employee;
import com.api.project.domain.location.domain.Location;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@Getter
public class Department {
    @Id
    @Column(name = "department_id", columnDefinition = "int")
    private Long id;

    @Column(name = "department_name",nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    protected Department() {}
}
