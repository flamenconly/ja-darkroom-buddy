package com.thomasringhofer.jadarkroombuddy.entities;

/**
 * Created by Thomas on 09.02.2018.
 * Interface for Objects which provides a json representation.
 */
interface JsonSerializable<T> {

    T deserialize(String jsonValue);

    String serialize();

}
