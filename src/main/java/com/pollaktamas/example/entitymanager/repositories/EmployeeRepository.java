package com.pollaktamas.example.entitymanager.repositories;

import com.pollaktamas.example.entitymanager.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
}
