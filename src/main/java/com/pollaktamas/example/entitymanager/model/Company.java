package com.pollaktamas.example.entitymanager.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Company {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
}
