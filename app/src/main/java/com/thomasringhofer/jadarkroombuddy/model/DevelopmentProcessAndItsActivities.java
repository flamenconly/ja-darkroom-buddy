package com.thomasringhofer.jadarkroombuddy.model;

import java.io.Serializable;

/**
 * Created by Thomas on 03.03.2018.
 */
public class DevelopmentProcessAndItsActivities implements Serializable {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    private String duration;

    private String description;

    private String title;


}
