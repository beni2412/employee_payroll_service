package employee_payroll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//This class performs employee payroll IO service and operations 
public class EmployeePayrollFileIOOperations {

	public static String PAYROLL_TEXT_FILE = "src/PayrollExamples.txt";

	public void writeEmployeePayrollData(List<EmployeePayrollData> employeeList) {
		StringBuffer buffer = new StringBuffer();
		employeeList.forEach(employee -> {
			String employeeDataString = employee.toString().concat("\n");
			buffer.append(employeeDataString);
		});
		try {
			Files.write(Paths.get(PAYROLL_TEXT_FILE), buffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long countNoOfEntries() {
		long entries = 0;
		try {
			entries = Files.lines(new File(PAYROLL_TEXT_FILE).toPath()).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}
	
	public void printEmployeePayrollData() {
		try {
			Files.lines(new File(PAYROLL_TEXT_FILE).toPath()).forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
