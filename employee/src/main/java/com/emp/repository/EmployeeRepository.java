package com.emp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//	List<Employee> findAll();
	public Page<Employee> findAll(Specification<Employee> specification, Pageable request);
}
