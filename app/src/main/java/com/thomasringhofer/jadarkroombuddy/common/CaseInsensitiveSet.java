package com.thomasringhofer.jadarkroombuddy.common;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Thomas on 24.03.2018.
 * Set Implementation where the given String will be handled case insensitive.
 */
public class CaseInsensitiveSet extends HashSet<String> {

    public CaseInsensitiveSet(){
    }

    public CaseInsensitiveSet(List<String> list){
        if(list == null) throw new IllegalArgumentException();
        if(!addAll(list)){
            throw new IllegalStateException("Non unique entries provided");
        }
    }

    @Override
    public boolean add(String s) {
        return super.add(s.toLowerCase());
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {

        boolean result = true;

        for (String s:c) {
            result &= this.add(s);
        }
        return result;
    }

    @Override
    public boolean contains(Object o) {

        if(o == null) throw new IllegalArgumentException();
        return super.contains(o.toString().toLowerCase());
    }
}
