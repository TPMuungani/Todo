package com.example.management_engine.data;

import com.example.management_engine.domain.Department;
import com.example.management_engine.domain.User;
import com.example.management_engine.enums.ProgressCheck;
import java.util.List;

public class TodoDTO {
    private String title;
    private String description;
    private Department department;
    private ProgressCheck progressCheck;
    private User user;
    private List<String> subTasks;
}
