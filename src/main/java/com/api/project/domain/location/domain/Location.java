package com.api.project.domain.location.domain;

import com.api.project.domain.countrie.domain.Countries;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private Long locationId;

    private String streetAddress;

    private String postalCode;

    @Column(nullable = false)
    private String city;

    private String stateProvince;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Countries country;

    protected Location() {}
}
