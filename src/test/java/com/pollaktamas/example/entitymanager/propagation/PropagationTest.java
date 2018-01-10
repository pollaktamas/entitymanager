package com.pollaktamas.example.entitymanager.propagation;

import com.pollaktamas.example.entitymanager.containermanaged.propagation.PropagationService1;
import com.pollaktamas.example.entitymanager.containermanaged.propagation.PropagationService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagationTest {

    @Autowired
    PropagationService1 propagationService1;

    @Autowired
    PropagationService2 propagationService2;

    /**
     * A PropagationService1 és PropagationService2 osztályok más EntityManager példányokkal rendelkeznek, de mivel
     * egy tranzakcióban vesznek részt, így propagálódik a PC és ugyanazon entitáshalmazon dolgoznak.
     */
    @Test
    public void testPropagation() {
        assertTrue(propagationService1.getEntityManager() != propagationService2.getEntityManager());
        propagationService1.method();
    }
}
