package com.pollaktamas.example.entitymanager.repositories;

import com.pollaktamas.example.entitymanager.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
}
