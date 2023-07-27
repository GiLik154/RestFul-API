package com.api.project.domain.region.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
@Getter
public class Region {
    @Id
    @Column(columnDefinition = "int")
    private Long regionId;

    private String regionName;

    protected Region() {}
}
