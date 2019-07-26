package com.emp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
//	@Column(name = "DepartmentId")
//	private String departmentId;
	@Column(name = "DepartmentName")
	private String departmentName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
//	public String getDepartmentId() {
//		return departmentId;
//	}
//	public void setDepartmentId(String departmentId) {
//		this.departmentId = departmentId;
//	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	

}
