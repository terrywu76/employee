package com.emp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.model.Department;
import com.emp.repository.DepartmentRepository;
import com.emp.service.DepartmentService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class DepartmentController {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "/findAllDepartment")
	public List<Department> findAllDepartment() {

		List<Department> departments = new ArrayList<Department>();
		departments = departmentRepository.findAll();


		return departments;
	}

	@RequestMapping("/findDepartmentById")
	@ResponseBody
	public Department findDepartmentById(Integer id) {
		Department result = new Department();
		result = departmentService.findDepartmentById(id);
		//resultMap.put("result", result);
		return result;
	}
	
	@RequestMapping("/addDepartment")
	@ResponseBody
	public Department addDepartment(@RequestBody Department department) {
		Department result = new Department();
		result = departmentService.addDepartment(department);
		return result;
	}
	
	@RequestMapping("/updateDepartment")
	@ResponseBody
	public Department updateDepartment(@RequestBody Department department) {
		Department result = new Department();
		result = departmentService.updateDepartment(department);
		return result;
	}
	
	@RequestMapping("/deleteDepartment")
	@ResponseBody
	public String deleteDepartment(Integer id) {
		departmentService.deleteDepartment(id);
		return "success";
	}

}
