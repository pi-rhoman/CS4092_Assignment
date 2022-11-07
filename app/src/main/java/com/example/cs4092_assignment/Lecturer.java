package com.example.cs4092_assignment;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import java.util.ArrayList;

public class Lecturer {

    public Lecturer(Context context,
                    String name,
                    String department,
                    String field,
                    String imageFileName,
                    String url,
                    String[] researchAreas) {
        this.name = name;
        this.department = department;
        this.field = field;
        this.imageFileName = imageFileName;
        // todo validate url
        this.Url = url;
        this.researchAreas = researchAreas;

        // get image from filename
        this.image = context.getResources().getIdentifier(imageFileName, "drawable", context.getPackageName());
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
    Integer image;

    public String toString() {
        return "Person(" + name + ", " + department +")";
    }

    public int getImage(){
        return image;
    }

}
