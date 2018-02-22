package com.thomasringhofer.jadarkroombuddy.entities;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Thomas on 22.02.2018.
 */

public class FluidInUse extends Fluid {

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    private float amount;

    public FluidInUse(String title,Float amount){
        super(title);
        this.amount = amount;
    }

}
