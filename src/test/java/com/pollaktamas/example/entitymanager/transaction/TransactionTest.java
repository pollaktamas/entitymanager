package com.pollaktamas.example.entitymanager.transaction;

import com.pollaktamas.example.entitymanager.containermanaged.transaction.ExampleService_Transaction;
import com.pollaktamas.example.entitymanager.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
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
