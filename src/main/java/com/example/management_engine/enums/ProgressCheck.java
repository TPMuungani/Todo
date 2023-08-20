package com.example.management_engine.enums;

public enum ProgressCheck {
    IN_PROGRESS("INPROGRESS"), COMPLETE("COMPLETE"), ON_HOLD("ONHOLD"), OPEN("OPEN"), CLOSE("CLOSE"), OVERDUE("OVERDUE");
    private final String description;

    ProgressCheck(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
