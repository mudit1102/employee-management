package com.work.management.service;

public interface DataPersistingService<T,V> {

    public T preHandle(T document);

    public V handle(T document);

    public void postHandle(V document);

   default public void process(T document){
        postHandle(handle(preHandle(document)));
    }
}
