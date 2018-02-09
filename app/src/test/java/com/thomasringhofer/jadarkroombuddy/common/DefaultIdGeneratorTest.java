package com.thomasringhofer.jadarkroombuddy.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Thomas on 09.02.2018.
 */
public class DefaultIdGeneratorTest {
    @Test
    public void generate_10000_unequal() throws Exception {

        IIdGenerator generator = new DefaultIdGenerator();

        Set<String> ids = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            String id = generator.Generate();
            if(ids.contains(id)){
                Assert.assertTrue("In 10000 similar ids where generated!",false);
            }
        }

    }

}