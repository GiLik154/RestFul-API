package com.api.project.domain.countrie.domain;

import com.api.project.domain.region.domain.Region;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "countries")
@Getter
public class Countries {
    @Id
    @Column(name = "country_id", columnDefinition = "char", length = 2)
    private String id;

    @Column(name = "country_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    protected Countries() {}
}
