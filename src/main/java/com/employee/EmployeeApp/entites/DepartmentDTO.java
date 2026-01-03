package com.employee.EmployeeApp.entites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="department")
public class DepartmentDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deptId;
	
	private String deptName;
	
	@OneToMany(mappedBy="department",cascade = CascadeType.ALL)
	private List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();


	public void addEmployee(EmployeeDTO emp) {
	    employees.add(emp);
	    emp.setDepartment(this);
	}

	public DepartmentDTO() {
		super();
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}
	
}
