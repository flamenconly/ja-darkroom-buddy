package com.thomasringhofer.jadarkroombuddy.entities;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

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
        serializedObject =  "{\"id\":\"id\",\"title\":\"test\",\"creationDate\":{\"date\":{\"year\":2028,\"month\":1,\"day\":1},\"time\":{\"hour\":20,\"minute\":50,\"second\":0,\"nano\":0}}}";
        deserializedObject = new Process();
        deserializedObject.setTitle("test");
        deserializedObject.setId("id");
        deserializedObject.setCreationDate(LocalDateTime.of(2028,1,1,20,50));
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
}
