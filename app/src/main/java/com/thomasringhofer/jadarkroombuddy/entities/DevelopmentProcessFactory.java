package com.thomasringhofer.jadarkroombuddy.entities;

import com.thomasringhofer.jadarkroombuddy.common.IIdGenerator;

/**
 *
 * Created by Thomas on 10.02.2018.
 */
public class DevelopmentProcessFactory {

    public DevelopmentProcess CreateDefaultNegativeDevelopmentProcess(IIdGenerator idGenerator) throws IllegalArgumentException{
        if(idGenerator==null)throw new IllegalArgumentException("idGenerator must not be null!");
        DevelopmentProcess developmentProcess = createDefaultDevelopmentProcess(idGenerator);
        developmentProcess.setType(DevelopmentProcess.TYPE_NEGATIVE);
        return developmentProcess;
    }

    public DevelopmentProcess CreateDefaultPositiveDevelopmentProcess(IIdGenerator idGenerator) throws IllegalArgumentException{
        if(idGenerator==null)throw new IllegalArgumentException("idGenerator must not be null!");
        DevelopmentProcess developmentProcess = createDefaultDevelopmentProcess(idGenerator);
        developmentProcess.setType(DevelopmentProcess.TYPE_POSITIVE);
        return developmentProcess;
    }

    private DevelopmentProcess createDefaultDevelopmentProcess(IIdGenerator idGenerator) throws IllegalArgumentException{
        DevelopmentProcess developmentProcess = new DevelopmentProcess();
        developmentProcess.setId(idGenerator.Generate());
        developmentProcess.setCreationTimestamp(System.currentTimeMillis());

        return developmentProcess;
    }

}
