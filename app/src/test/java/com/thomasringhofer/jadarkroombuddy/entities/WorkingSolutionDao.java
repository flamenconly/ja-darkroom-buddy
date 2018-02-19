package com.thomasringhofer.jadarkroombuddy.entities;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Thomas on 19.02.2018.
 */

public class WorkingSolutionDao {

    @Test
    public void amount_calculation_correct(){
        WorkingSolution workingSolution = new WorkingSolution();

        workingSolution.getContainedFluid().add(new Fluid("Water",200f));
        workingSolution.getContainedFluid().add(new Fluid("Farbentwickler CD Part1",100f));
        workingSolution.getContainedFluid().add(new Fluid("Farbentwickler CD Part2",100f));
        workingSolution.getContainedFluid().add(new Fluid("Farbentwickler CD Part3",100f));

        Assert.assertEquals(workingSolution.getAmount(),500f,0f);
    }

}
