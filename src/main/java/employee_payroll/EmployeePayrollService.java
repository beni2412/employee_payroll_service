package employee_payroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO;
	}

	private List<EmployeePayrollData> employeePayrollList;

	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}

	public static void main(String[] args) {
		System.out.println("Welcome");

		ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployeePayrollData(consoleInputReader);
		employeePayrollService.writeData(IOService.CONSOLE_IO);

	}

	public void readEmployeePayrollData(Scanner consoleInputReader) {
		System.out.println("Enter Employee ID: ");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter Employee name: ");
		String name = consoleInputReader.next();
		System.out.println("Enter Employee salary: ");
		double salary = consoleInputReader.nextDouble();
		employeePayrollList.add(new EmployeePayrollData(id, name, salary));
		
	}
	
	
		public void writeData(IOService ioService) {
			if (ioService.equals(IOService.CONSOLE_IO))
				System.out.println("Writing Employee Payroll Data to Console\n" + employeePayrollList);
			else if (ioService.equals(IOService.FILE_IO))
				new EmployeePayrollFileIOOperations().writeEmployeePayrollData(employeePayrollList);
		}

		
		public long countEntries(IOService ioService) {
			if (ioService.equals(IOService.FILE_IO))
				return new EmployeePayrollFileIOOperations().countNoOfEntries();
			return 0;
		}
		
		public void printData(IOService ioService) {
			new EmployeePayrollFileIOOperations().printEmployeePayrollData();
		}

}
