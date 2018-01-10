package com.pollaktamas.example.entitymanager.jpql;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Service
@Repository
public class JPQLService {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(REQUIRED)
    public void persist(Employee employee) {
        entityManager.persist(employee);
    }

    public List<Employee> findEmployeeByNameLike(String namePart) {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE :namePart ", Employee.class);
        query.setParameter("namePart", "%" + namePart.toLowerCase() + "%");

        return query.getResultList();
    }
}
