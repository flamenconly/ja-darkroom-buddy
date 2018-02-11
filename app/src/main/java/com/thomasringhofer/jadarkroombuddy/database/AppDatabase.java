package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcess;

/**
 * Created by Thomas on 11.02.2018.
 */
@Database(entities = {DevelopmentProcess.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase CreateInstance(Context context){
        //if(INSTANCE == null)INSTANCE = Room.databaseBuilder(context,AppDatabase.class,"productive").build();
        // For developing purposes only memory database
        if(INSTANCE == null)INSTANCE = Room.inMemoryDatabaseBuilder(context,AppDatabase.class).build();
        return INSTANCE;
    }

    public static AppDatabase GetInstance(){
        if(INSTANCE == null) throw new IllegalStateException("Call CreateInstance before using GetInstance");
        return INSTANCE;
    }

    public abstract DevelopmentProcessDao developmentProcessDao();
}
