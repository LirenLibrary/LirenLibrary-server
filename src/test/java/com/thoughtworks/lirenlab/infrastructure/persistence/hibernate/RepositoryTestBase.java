package com.thoughtworks.lirenlab.infrastructure.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class RepositoryTestBase {
    @Mock
    protected Session session;

    @Mock
    protected SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        given(sessionFactory.getCurrentSession()).willReturn(session);
        moreSetUp();
    }

    protected void moreSetUp() {
    }
}
