package com.thomasringhofer.jadarkroombuddy.entities;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testclass for the object Process.
 * Created by Thomas on 09.02.2018.
 */

public class ProcessTest {

    @Test
    public void serialization_is_correct() throws Exception{
        String awaitedString = "{\"title\":\"test\"}";
        Process process = new Process();
        process.setTitle("test");
        String resultString = process.serialize();
        assertEquals(awaitedString, resultString);
    }

    public void deserialization_is_correct(){

    }

}
