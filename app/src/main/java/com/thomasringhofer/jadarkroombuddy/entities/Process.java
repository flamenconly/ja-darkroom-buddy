package com.thomasringhofer.jadarkroombuddy.entities;

import com.google.gson.Gson;

import java.time.LocalDateTime;

/**
 * Representation of a Developmentprocess.
 * Created by Thomas on 09.02.2018.
 */

public class Process implements JsonSerializable<Process> {

    public static String TYPE_NEGATIVE = "Negative";
    public static String TYPE_POSITIVE = "Positive";

    private String type;

    public String getType() {
        return type;
    }

    /**
     *
     * @param type, One of the TYPE_* Constants is allowed
     * @throws IllegalArgumentException if the given TYPE is not from one of the TYPE_* Constants
     */
    public void setType(String type) throws IllegalArgumentException {
        if(type.equalsIgnoreCase(TYPE_NEGATIVE) || type.equalsIgnoreCase(TYPE_POSITIVE)){
            this.type = type;
        }else{
            throw new IllegalArgumentException("type is unknown");
        }

    }

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
            result &= this.getType().equals(incoming.getType());

        }else{
            return false;
        }

        return result;
    }
}
