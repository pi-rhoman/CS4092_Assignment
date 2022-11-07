package com.example.cs4092_assignment;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Lecturer {

    public Lecturer(Context context,
                    String name,
                    String department,
                    String field,
                    String imageFileName,
                    String url,
                    String[] researchAreas) throws MalformedURLException, URISyntaxException{
        this.name = name;
        this.department = department;
        this.field = field;
        this.imageFileName = imageFileName;
        setUrl(url);
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

    public URI getUrl() {
        return url;
    }

    /**
     *
     * @param url well formed url or null
     * @throws MalformedURLException if the url is invalid and non null
     * @throws URISyntaxException if the uri syntax is invalid an non null
     */
    public void setUrl(String url) throws MalformedURLException, URISyntaxException {
        if(url == null){
            this.url = null;
        } else {
            this.url = new URL(url).toURI();
        }

    }

    String name;
    String department;
    String field;
    String imageFileName;
    URI url;
    String [] researchAreas;
    Integer image;

    public String toString() {
        return "Person(" + name + ", " + department +")";
    }

    public int getImage(){
        return image;
    }

}
