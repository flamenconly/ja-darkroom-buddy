package com.thomasringhofer.jadarkroombuddy.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testclass for the object Process.
 * Created by Thomas on 09.02.2018.
 */

public class ProcessTest {

    private String serializedObject;
    private Process deserializedObject;

    @Before
    public void setUp(){
        serializedObject =  "{\"type\":\"Negative\",\"id\":\"id\",\"title\":\"test\",\"creationTimestamp\":1830372600000}";
        deserializedObject = new Process();
        deserializedObject.setTitle("test");
        deserializedObject.setId("id");
        deserializedObject.setType(Process.TYPE_NEGATIVE);
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
        Process awaitedProcess = deserializedObject;
        String resultString =serializedObject;
        Process resultProcess = new Process().deserialize(resultString);
        assertEquals(awaitedProcess,resultProcess);
    }

    @Test(expected = IllegalArgumentException.class)
    public void type_illegal_argument_exception() throws Exception{
        Process process = new Process();
        process.setType("totally wrong type");
    }

    @Test
    public void type_legal_arguments() throws Exception{
        Process process = new Process();
        process.setType(Process.TYPE_NEGATIVE);
        assertEquals(process.getType(),Process.TYPE_NEGATIVE);

        process.setType(Process.TYPE_POSITIVE);
        assertEquals(process.getType(),Process.TYPE_POSITIVE);
    }
}
