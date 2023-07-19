package com.econrich.homework.controller.res;

import com.econrich.homework.domain.department.domain.Department;
import com.econrich.homework.domain.location.domain.Location;
import lombok.Getter;

@Getter
public class GetDepartmentRes {
    private final Long departmentId;
    private final String departmentName;
    private final Long mangerId;
    private final Long locationId;
    private final String streetAddress;
    private final String postalCode;
    private final String city;
    private final String stateProvince;
    private final String countryId;

    public GetDepartmentRes(Department department, Location location) {
        this.departmentId = department.getId();
        this.departmentName = department.getName();
        this.mangerId = department.getManager().getId();
        this.locationId = location.getLocationId();
        this.streetAddress = location.getStreetAddress();
        this.postalCode = location.getPostalCode();
        this.city = location.getCity();
        this.stateProvince = location.getStateProvince();
        this.countryId = location.getCountry().getId();
    }
}