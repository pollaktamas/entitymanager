package com.pollaktamas.example.entitymanager.containermanaged.transaction;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class TransactionTest {

    @Autowired
    ExampleService_Transaction exampleService;

    /**
     * A findOutSideOfTransaction és findInNewTransaction metódusok nem ugyanabban a tranzakcióban fognak lefutni, mint a persist.
     * A lefutásuk során más PersistenceContex-et fog használni az entity manager, amiben nem létezik az előzőleg elmentett
     * Employee példány, így az alkalmazás lekérdezi az adatábzisból, és egy másik példányra kapunk referenciát.
     * <p>
     * (Ha a persist-ból hívnánk a findInNewTransaction-t, a findInNewTransaction-n belül akkor is egy új PC-vel dolgozna az entity manager).
     */
    @Test
    public void test() {
        Employee employee = new Employee();
        exampleService.persist(employee);

        assertTrue(employee != exampleService.findOutSideOfTransaction(employee.getId()));
        assertTrue(employee != exampleService.findInNewTransaction(employee.getId()));
    }
}
