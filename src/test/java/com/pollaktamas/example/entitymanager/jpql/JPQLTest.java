package com.pollaktamas.example.entitymanager.jpql;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class JPQLTest {

    @Autowired
    JPQLService jpqlService;

    @Test
    public void test() {
        // GIVEN
        Employee employee = new Employee();
        employee.setName("Janos");
        jpqlService.persist(employee);

        Employee employee2 = new Employee();
        employee2.setName("Andor");
        jpqlService.persist(employee2);

        Employee employee3 = new Employee();
        employee3.setName("Ferenc");
        jpqlService.persist(employee3);

        // WHEN
        List<Employee> results = jpqlService.findEmployeeByNameLike("an");

        // THEN
        assertTrue(results.size() == 2);
        assertTrue(results.contains(employee));
        assertTrue(results.contains(employee2));
    }
}
