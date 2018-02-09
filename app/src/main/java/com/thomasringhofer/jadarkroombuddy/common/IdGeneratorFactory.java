package com.thomasringhofer.jadarkroombuddy.common;

/**
 * Created by Thomas on 09.02.2018.
 */

public class IdGeneratorFactory {

    private static IdGeneratorFactory INSTANCE;

    public static IdGeneratorFactory GetInstance(){

        if(INSTANCE == null)INSTANCE = new IdGeneratorFactory();
        return INSTANCE;

    }

    public IIdGenerator GetDefaultGenerator(){
        return new DefaultIdGenerator();
    }

}
