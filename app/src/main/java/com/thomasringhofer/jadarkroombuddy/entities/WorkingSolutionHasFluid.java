package com.thomasringhofer.jadarkroombuddy.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

/**
 * Created by Thomas on 22.02.2018.
 */
@Entity(tableName = "workingsolution_has_fluid", indices = {
        @Index(value={"working_solution_id","fluid_id"},unique = true)
    },
    foreignKeys = {
            @ForeignKey(entity = WorkingSolution.class,parentColumns = "id",childColumns = "working_solution_id"),
            @ForeignKey(entity = Fluid.class,parentColumns = "id",childColumns = "fluid_id")
    },
    primaryKeys = {"working_solution_id","fluid_id"}
)
public class WorkingSolutionHasFluid {

    public Long getFluidId() {
        return fluidId;
    }

    public void setFluidId(Long fluidId) {
        this.fluidId = fluidId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Long getWorkingSolutionId() {
        return workingSolutionId;
    }

    public void setWorkingSolutionId(Long workingSolutionId) {
        this.workingSolutionId = workingSolutionId;
    }

    @NonNull
    @ColumnInfo(name = "working_solution_id",index = true)
    private Long workingSolutionId;

    @NonNull
    @ColumnInfo(name = "fluid_id",index = true)
    private Long fluidId;

    @NonNull
    @ColumnInfo(name = "amount")
    private float amount;
}
