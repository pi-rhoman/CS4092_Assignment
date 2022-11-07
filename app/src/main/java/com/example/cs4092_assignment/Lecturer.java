package com.example.cs4092_assignment;

import java.util.ArrayList;

public class Lecturer {

    public Lecturer(String name, String department, String field, String imageFileName, String url, String[] researchAreas) {
        this.name = name;
        this.department = department;
        this.field = field;
        this.imageFileName = imageFileName;
        this.Url = url;
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

    public String[] getResearchAreas() {
        return researchAreas;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getUrl() {
        return Url;
    }

    String name;
    String department;
    String field;
    String imageFileName;
    String Url;
    String [] researchAreas;

    public String toString() {
        return name + ", " + department;
    }

}
