package com.thomasringhofer.jadarkroombuddy.entities;

import com.google.gson.Gson;

import java.time.LocalDateTime;

/**
 * Representation of a Developmentprocess.
 * Created by Thomas on 09.02.2018.
 */

public class Process implements JsonSerializable<Process> {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private LocalDateTime creationDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

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

    @Override
    public boolean equals(Object obj) {

        boolean result = true;

        if(obj.getClass().equals(this.getClass()))
        {
            Process incoming = (Process)obj;
            result &= this.getTitle().equals(incoming.getTitle());
            result &= this.getId().equals(incoming.getId());
            result &= this.getCreationDate().equals(incoming.getCreationDate());

        }else{
            return false;
        }

        return result;
    }
}
