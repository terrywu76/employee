package com.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
