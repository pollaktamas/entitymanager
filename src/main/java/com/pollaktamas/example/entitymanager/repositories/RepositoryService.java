package com.pollaktamas.example.entitymanager.repositories;

import com.pollaktamas.example.entitymanager.model.Company;
import com.pollaktamas.example.entitymanager.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Service
public class RepositoryService {

    @Autowired EmployeeRepository employeeRepository;
    @Autowired CompanyRepository companyRepository;

    @Transactional(REQUIRED)
    public void method() {
        Employee employee = new Employee();
        Employee savedEmployee = employeeRepository.save(employee);

        employeeRepository.findOne(savedEmployee.getId());

        companyRepository.save(new Company());
    }
}
