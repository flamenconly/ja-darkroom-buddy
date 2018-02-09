package com.thomasringhofer.jadarkroombuddy.common;

import java.util.UUID;

/**
 * Created by Thomas on 09.02.2018.
 */

class DefaultIdGenerator implements IIdGenerator {

    @Override
    public String Generate() {
        return UUID.randomUUID().toString();
    }
}
