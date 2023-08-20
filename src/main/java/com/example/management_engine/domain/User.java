package com.example.management_engine.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class User extends BaseEntity{
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    @OneToOne
    private Department department;

}
