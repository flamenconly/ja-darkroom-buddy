package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.thomasringhofer.jadarkroombuddy.common.IIdGenerator;
import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcess;
import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcessFactory;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Thomas on 11.02.2018.
 */

@RunWith(AndroidJUnit4.class)
public class SimpleDatabaseTests {

    private AppDatabase testDatabase;

    @Before
    public void createDB(){
        Context context = InstrumentationRegistry.getTargetContext();
        testDatabase = Room.inMemoryDatabaseBuilder(context,AppDatabase.class).build();
    }

    @After
    public void closeDB(){
        testDatabase.close();
    }

    @Test
    public void testSelectDevelopmentProcess(){
        DevelopmentProcess[] processes = testDatabase.developmentProcessDao().LoadAllProcesses();
        Assert.assertTrue(processes.length==0);
    }

    @Test(expected = SQLiteConstraintException.class)
    public void testDevelopmentProcess_TitleConstraint(){
        DevelopmentProcess process = new DevelopmentProcessFactory().CreateDefaultNegativeDevelopmentProcess();
        testDatabase.developmentProcessDao().InsertProcesses(process);
    }

    @Test
    public void testInsertDevelopmentProcess(){
        DevelopmentProcess process = new DevelopmentProcessFactory().CreateDefaultNegativeDevelopmentProcess();
        process.setTitle("Testtitle");

        long newId = testDatabase.developmentProcessDao().InsertProcesses(process)[0];
        process.setId(newId);

        DevelopmentProcess[] processes = testDatabase.developmentProcessDao().LoadAllProcesses();
        Assert.assertEquals(process,processes[0]);
    }
}
