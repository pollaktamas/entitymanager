package com.pollaktamas.example.entitymanager.containermanaged.propagation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class PropagationTest {

    @Autowired
    PropagationService1 propagationService1;

    @Autowired
    PropagationService2 propagationService2;

    /**
     * A PropagationService1 és PropagationService2 osztályok más EntityManager példányokat hívhatnak, de mivel
     * egy tranzakcióban vesznek részt, így propagálódik a PC és ugyanazon entitáshalmazon dolgoznak.
     */
    @Test
    public void testPropagation() {
        propagationService1.method();
    }
}
