package com.thomasringhofer.jadarkroombuddy.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 19.02.2018.
 * The Working Solution
 */
@Entity(tableName = "working_solution")
public class WorkingSolution {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    @ColumnInfo(name = "title",index = true)
    private String title;

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }
    @Nullable
    @ColumnInfo(name="notes")
    private String notes;

    @Nullable
    public String getNotes() {
        return notes;
    }

    public void setNotes(@Nullable String notes) {
        this.notes = notes;
    }

    @Relation(parentColumn = "id",entityColumn = "id")
    private List<Fluid> containedFluid;

    public WorkingSolution() {
        containedFluid = new ArrayList<>();
    }

    public void setContainedFluid(List<Fluid> containedFluid){this.containedFluid = containedFluid;}

    public List<Fluid> getContainedFluid(){return this.containedFluid;}

    /**
     * The amount in litres of the total solution.
     * @return amount
     */
    public  Float getAmount(){return calculateAmountInLitres();}

    private Float calculateAmountInLitres(){

        Float amount = 0f;

        for (Fluid f : containedFluid) {
            amount+=f.getAmount();
        }
        return amount;
    }



}
