package com.pollaktamas.example.entitymanager.containermanaged.propagation;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static javax.persistence.PersistenceContextType.TRANSACTION;
import static javax.transaction.Transactional.TxType.REQUIRED;

@Service
public class PropagationService2 {

    @PersistenceContext(type = TRANSACTION)
    EntityManager entityManager;

    @Transactional(REQUIRED)
    public Employee findEmployeeById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
