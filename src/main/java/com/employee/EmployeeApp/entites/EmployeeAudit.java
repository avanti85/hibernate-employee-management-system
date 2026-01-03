package com.employee.EmployeeApp.entites;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int auditId;
	
	private int empId;
	
	private String field;
	private String oldValue;
	private String newValue;
	private LocalDateTime changedAt;
	
	public EmployeeAudit() {
		
	}
	
	public EmployeeAudit(int auditId, int empId, String field, String oldValue, String newValue,
			LocalDateTime changedAt) {
		super();
		this.auditId = auditId;
		this.empId = empId;
		this.field = field;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.changedAt = changedAt;
	}

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}
	
	
}
