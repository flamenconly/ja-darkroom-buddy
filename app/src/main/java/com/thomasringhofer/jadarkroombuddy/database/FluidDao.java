package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.thomasringhofer.jadarkroombuddy.entities.Fluid;

/**
 * Created by Thomas on 19.02.2018.
 */

@Dao
public interface FluidDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insertFluid(Fluid fluid);

    @Update(onConflict = OnConflictStrategy.FAIL)
    Fluid updateFluid(Fluid fluid);

    @Delete
    void deleteFluid(Fluid fluid);

}
