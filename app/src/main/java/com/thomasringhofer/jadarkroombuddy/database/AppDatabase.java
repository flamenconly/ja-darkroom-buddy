package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcess;
import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcessFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Created by Thomas on 11.02.2018.
 */
@Database(entities = {DevelopmentProcess.class},version = 1)
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

    public abstract DevelopmentProcessDao developmentProcessDao();
}
