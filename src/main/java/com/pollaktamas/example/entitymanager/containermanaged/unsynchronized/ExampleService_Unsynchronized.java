package com.pollaktamas.example.entitymanager.containermanaged.unsynchronized;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static javax.persistence.PersistenceContextType.EXTENDED;
import static javax.persistence.SynchronizationType.UNSYNCHRONIZED;
import static javax.transaction.Transactional.TxType.REQUIRED;

@Service
public class ExampleService_Unsynchronized {

    @PersistenceContext(type = EXTENDED, synchronization = UNSYNCHRONIZED)
    EntityManager entityManager;

    @Transactional(REQUIRED)
    public void persist(Employee employee) {
        entityManager.persist(employee);
    }

    @Transactional(REQUIRED)
    public void commit() {
        entityManager.joinTransaction();
    }
}
