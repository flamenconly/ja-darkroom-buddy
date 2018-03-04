package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Transaction;
import android.content.Context;
import android.support.annotation.NonNull;

import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcess;
import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcessFactory;
import com.thomasringhofer.jadarkroombuddy.entities.Fluid;
import com.thomasringhofer.jadarkroombuddy.entities.FluidInUse;
import com.thomasringhofer.jadarkroombuddy.entities.WorkingSolution;
import com.thomasringhofer.jadarkroombuddy.model.WorkingSolutionAndItsFluids;
import com.thomasringhofer.jadarkroombuddy.entities.WorkingSolutionHasFluid;
import com.thomasringhofer.jadarkroombuddy.exceptions.PersistEntityFailedException;

import java.util.List;


/**
 * Created by Thomas on 11.02.2018.
 */
@Database(entities = {DevelopmentProcess.class,WorkingSolution.class,Fluid.class,WorkingSolutionHasFluid.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase CreateInstance(Context context){
        //TODO: Remove when developing is done to release mode
        //if(INSTANCE == null)INSTANCE = Room.databaseBuilder(context,AppDatabase.class,"productive").build();
        // For developing purposes only memory database
        if(INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        }

        return INSTANCE;
    }

    public static AppDatabase GetInstance(){
        if(INSTANCE == null) throw new IllegalStateException("Call CreateInstance before using GetInstance");
        return INSTANCE;
    }

    public void recreateData(){

        DevelopmentProcess processes;
        DevelopmentProcessFactory factory = new DevelopmentProcessFactory();

        processes = factory.CreateDefaultNegativeDevelopmentProcess();
        processes.setTitle("First Process");
        GetInstance().developmentProcessDao().InsertProcesses(processes);

        processes = factory.CreateDefaultNegativeDevelopmentProcess();
        processes.setTitle("Second Process");
        GetInstance().developmentProcessDao().InsertProcesses(processes);

        processes = factory.CreateDefaultPositiveDevelopmentProcess();
        processes.setTitle("Third Process");
        GetInstance().developmentProcessDao().InsertProcesses(processes);

    }

    /**
     * Convenient Method to retrieve the complete WorkingSolutionObject.
     * @param workingSolutionId of the desired workingSolutionObject.
     * @return the composed object of workingsolution and it's child objects.
     */
    public WorkingSolutionAndItsFluids getWorkingSolutionAndItsFluidsById(long workingSolutionId){

        WorkingSolutionAndItsFluids workingSolutionAndItsFluids = new WorkingSolutionAndItsFluids();
        WorkingSolution workingSolution = workingSolutionDao().loadWorkingSolutionById(workingSolutionId);

        if(workingSolution != null){
            workingSolutionAndItsFluids.setWorkingSolution(workingSolution);

            List<FluidInUse> fluids = workingSolutionHasFluidDao().getFluidOfWorkingSolution(workingSolution.getId());
            workingSolutionAndItsFluids.setContainedFluids(fluids);
        }

        return workingSolutionAndItsFluids;
    }

    /**
     * Convenience Method for persisting a Working Solution and it's contents.
     * @param workingSolutionAndItsFluids
     * @return true if ok.
     * @throws PersistEntityFailedException
     */
    @Transaction
    public boolean insertUpdateWorkingSolutionAndItsFluids(@NonNull final WorkingSolutionAndItsFluids workingSolutionAndItsFluids)  {

        WorkingSolution workingSolution = workingSolutionAndItsFluids.getWorkingSolution();
        List<FluidInUse> fluids = workingSolutionAndItsFluids.getContainedFluids();

        try {
            insertUpdateWorkingSolution(workingSolution);
        } catch (PersistEntityFailedException e) {
            e.printStackTrace();
            return false;
        }

        try {
            insertUpdateFluids(workingSolution,fluids);
        } catch (PersistEntityFailedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void insertUpdateWorkingSolution(WorkingSolution workingSolution) throws PersistEntityFailedException{
        if(workingSolution.getId()==null){
            long id = workingSolutionDao().insertWorkingSolution(workingSolution);
            if(id==-1)
                throw new PersistEntityFailedException("Insert of WorkingSolution failed");
            else
                workingSolution.setId(id);
        }else{
            int result = workingSolutionDao().updateWorkingSolution(workingSolution);
            if(result!=1)
                throw new PersistEntityFailedException("Update of WorkingSolution with ID: "+ workingSolution.getId().toString() + " failed");
        }
    }

    private void insertUpdateFluids(WorkingSolution workingSolution, List<FluidInUse> fluidsInUse) throws PersistEntityFailedException {

        for (FluidInUse fluidInUse :
                fluidsInUse) {

            Fluid fluid = new Fluid();
            fluid.setTitle(fluidInUse.getTitle());

            if(fluidInUse.getId()==null){

                long id = fluidDao().insertFluid(fluid);
                if(id==-1){
                    throw new PersistEntityFailedException("Insert of fluid failed.");
                }else{
                    fluid.setId(id);
                    fluidInUse.setId(id);
                }
            }else{
                fluid.setId(fluidInUse.getId());
                fluidDao().updateFluid(fluid);
            }

            WorkingSolutionHasFluid workingSolutionHasFluid = new WorkingSolutionHasFluid();
            workingSolutionHasFluid.setFluidId(fluidInUse.getId());
            workingSolutionHasFluid.setAmount(fluidInUse.getAmount());
            workingSolutionHasFluid.setWorkingSolutionId(workingSolution.getId());

            workingSolutionHasFluidDao().insert(workingSolutionHasFluid);
        }
    }

    public abstract DevelopmentProcessDao developmentProcessDao();

    public abstract WorkingSolutionDao workingSolutionDao();

    public abstract FluidDao fluidDao();

    public abstract WorkingSolutionHasFluidDao workingSolutionHasFluidDao();
}
