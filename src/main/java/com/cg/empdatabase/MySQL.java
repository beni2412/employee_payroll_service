package com.cg.empdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Enumeration;

import java.sql.Driver;

public class MySQL {
	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
		String userName = "root";
		String password = "Harman24@";
		Connection connection=null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		listDrivers();

		try {
			System.out.println("Connecting to database:" + jdbcURL);
			connection = DriverManager.getConnection(jdbcURL, userName, password);
			System.out.println("Connection is succesful!!!" + connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = (Driver) driverList.nextElement();
			System.out.println("  " + driverClass.getClass().getName());
		}

	}
}
