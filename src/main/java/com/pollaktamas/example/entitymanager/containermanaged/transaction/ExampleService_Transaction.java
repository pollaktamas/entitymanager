package com.pollaktamas.example.entitymanager.containermanaged.transaction;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static javax.persistence.PersistenceContextType.TRANSACTION;
import static javax.transaction.Transactional.TxType.*;

@Service
public class ExampleService_Transaction {

    @PersistenceContext(type = TRANSACTION)
    EntityManager entityManager;

    /**
     * Egy PersistenceContext-en belül egy adott entitásból csak egy példány létezhet.
     * <p>
     * Ha egy entitás már a PersistenceContext része, akkor bármely, az entitás betöltését igénylő
     * művelet során a PersistenceProvider a PC-ben lévő példányt használja. Nincs szükség adatbázis hívásra.
     */
    @Transactional(REQUIRED)
    public void persist(Employee employee) {
        entityManager.persist(employee);
        Employee employee1 = entityManager.find(Employee.class, employee.getId());
        Employee employee2 = entityManager.find(Employee.class, employee.getId());

        Assert.isTrue(employee == employee1);
        Assert.isTrue(employee1 == employee2);
    }

    @Transactional(NOT_SUPPORTED)
    public Employee findOutSideOfTransaction(long primaryKey) {
        return entityManager.find(Employee.class, primaryKey);
    }

    @Transactional(REQUIRES_NEW)
    public Employee findInNewTransaction(long primaryKey) {
        return entityManager.find(Employee.class, primaryKey);
    }
}