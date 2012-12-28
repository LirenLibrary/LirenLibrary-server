package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate.integrator;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * Did nothing, just as an example how to integrate with Hibernate
 */
public class CustomListenerIntegrator implements Integrator{

    @Override
    public void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        final EventListenerRegistry eventRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        eventRegistry.prependListeners(EventType.POST_INSERT, CustomEventListener.class);
        eventRegistry.prependListeners(EventType.POST_LOAD, CustomEventListener.class);
    }

    @Override
    public void integrate(MetadataImplementor metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
    }
}
