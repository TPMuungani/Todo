package com.example.management_engine.domain;

import com.example.management_engine.enums.ProgressCheck;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class SubTasks extends BaseEntity{
    private String name;
    private ProgressCheck progressCheck;
}
