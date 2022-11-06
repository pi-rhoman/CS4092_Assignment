package com.example.cs4092_assignment;

import java.util.ArrayList;

public class Lecturer {
    public Lecturer(String name, String department, String field, ArrayList<String> researchAreas) {
        this.name = name;
        this.department = department;
        this.field = field;
        this.researchAreas = researchAreas;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getField() {
        return field;
    }

    public ArrayList<String> getResearchAreas() {
        return researchAreas;
    }

    String name, department, field;
    ArrayList<String> researchAreas;

}
