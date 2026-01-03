package com.employee.EmployeeApp.entites;

import javax.annotation.processing.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class EmployeeDTO {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empID;
	private String empName;
	private String empDesg;
	private double salary;
	
	@ManyToOne
    @JoinColumn(name = "deptId", nullable=false)
    private DepartmentDTO department;
	
	
	public EmployeeDTO() {
		super();
	}
	
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDesg() {
		return empDesg;
	}
	public void setEmpDesg(String empDesg) {
		this.empDesg = empDesg;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public DepartmentDTO getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}
	@Override
	public  String toString() {
		return "EmployeeDTO [empID=" + empID + ", empName=" + empName + ", empDesg=" + empDesg + ", salary=" + salary
				+ "]";
	}
	
}
