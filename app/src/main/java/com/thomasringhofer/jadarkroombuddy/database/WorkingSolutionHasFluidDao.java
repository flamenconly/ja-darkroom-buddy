package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.thomasringhofer.jadarkroombuddy.entities.FluidInUse;
import com.thomasringhofer.jadarkroombuddy.entities.WorkingSolutionHasFluid;

import java.util.List;

/**
 * Created by Thomas on 22.02.2018.
 */
@Dao
public interface WorkingSolutionHasFluidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WorkingSolutionHasFluid workingSolutionHasFluid);

    @Query(value="select fluid.*,workingsolution_has_fluid.amount from fluid " +
            "inner join workingsolution_has_fluid " +
            "on fluid.id = workingsolution_has_fluid.fluid_id " +
            "where workingsolution_has_fluid.working_solution_id = :workingSolutionId")
    List<FluidInUse> getFluidOfWorkingSolution(final Long workingSolutionId);

    @Delete
    void deleteById(WorkingSolutionHasFluid workingSolutionHasFluid);
}
