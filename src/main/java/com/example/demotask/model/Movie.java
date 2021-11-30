package com.example.demotask.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
public class Movie {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String country;
}

