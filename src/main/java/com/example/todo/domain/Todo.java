package com.example.todo.domain;

import lombok.Data;
import javax.persistence.ManyToOne;

@Data
public class Todo extends BaseEntity{
    private String title;
    private String description;
    private Long user_id;
}
