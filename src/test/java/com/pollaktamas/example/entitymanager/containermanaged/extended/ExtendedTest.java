package com.pollaktamas.example.entitymanager.containermanaged.extended;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ExtendedTest {

    @Autowired
    ExampleService_Extended exampleService;

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    @Test
    public void test() {
        Employee employee = new Employee();
        employee.setName("Bob");
        exampleService.persist(employee);

        // Ugyanaz a PC van használatban, így ugyanarra az objektumra kapunk referenciát
        assertTrue(employee == exampleService.findOutSideOfTransaction(employee.getId()));
        assertTrue(employee == exampleService.findInNewTransaction(employee.getId()));

        exampleService.modifyOutsideOfTransaction(employee, "Frank");

        // Az Employee neve tranzakción kívül változott meg, így a változtatások még csak a memóriában léteznek
        EntityManager appManagedEntityManager = entityManagerFactory.createEntityManager();
        Employee persistedEmployee = appManagedEntityManager.find(Employee.class, employee.getId());
        assertNotEquals("Frank", persistedEmployee.getName());

        // Egy tranzakcióban futó metódus végén commit történik, amely során megtörénik az adatbázisba írás
        exampleService.methodInTransaction();

        appManagedEntityManager.clear();
        Employee modifiedEmployee = appManagedEntityManager.find(Employee.class, employee.getId());
        assertEquals("Frank", modifiedEmployee.getName());
    }
}
