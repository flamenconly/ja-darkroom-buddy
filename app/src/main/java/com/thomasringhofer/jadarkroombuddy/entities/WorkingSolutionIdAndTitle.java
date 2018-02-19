package com.thomasringhofer.jadarkroombuddy.entities;

import android.support.annotation.NonNull;

/**
 * Created by Thomas on 19.02.2018.
 */
public class WorkingSolutionIdAndTitle {
    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    private long id;

    @NonNull
    private String title;
}
