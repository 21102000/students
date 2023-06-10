package com.sat.students.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "candidates")
public class Candidates {

    @Id
    private String name;

    private String address;

    private String city;

    private String country;

    private int pincode;

    private int satScore;

    private String result;

    @Column(name = "ranks")
    private int rank;
}
