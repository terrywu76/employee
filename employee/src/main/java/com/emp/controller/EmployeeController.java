package com.emp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.model.Employee;
import com.emp.repository.EmployeeRepository;
import com.emp.service.EmployeeService;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmployeeController {

	// 透過 @RequestMapping 指定從/會被對應到此addMemberPage()方法
//	@Autowired
//	MemberAccount memberAccount;
//	
//	@Autowired
//	MemberService memberService;
//	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeService employeeService;

//	@Autowired
//	DataSource dataSource;
	
	@RequestMapping(value = "/queryEmployee")
	@ResponseBody
	public List<Employee> queryEmployee(@RequestBody Employee employee) {
		Page<Employee> result = employeeService.queryEmployee(employee);
		List<Employee> r = result.getContent();
		return r;
	}

//	@GetMapping("/findAllEmployee")
	@RequestMapping(value = "/findAllEmployee")
	public List<Employee> findAllEmployee() {

		List<Employee> employees = new ArrayList<Employee>();
		employees = employeeRepository.findAll();

//		for(int i=0;i<employees.size();i++){
//			System.out.println(employees.get(i).getId());
//		}
		return employees;
	}

	@RequestMapping("/findEmployeeById")
	@ResponseBody
	public Employee findEmployeeById(Integer id) {
		Employee result = new Employee();
		result = employeeService.findEmployeeById(id);
		//resultMap.put("result", result);
		return result;
	}
	
	@RequestMapping("/addEmployee")
	@ResponseBody
	public Employee addEmployee(@RequestBody Employee employee) {
		Employee result = new Employee();
		result = employeeService.addEmployee(employee);
		return result;
	}
	
	@RequestMapping("/updateEmployee")
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee employee) {
		Employee result = new Employee();
		result = employeeService.updateEmployee(employee);
		return result;
	}
	
	@RequestMapping("/deleteEmployee")
	@ResponseBody
	public String deleteEmployee(Integer id) {
		employeeService.deleteEmployee(id);
		return "success";
	}

}
