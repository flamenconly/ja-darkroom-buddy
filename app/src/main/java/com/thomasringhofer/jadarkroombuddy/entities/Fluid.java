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
    private Integer id;

    public Integer getId(){return this.id;}

    public void setId(Integer id){this.id = id;}

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
    @ColumnInfo(name = "amount")
    private Float amount;

    /**
     * The amount in litres of the total solution.
     * @return amount
     */
    public  Float getAmount(){return this.amount;}

    /**
     * The amount in litres of the total solution.
     * @param amount
     */
    public void setAmount(Float amount){this.amount = amount;}

    public Fluid(){}

    public Fluid(String title, Float amount){
        this.title = title;
        this.amount = amount;
    }

}
