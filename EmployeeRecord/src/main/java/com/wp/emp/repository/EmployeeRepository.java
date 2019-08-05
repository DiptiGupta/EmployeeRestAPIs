package com.wp.emp.repository;

import com.wp.emp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    Employee save(Employee user);

    Page<Employee> findAll(Pageable pageable);




}
