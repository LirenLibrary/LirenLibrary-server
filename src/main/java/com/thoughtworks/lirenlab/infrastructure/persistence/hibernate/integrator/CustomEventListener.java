package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate.integrator;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;

public class CustomEventListener implements PostInsertEventListener, PostLoadEventListener {

    @Override
    public void onPostLoad(PostLoadEvent event) {
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
    }
}