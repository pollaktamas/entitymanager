package com.pollaktamas.example.entitymanager.containermanaged.propagation;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static javax.persistence.PersistenceContextType.TRANSACTION;
import static javax.transaction.Transactional.TxType.REQUIRED;

@Service
public class PropagationService1 {

    @Autowired
    PropagationService2 propagationService2;

    @PersistenceContext(type = TRANSACTION)
    EntityManager entityManager;

    /**
     * A propagationService2 ugyanabban a tranzakcióban vesz részt, így ugyanazt a PC-t fogja használni, ezért
     * kapunk ugyanarra az Employee-ra referenciát.
     */
    @Transactional(REQUIRED)
    public void method() {
        Employee employee = new Employee();
        entityManager.persist(employee);

        Employee employeeById = propagationService2.findEmployeeById(employee.getId());
        Assert.isTrue(employee == employeeById);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
