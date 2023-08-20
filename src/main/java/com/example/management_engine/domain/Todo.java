package com.example.management_engine.domain;

import com.example.management_engine.enums.ProgressCheck;
import lombok.Data;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Todo extends BaseEntity{
    private String title;
    private String description;
    @ManyToOne
    private Department department;
    private ProgressCheck progressCheck;
    @ManyToOne
    private User user;
    @OneToMany
    private List<SubTasks> subTasks;
    private BigDecimal percentageProgress;
    private Date startDate;
    private Date finishDate;
}
