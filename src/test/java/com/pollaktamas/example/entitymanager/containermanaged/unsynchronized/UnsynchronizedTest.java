package com.pollaktamas.example.entitymanager.containermanaged.unsynchronized;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class UnsynchronizedTest {

    @Autowired
    ExampleService_Unsynchronized exampleService;

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    @Test
    public void test() {
        Employee employee = new Employee();
        exampleService.persist(employee);

        // Hiába futott tranzakcióban a persist metódus, nem íródott db-be az entitás
        assertNull(employee.getId());

        exampleService.commit();

        assertNotNull(employee.getId());
    }
}
