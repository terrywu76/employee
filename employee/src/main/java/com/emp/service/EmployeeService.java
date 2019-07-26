package com.emp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.emp.model.Department;
import com.emp.model.Employee;
import com.emp.repository.DepartmentRepository;
import com.emp.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Resource
	private EmployeeRepository employeeRepository;
	
	@Resource
	private DepartmentRepository departmentRepository;
	
	
	public Page<Employee> queryEmployee(Employee employee){
		Page<Employee> querlist = null;
		
		try {
			Pageable request = new PageRequest(employee.getPageNo(), employee.getPageSize(),
					Sort.Direction.DESC, "employeeNo");
			
			Specification<Employee> specification = new Specification<Employee>() {
				@Override
				public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					// TODO Auto-generated method stub
					
					Join<Employee, Department> join = root.join("department", JoinType.LEFT);
					
					List<Predicate> list = new ArrayList<Predicate>();
					
					if (StringUtils.isNotBlank(employee.getName())) {
						list.add(cb.equal(root.get("name"), employee.getName()));
					}
					if (StringUtils.isNotBlank(employee.getEmployeeNo())) {
						list.add(cb.equal(root.get("employeeNo"), employee.getEmployeeNo()));
					}
					if (employee.getAge() != null) {
						list.add(cb.equal(root.get("age"), employee.getAge()));
					}

					if (employee.getDepartment() != null && StringUtils.isNotBlank(employee.getDepartment().getDepartmentName())) {
						list.add(cb.equal(join.get("departmentName"), employee.getDepartment().getDepartmentName()));
					}

					return cb.and(list.toArray(new Predicate[list.size()]));
				}
			};
			
			querlist = employeeRepository.findAll(specification, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return querlist;
	}
	
	
	public List<Employee> findAllEmployee() {
		List<Employee> employees = new ArrayList<Employee>();
		employees = employeeRepository.findAll();
        return employees;
	}
	
	public Employee findEmployeeById(Integer id) {
		Employee employee = employeeRepository.findOne(id);
        return employee;
	}
	
	public List<Employee> findEmployee(Employee employee) {
		List<Employee> employees = employeeRepository.findAll();
        return employees;
	}
	
	public Employee addEmployee(Employee employee) {
//		Employee tmp = employeeRepository.findOne(employee.getId());
//		if(tmp != null) {
//			return null;
//		}
		employee.setCreateDate(Now());
		employee.setOperationDate(Now());
		Employee tmp = employeeRepository.save(employee);
        return tmp;
	}
	
	public Employee updateEmployee(Employee employee) {
		Employee tmp = employeeRepository.findOne(employee.getId());
		if(tmp == null) {
			return null;
		}
		employee.setCreateDate(tmp.getCreateDate());
		employee.setOperationDate(Now());
		tmp = employeeRepository.save(employee);
        return tmp;
	}
	
	public void deleteEmployee(Integer id) {
		employeeRepository.delete(id);
	}
	
	public String Now() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);
		return dateString;
	}

}
