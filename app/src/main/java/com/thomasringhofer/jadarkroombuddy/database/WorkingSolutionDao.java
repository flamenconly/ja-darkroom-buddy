package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.thomasringhofer.jadarkroombuddy.entities.WorkingSolution;
import com.thomasringhofer.jadarkroombuddy.entities.WorkingSolutionIdAndTitle;

import java.util.List;

/**
 * Created by Thomas on 19.02.2018.
 */
@Dao
public interface WorkingSolutionDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    Long insertWorkingSolution(WorkingSolution workingSolutions);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateWorkingSolution(WorkingSolution workingSolution);

    @Delete()
    void deleteWorkingSolution(WorkingSolution workingSolution);

    @Query("select id,title from working_solution")
    List<WorkingSolutionIdAndTitle> loadAllWorkingSolutions();

    @Query("select * from working_solution where id = :id")
    WorkingSolution loadWorkingSolutionById(long id);

}
