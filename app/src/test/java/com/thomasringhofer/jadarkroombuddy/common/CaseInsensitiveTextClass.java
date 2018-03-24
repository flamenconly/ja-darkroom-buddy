package com.thomasringhofer.jadarkroombuddy.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Thomas on 24.03.2018.
 */

public class CaseInsensitiveTextClass {

    @Test
    public void testCaseInsensitiveness(){

        Set<String> set = new CaseInsensitiveSet();
        set.add("HaLlo");
        Assert.assertTrue("",set.contains("hallo"));

    }

    @Test(expected = IllegalStateException.class)
    public void testCaseInsensitivenessForCollectionAtAdding(){

        List<String> list = new ArrayList();
        list.add("HaLlO");
        list.add("HaLlO");

        Set<String> set = new CaseInsensitiveSet(list);

    }

}
