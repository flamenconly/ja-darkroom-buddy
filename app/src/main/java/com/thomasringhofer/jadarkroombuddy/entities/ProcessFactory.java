package com.thomasringhofer.jadarkroombuddy.entities;

import com.thomasringhofer.jadarkroombuddy.common.IIdGenerator;

/**
 *
 * Created by Thomas on 10.02.2018.
 */
public class ProcessFactory {

    public Process CreateDefaultNegativeProcess(IIdGenerator idGenerator) throws IllegalArgumentException{
        if(idGenerator==null)throw new IllegalArgumentException("idGenerator must not be null!");
        Process process = createDefaultProcess(idGenerator);
        process.setType(Process.TYPE_NEGATIVE);
        return process;
    }

    public Process CreateDefaultPositiveProcess(IIdGenerator idGenerator) throws IllegalArgumentException{
        if(idGenerator==null)throw new IllegalArgumentException("idGenerator must not be null!");
        Process process = createDefaultProcess(idGenerator);
        process.setType(Process.TYPE_POSITIVE);
        return process;
    }

    private Process createDefaultProcess(IIdGenerator idGenerator) throws IllegalArgumentException{
        Process process = new Process();
        process.setId(idGenerator.Generate());
        process.setCreationTimestamp(System.currentTimeMillis());

        return process;
    }

}
