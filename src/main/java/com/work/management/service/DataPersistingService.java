package com.work.management.service;

import com.work.management.resource.ProjectResource;

public interface DataPersistingService<T,V> {

    public T preHandle(T document);

    public V handle(T document);

    public void postHandle(V document);

   default public void process(T document){
        postHandle(handle(preHandle(document)));
    }
}
