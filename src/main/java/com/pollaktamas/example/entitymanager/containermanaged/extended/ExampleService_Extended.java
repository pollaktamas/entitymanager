package com.pollaktamas.example.entitymanager.containermanaged.extended;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static javax.persistence.PersistenceContextType.EXTENDED;
import static javax.transaction.Transactional.TxType.*;

@Service
public class ExampleService_Extended {

    @PersistenceContext(type = EXTENDED)
    EntityManager entityManager;

    @Transactional(REQUIRED)
    public void persist(Employee employee) {
        entityManager.persist(employee);
    }

    @Transactional(NEVER)
    public Employee findOutSideOfTransaction(long primaryKey) {
        return entityManager.find(Employee.class, primaryKey);
    }

    @Transactional(REQUIRES_NEW)
    public Employee findInNewTransaction(long primaryKey) {
        return entityManager.find(Employee.class, primaryKey);
    }

    @Transactional(NEVER)
    public void modifyOutsideOfTransaction(Employee employee, String name) {
        employee.setName(name);
    }

    @Transactional(REQUIRED)
    public void methodInTransaction() {
        // Egy dummy EntityManager művelet, hogy csatlakozzunk az aktuális tranzakcióhoz
        entityManager.contains(new Employee());
    }
}