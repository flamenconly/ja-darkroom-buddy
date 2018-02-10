package com.thomasringhofer.jadarkroombuddy.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testclass for the object DevelopmentProcess.
 * Created by Thomas on 09.02.2018.
 */

public class DevelopmentProcessTest {

    private String serializedObject;
    private DevelopmentProcess deserializedObject;

    @Before
    public void setUp(){
        serializedObject =  "{\"type\":\"Negative\",\"id\":\"id\",\"title\":\"test\",\"creationTimestamp\":1830372600000}";
        deserializedObject = new DevelopmentProcess();
        deserializedObject.setTitle("test");
        deserializedObject.setId("id");
        deserializedObject.setType(DevelopmentProcess.TYPE_NEGATIVE);
        deserializedObject.setCreationTimestamp(1830372600000l);
    }

    @Test
    public void serialization_is_correct() throws Exception{
        String awaitedString = serializedObject;
        String resultString = deserializedObject.serialize();
        assertEquals(awaitedString, resultString);
    }

    @Test
    public void deserialization_is_correct(){
        DevelopmentProcess awaitedDevelopmentProcess = deserializedObject;
        String resultString =serializedObject;
        DevelopmentProcess resultDevelopmentProcess = new DevelopmentProcess().deserialize(resultString);
        assertEquals(awaitedDevelopmentProcess, resultDevelopmentProcess);
    }

    @Test(expected = IllegalArgumentException.class)
    public void type_illegal_argument_exception() throws Exception{
        DevelopmentProcess developmentProcess = new DevelopmentProcess();
        developmentProcess.setType("totally wrong type");
    }

    @Test
    public void type_legal_arguments() throws Exception{
        DevelopmentProcess developmentProcess = new DevelopmentProcess();
        developmentProcess.setType(DevelopmentProcess.TYPE_NEGATIVE);
        assertEquals(developmentProcess.getType(), DevelopmentProcess.TYPE_NEGATIVE);

        developmentProcess.setType(DevelopmentProcess.TYPE_POSITIVE);
        assertEquals(developmentProcess.getType(), DevelopmentProcess.TYPE_POSITIVE);
    }
}
