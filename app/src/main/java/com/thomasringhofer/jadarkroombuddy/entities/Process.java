package com.thomasringhofer.jadarkroombuddy.entities;

import com.google.gson.Gson;

/**
 * Representation of a Developmentprocess.
 * Created by Thomas on 09.02.2018.
 */

public class Process implements JsonSerializable<Process> {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Process deserialize(String jsonValue) {
        Gson gson = new Gson();
        Process instance = gson.fromJson(jsonValue,Process.class);
        return instance;
    }

    @Override
    public String serialize() {
        Gson gson = new Gson();
        String string = gson.toJson(this);
        return string;
    }
}
