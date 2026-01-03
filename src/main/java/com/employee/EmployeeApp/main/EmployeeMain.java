package com.employee.EmployeeApp.main;

import java.util.Scanner;

import com.employee.EmployeeApp.employeeDAO.EmployeeDAO;
import com.employee.EmployeeApp.entites.DepartmentDTO;
import com.employee.EmployeeApp.entites.EmployeeDTO;
import com.employee.EmployeeApp.service.EmployeeService;

import jakarta.persistence.Query;

public class EmployeeMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		EmployeeService empService = new EmployeeService();
		
		boolean b=true;
		while(b) {
			System.out.println();
			System.out.println("======================================");
			System.out.println("           EMPLOYEE MENU               ");
			System.out.println("======================================");
			System.out.println("\t 1. Insert Employee");
			System.out.println("\t 2. Update Employee");
			System.out.println("\t 3. Delete Employee");
			System.out.println("\t 4. Display Employee");
			System.out.println("\t 5. Display All Employees");
			System.out.println("\t 6. Search Employee by Name");
			System.out.println("\t 7. View Employee Change History");
			System.out.println("\t 8. Exit");
			System.out.println("======================================");
			
			System.out.print("Enter your choice: ");

			int ch = sc.nextInt();
		
	   switch(ch) {
		
		case 1->{
		//insert
			 System.out.println("Enter Name : ");
	    	  String name = sc.next();
	    	  System.out.println("Enter Designation : ");
	    	  String des = sc.next();
	    	  System.out.println("Enter salary : ");
	    	  double salary = sc.nextDouble();
	    	  System.out.println("Enter Department Name :");
	    	  String deptName = sc.next();
	    	  
	    	  DepartmentDTO dept = new DepartmentDTO();
	    	  dept.setDeptName(deptName);
	    	  
	     EmployeeDTO eobj = new EmployeeDTO();
	     eobj.setEmpName(name);
	     eobj.setSalary(salary);
	     eobj.setEmpDesg(des);
	    
	    dept.addEmployee(eobj);
		empService.insert(dept);
		}
		
		case 2->{
		//update
		System.out.println("Enter Employee ID: ");
		int empid=sc.nextInt();
		empService.update(empid);
		}
		
		case 3->{
		//delete
		System.out.println("Enter Employee ID: ");
		int id=sc.nextInt();
		empService.delete(id);
		}
		
		case 4->{
			//view
			System.out.println("Enter Employee ID : ");
			int id = sc.nextInt();
			empService.display(id);
		}
		case 5->{
		empService.displayAll();
		}
		
		case 6->{
			System.out.println("Enter Employee Name :");
			String name = sc.next();
			empService.search(name);
		}
		case 7->{
			System.out.println("Enter Employee ID : ");
			int id = sc.nextInt();
			
			
			empService.updateEmpSalary(id);
		}
		case 8->{
			System.out.println("Goodbye!!!");
			b=false;
		}
		}
		}
	}
}
