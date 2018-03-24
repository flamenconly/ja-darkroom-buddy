package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.thomasringhofer.jadarkroombuddy.entities.Fluid;

import java.util.List;

/**
 * Created by Thomas on 19.02.2018.
 */
@Dao
public interface FluidDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insertFluid(Fluid fluid);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateFluid(Fluid fluid);

    @Delete
    void deleteFluid(Fluid fluid);

    @Query(value = "select * from fluid order by title")
    List<Fluid> getAll();

    @Query(value = "select title from fluid")
    List<String> getTitles();

    @Query(value = "select * from fluid where title = :title")
    Fluid getItemByTitle(String title);

}
