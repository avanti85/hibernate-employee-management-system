package com.employee.EmployeeApp.employeeDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.employee.EmployeeApp.entites.DepartmentDTO;
import com.employee.EmployeeApp.entites.EmployeeAudit;
import com.employee.EmployeeApp.entites.EmployeeDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class EmployeeDAO {


	    private static EntityManagerFactory factory =
	            Persistence.createEntityManagerFactory("hibernate");

	    private static EntityManager manager;
	    private static EntityTransaction transaction;

	    public static void openConnection() {
	        manager = factory.createEntityManager();
	        transaction = manager.getTransaction();
	    }

	    public static void closeConnection() {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        manager.close();
	    }

	    public static EntityManager getManager() {
	        return manager;
	    }
	}
