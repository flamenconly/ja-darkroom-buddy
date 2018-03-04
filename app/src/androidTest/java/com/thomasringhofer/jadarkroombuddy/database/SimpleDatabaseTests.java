package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcess;
import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcessFactory;
import com.thomasringhofer.jadarkroombuddy.entities.FluidInUse;
import com.thomasringhofer.jadarkroombuddy.entities.WorkingSolution;
import com.thomasringhofer.jadarkroombuddy.model.WorkingSolutionAndItsFluids;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

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

    @Test
    public void testInsertWorkingSolution(){
        WorkingSolution workingSolution = new WorkingSolution();
        workingSolution.setTitle("Title1");
        workingSolution.setNotes("<strong>A lot of bold text</strong>");

        long id = testDatabase.workingSolutionDao().insertWorkingSolution(workingSolution);

        Assert.assertTrue(id >0);
    }

    @Test
    public void testWorkingSolutionAndItsFluidsGetAmount(){
        WorkingSolutionAndItsFluids workingSolutionAndItsFluids = SetupWorkingSolutionAndItsFluids();
        Assert.assertEquals(workingSolutionAndItsFluids.getTotalAmount(),500f);
    }

    @Test
    public void testInsertWorkingSolutionHasFluidsConvenientMethod(){
        WorkingSolutionAndItsFluids workingSolutionAndItsFluids = SetupWorkingSolutionAndItsFluids();

        boolean result = testDatabase.insertUpdateWorkingSolutionAndItsFluids(workingSolutionAndItsFluids);
        Assert.assertTrue(result);

        Assert.assertEquals(workingSolutionAndItsFluids.getWorkingSolution().getId(),new Long(1));

        for (FluidInUse fluidInUse :
                workingSolutionAndItsFluids.getContainedFluids()) {

            Assert.assertTrue(fluidInUse.getId()>0);

        }
    }

    @Test
    public void testQueryWorkingSolutionHasFluidsConvenientMethod(){
        WorkingSolutionAndItsFluids workingSolutionAndItsFluids = SetupWorkingSolutionAndItsFluids();

        boolean result = testDatabase.insertUpdateWorkingSolutionAndItsFluids(workingSolutionAndItsFluids);
        Assert.assertTrue(result);

        WorkingSolutionAndItsFluids workingSolutionAndItsFluidsFromDB =
                testDatabase.getWorkingSolutionAndItsFluidsById(
                        workingSolutionAndItsFluids.getWorkingSolution().getId()
                );

        Assert.assertEquals(workingSolutionAndItsFluids.getWorkingSolution().getId(),
                workingSolutionAndItsFluidsFromDB.getWorkingSolution().getId());

        Assert.assertEquals(workingSolutionAndItsFluids.getWorkingSolution().getNotes(),
                workingSolutionAndItsFluidsFromDB.getWorkingSolution().getNotes());

        Assert.assertEquals(workingSolutionAndItsFluids.getWorkingSolution().getTitle(),
                workingSolutionAndItsFluidsFromDB.getWorkingSolution().getTitle());

        // The order can be different, so the amount is a high level test for the correct insert and query
        Assert.assertEquals(workingSolutionAndItsFluids.getTotalAmount(),workingSolutionAndItsFluidsFromDB.getTotalAmount());

    }

    private WorkingSolutionAndItsFluids SetupWorkingSolutionAndItsFluids(){
        WorkingSolutionAndItsFluids workingSolutionAndItsFluids = new WorkingSolutionAndItsFluids();

        WorkingSolution workingSolution = new WorkingSolution();
        workingSolution.setTitle("Title1");
        workingSolution.setNotes("<strong>A lot of bold text</strong>");
        workingSolutionAndItsFluids.setWorkingSolution(workingSolution);

        workingSolutionAndItsFluids.setContainedFluids(new ArrayList<FluidInUse>());
        workingSolutionAndItsFluids.getContainedFluids().add(new FluidInUse("Water",200f));
        workingSolutionAndItsFluids.getContainedFluids().add(new FluidInUse("Colortec Part 1",100f));
        workingSolutionAndItsFluids.getContainedFluids().add(new FluidInUse("Colortec Part 2",100f));
        workingSolutionAndItsFluids.getContainedFluids().add(new FluidInUse("Colortec Part 3",100f));

        return workingSolutionAndItsFluids;
    }
}
