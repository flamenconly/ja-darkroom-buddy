package com.thomasringhofer.jadarkroombuddy.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 22.02.2018.
 * Composed Object for WorkingSolution and the contained fluids
 */
public class WorkingSolutionAndItsFluids {

    public WorkingSolutionAndItsFluids(){}

    public WorkingSolutionAndItsFluids(WorkingSolution workingSolution,List<FluidInUse> fluids){
        setWorkingSolution(workingSolution);
        setContainedFluids(fluids);
    }

    public WorkingSolution getWorkingSolution() {
        return workingSolution;
    }

    public void setWorkingSolution(WorkingSolution workingSolution) {
        this.workingSolution = workingSolution;
    }

    public List<FluidInUse> getContainedFluids() {
        return containedFluids;
    }

    public void setContainedFluids(List<FluidInUse> containedFluids) {
        this.containedFluids = containedFluids;
    }

    /**
     * The amount in litres of the total solution.
     * @return amount
     */
    public  Float getTotalAmount(){return calculateAmountInLitres();}

    private Float calculateAmountInLitres(){

        Float amount = 0f;

        for (FluidInUse f : containedFluids) {
            amount+=f.getAmount();
        }
        return amount;
    }

    private WorkingSolution workingSolution;

    private List<FluidInUse> containedFluids;
}
