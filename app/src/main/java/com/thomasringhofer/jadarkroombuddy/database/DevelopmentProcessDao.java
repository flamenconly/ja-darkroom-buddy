package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcess;

/**
 * Created by Thomas on 11.02.2018.
 */

@Dao
public interface DevelopmentProcessDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long[] InsertProcesses(DevelopmentProcess... processes);

    @Query("Select * from development_process")
    DevelopmentProcess[] LoadAllProcesses();

}
