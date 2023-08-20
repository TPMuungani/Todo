package com.example.management_engine.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Department extends BaseEntity{
    private String name;
}
