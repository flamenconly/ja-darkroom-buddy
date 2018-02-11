package com.thomasringhofer.jadarkroombuddy.entities;

import com.thomasringhofer.jadarkroombuddy.common.IIdGenerator;

/**
 *
 * Created by Thomas on 10.02.2018.
 */
public class DevelopmentProcessFactory {

    public DevelopmentProcess CreateDefaultNegativeDevelopmentProcess() throws IllegalArgumentException{

        DevelopmentProcess developmentProcess = createDefaultDevelopmentProcess();
        developmentProcess.setType(DevelopmentProcess.TYPE_NEGATIVE);
        return developmentProcess;
    }

    public DevelopmentProcess CreateDefaultPositiveDevelopmentProcess() throws IllegalArgumentException{

        DevelopmentProcess developmentProcess = createDefaultDevelopmentProcess();
        developmentProcess.setType(DevelopmentProcess.TYPE_POSITIVE);
        return developmentProcess;
    }

    private DevelopmentProcess createDefaultDevelopmentProcess() throws IllegalArgumentException{
        DevelopmentProcess developmentProcess = new DevelopmentProcess();
        developmentProcess.setCreationTimestamp(System.currentTimeMillis());

        return developmentProcess;
    }

}
