package com.thomasringhofer.jadarkroombuddy.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Thomas on 19.02.2018.
 * Represents a Fluid
 */
@Entity(tableName = "fluid")
public class Fluid {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    public Long getId(){return this.id;}

    public void setId(Long id){this.id = id;}

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    /**
     * @return The name of the fluid.
     */
    public String getTitle(){return title;}

    /**
     * Sets the name of the fluid.
     * @param title
     */
    public void setTitle(String title){this.title = title;}

    public Fluid(){}

    public Fluid(String title){
        this.title = title;
    }

}
