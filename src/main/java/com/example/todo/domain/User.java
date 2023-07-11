package com.example.todo.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class User extends BaseEntity{
    private String first_name;
    private String last_name;
    private String email;
    private List<Todo> todos;
}
