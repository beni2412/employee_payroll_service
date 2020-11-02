package com.cg.empdatabase;

public class EmployeePayrollException extends Exception{

	enum ExceptionType{
		CONNECTION_ERROR, INCORRECT_INFO
	}

	ExceptionType type;

	public EmployeePayrollException(ExceptionType type, String message) {
		super(message);
		this.type = type;
	}
}