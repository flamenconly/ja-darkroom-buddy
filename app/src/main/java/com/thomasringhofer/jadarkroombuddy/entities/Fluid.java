package com.thomasringhofer.jadarkroombuddy.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Thomas on 19.02.2018.
 * Represents a Fluid
 */
@Entity(tableName = "fluid",indices = {@Index(value="title",name="idx_uq_fluid_title",unique = true),@Index(value="type",name="idx_fluid_type")})
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

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    @ColumnInfo(name="type")
    private String type;


    public Fluid(){}

    @Ignore
    public Fluid(String title){
        this.title = title;
    }

}
