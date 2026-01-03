package com.employee.EmployeeApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.employee.EmployeeApp.employeeDAO.EmployeeDAO;
import com.employee.EmployeeApp.entites.DepartmentDTO;
import com.employee.EmployeeApp.entites.EmployeeAudit;
import com.employee.EmployeeApp.entites.EmployeeDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class EmployeeService {

	
	private static Scanner sc = new Scanner(System.in);
	
public void updateEmpSalary(int id) {
	
	EmployeeDAO.openConnection();
    EntityManager manager = EmployeeDAO.getManager();
    EntityTransaction transaction = manager.getTransaction();

    transaction.begin();
		
		EmployeeDTO emp = manager.find(EmployeeDTO.class, id);

		double oldSalary = emp.getSalary();
		double newSalary = 0.0;
		System.out.println("Umm, but what is ur desgination???");
		System.out.println("1.Enginneer");
		System.out.println("2.Developer");
		System.out.println("3.Tester");
		System.out.println("4.Data Analyst");
		int choice = sc.nextInt();
		
		switch(choice) {
		 
		case 1->{
			newSalary = oldSalary + (oldSalary * 30 /100);
			
		}
	    case 2->{
	    	
			newSalary = oldSalary + (oldSalary * 20 / 100);
		}
	    case 3->{
	    	newSalary = oldSalary + (oldSalary * 10 / 100);
	    }
	    case 4->{
	    	newSalary = oldSalary + (oldSalary * 25 / 100);
	    }
		}
		
		
		if(oldSalary != newSalary) {
			
			emp.setSalary(newSalary);
			manager.merge(emp);
			
			EmployeeAudit empAudit = new EmployeeAudit();
			empAudit.setEmpId(id);
			empAudit.setField("salary");
		empAudit.setOldValue(String.valueOf(oldSalary));
		empAudit.setNewValue(String.valueOf(newSalary));
			empAudit.setChangedAt(LocalDateTime.now());
			
			manager.persist(empAudit);
			
			System.out.println("-------------------------------------------------------------------------------");
			System.out.printf("%-10s %-10s %-15s %-15s %-15s %-20s%n",
			        "AUDIT ID", "EMP ID", "FIELD", "OLD VALUE", "NEW VALUE", "CHANGED AT");
			System.out.println("-------------------------------------------------------------------------------");

			System.out.printf("%-10d %-10d %-15s %-15s %-15s %-20s%n",
			        empAudit.getAuditId(),
			        empAudit.getEmpId(),
			        empAudit.getField(),
			        empAudit.getOldValue(),
			        empAudit.getNewValue(),
			        empAudit.getChangedAt()
			);

			System.out.println("-------------------------------------------------------------------------------");

			
		}
		transaction.commit();
	    EmployeeDAO.closeConnection();
	    }
	
	public void insert(DepartmentDTO dept) {
		EmployeeDAO.openConnection();
	    EntityManager manager = EmployeeDAO.getManager();
	    EntityTransaction transaction = manager.getTransaction();

	    transaction.begin();
			
		
		manager.persist(dept);
		transaction.commit();
	    EmployeeDAO.closeConnection();
	}
	
	public void update(int id) {
		EmployeeDAO.openConnection();
	    EntityManager manager = EmployeeDAO.getManager();
	    EntityTransaction transaction = manager.getTransaction();

	    transaction.begin();
			
		
	EmployeeDTO emp = manager.find(EmployeeDTO.class, id);
	
	Scanner sc = new Scanner(System.in);
	boolean b=true;
	
	  while(b) {
	System.out.println("What you want to Update??");
	System.out.println("1.Name \n2.Designation \n3.Salary. \n4.Exit.");
	int ch = sc.nextInt();
	
	    switch(ch) {
	      case 1->{
	          System.out.println("Enter new Name : ");
	    	  String name = sc.next();
	    	  
	          emp.setEmpName(name);
	          manager.merge(emp);
	          System.out.println("Name Updated Successfully...");
	      }
	      case 2->{
	    	  System.out.println("Enter new Designation : ");
	    	  String des=sc.next();
	    	  
	    	  emp.setEmpDesg(des);
	    	  manager.merge(emp);
	          System.out.println("Designation Updated Successfully...");
	      }
	      case 3->{
	    	  System.out.println("Enter new Salary : ");
	    	  double salary = sc.nextDouble();
	    	  
	    	  emp.setSalary(salary);
	    	  manager.merge(emp);
	          System.out.println("Salary Updated Successfully...");
	      }
	      case 4->{
	    	  System.out.println("Bye!");
	    	  b=false;
	      }
	      }
	      }
	  transaction.commit();
	    EmployeeDAO.closeConnection();
	}
	
	public void delete(int id) {
		EmployeeDAO.openConnection();
	    EntityManager manager = EmployeeDAO.getManager();
	    EntityTransaction transaction = manager.getTransaction();

	    transaction.begin();
			
		
		EmployeeDTO emp = manager.find(EmployeeDTO.class, id);
		manager.remove(emp);
		System.out.println(emp.getEmpName()+" removed!!");
		
		transaction.commit();
	    EmployeeDAO.closeConnection();
	}
	
	public void display(int empId) {
		EmployeeDAO.openConnection();
	    EntityManager manager = EmployeeDAO.getManager();
	    EntityTransaction transaction = manager.getTransaction();

	    transaction.begin();
			
		TypedQuery<EmployeeDTO> query =
		        manager.createQuery(
		            "SELECT e FROM EmployeeDTO e LEFT JOIN FETCH e.department WHERE e.empID = :id",
		            EmployeeDTO.class
		        );

		    query.setParameter("id", empId);
		    
		    EmployeeDTO emp;
		    
		    try {
		        emp = query.getSingleResult();
		    } catch (Exception e) {
		        System.out.println("Employee not found");
		        return;
		    }

		    
		    if (emp == null) {
		        System.out.println("❌ Employee with ID " + empId + " not found");
		        return;
		    }

		    System.out.println("------------------------------------------------");
		    System.out.println("Employee ID   : " + emp.getEmpID());
		    System.out.println("Name          : " + emp.getEmpName());
		    System.out.println("Designation   : " + emp.getEmpDesg());
		    System.out.println("Salary        : " + emp.getSalary());
		    System.out.println("Department    : " +
		            (emp.getDepartment() != null
		                    ? emp.getDepartment().getDeptName()
		                    : "N/A"));
		    System.out.println("------------------------------------------------");
		    
		    transaction.commit();
		    EmployeeDAO.closeConnection();
		
	}
	public void displayAll() {
		EmployeeDAO.openConnection();
	    EntityManager manager = EmployeeDAO.getManager();
	    EntityTransaction transaction = manager.getTransaction();

	    transaction.begin();
			
		
		TypedQuery<EmployeeDTO> query =
			    manager.createQuery(
			        "SELECT e FROM EmployeeDTO e LEFT JOIN FETCH e.department",
			        EmployeeDTO.class);

			List<EmployeeDTO> empList = query.getResultList();
		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("%-10s %-15s %-15s %-12s %-15s%n",
		        "EMP ID", "NAME", "DESIGNATION", "SALARY", "DEPARTMENT");
		System.out.println("-----------------------------------------------------------------------");

		empList.forEach(e -> {
		    String deptName = (e.getDepartment() != null)
		            ? e.getDepartment().getDeptName()
		            : "N/A";

		    System.out.printf("%-10d %-15s %-15s %-12.2f %-15s%n",
		            e.getEmpID(),
		            e.getEmpName(),
		            e.getEmpDesg(),
		            e.getSalary(),
		            deptName);
		});

		System.out.println("-----------------------------------------------------------------------");

		transaction.commit();
	    EmployeeDAO.closeConnection();
	}
	
	public void search(String name) {
		EmployeeDAO.openConnection();
	    EntityManager manager = EmployeeDAO.getManager();
	    EntityTransaction transaction = manager.getTransaction();

	    transaction.begin();
			
	    TypedQuery<EmployeeDTO> query =
	        manager.createQuery(
	            "SELECT e FROM EmployeeDTO e LEFT JOIN FETCH e.department " +
	            "WHERE LOWER(e.empName) LIKE LOWER(:name)",
	            EmployeeDTO.class);

	    query.setParameter("name", "%" + name + "%");

	    List<EmployeeDTO> empList = query.getResultList();

	    System.out.println("-----------------------------------------------------------------------");
	    System.out.printf("%-10s %-15s %-15s %-12s %-15s%n",
	            "EMP ID", "NAME", "DESIGNATION", "SALARY", "DEPARTMENT");
	    System.out.println("-----------------------------------------------------------------------");

	    if (empList.isEmpty()) {
	        System.out.println("No employee found");
	    } else {
	        empList.forEach(e -> {
	            String deptName = (e.getDepartment() != null)
	                    ? e.getDepartment().getDeptName()
	                    : "N/A";

	            System.out.printf("%-10d %-15s %-15s %-12.2f %-15s%n",
	                    e.getEmpID(),
	                    e.getEmpName(),
	                    e.getEmpDesg(),
	                    e.getSalary(),
	                    deptName);
	        });
	    }

	    System.out.println("-----------------------------------------------------------------------");

	    transaction.commit();
	    EmployeeDAO.closeConnection();
	}
}
