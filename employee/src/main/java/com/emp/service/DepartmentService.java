package com.emp.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emp.model.Department;
import com.emp.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Resource
	private DepartmentRepository departmentRepository;
	
	public List<Department> findAllDepartment() {
		List<Department> departments = new ArrayList<Department>();
		departments = departmentRepository.findAll();
        return departments;
	}
	
	public Department findDepartmentById(Integer id) {
		Department department = departmentRepository.findOne(id);
        return department;
	}
	
	public List<Department> findDepartment(Department department) {
		List<Department> departments = departmentRepository.findAll();
        return departments;
	}
	
	public Department addDepartment(Department department) {
//		Department tmp = departmentRepository.findOne(department.getId());
//		if(tmp != null) {
//			return null;
//		}
		Department tmp = departmentRepository.save(department);
        return tmp;
	}
	
	public Department updateDepartment(Department department) {
		Department tmp = departmentRepository.findOne(department.getId());
		if(tmp == null) {
			return null;
		}
		tmp = departmentRepository.save(department);
        return tmp;
	}
	
	public void deleteDepartment(Integer id) {
		departmentRepository.delete(id);
	}
	
	public String Now() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);
		return dateString;
	}

}
