package com.thomasringhofer.jadarkroombuddy.entities;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Thomas on 19.02.2018.
 */

public class WorkingSolutionTest {

    @Test
    public void amount_calculation_correct(){

        WorkingSolution workingSolution = new WorkingSolution();

        WorkingSolutionAndItsFluids workingSolutionAndItsFluids;
        workingSolutionAndItsFluids = new WorkingSolutionAndItsFluids(workingSolution,new ArrayList<FluidInUse>());

        workingSolutionAndItsFluids.getContainedFluids().add(new FluidInUse("Water",200f));
        workingSolutionAndItsFluids.getContainedFluids().add(new FluidInUse("Farbentwickler CD Part1",100f));
        workingSolutionAndItsFluids.getContainedFluids().add(new FluidInUse("Farbentwickler CD Part2",100f));
        workingSolutionAndItsFluids.getContainedFluids().add(new FluidInUse("Farbentwickler CD Part3",100f));

        Assert.assertEquals(workingSolutionAndItsFluids.getTotalAmount(),500f,0f);
    }

}
