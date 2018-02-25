package com.thomasringhofer.jadarkroombuddy.exceptions;

/**
 * Exception to be thrown if a persisting command fails.
 * Created by Thomas on 25.02.2018.
 */
public class PersistEntityFailedException extends Exception {

    public PersistEntityFailedException(){super();}

    public PersistEntityFailedException(String message){super(message);}

    public PersistEntityFailedException(String message,Throwable innerException){super(message,innerException);}

}
