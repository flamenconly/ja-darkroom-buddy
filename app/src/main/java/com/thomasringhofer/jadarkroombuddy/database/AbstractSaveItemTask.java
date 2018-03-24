package com.thomasringhofer.jadarkroombuddy.database;

import com.thomasringhofer.jadarkroombuddy.common.AsynchronousHandler;

/**
 * Created by Thomas on 24.03.2018.
 */

public abstract class AbstractSaveItemTask<T> implements Runnable {

    protected T item;
    protected  AsynchronousHandler asynchronousHandler;

    public AbstractSaveItemTask(T item, AsynchronousHandler asynchronousHandler){
        if(item == null) throw new IllegalArgumentException("item");
        this.item = item;
        this.asynchronousHandler = asynchronousHandler;
    }

    @Override
    public void run() {

        if(itemCanBeSaved(item)){
            if(saveItem(item)) {
                asynchronousHandler.onSuccessCallback();
                return;
            }
        }

        asynchronousHandler.onFailureCallback();

    }

    protected abstract boolean saveItem(T item);

    protected abstract boolean itemCanBeSaved(T item);




}
