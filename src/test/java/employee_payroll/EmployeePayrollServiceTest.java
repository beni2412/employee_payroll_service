package employee_payroll;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import employee_payroll.EmployeePayrollService.IOService;

public class EmployeePayrollServiceTest {
	@Test
	public void given3EmployeeEntries_ShouldMatchTheEmployeeEntries_WhenWrittenToTheFile() {
		EmployeePayrollData[] empArray = { new EmployeePayrollData(200456, "Warren Buffet", 500000.0),
				new EmployeePayrollData(200457, "Amanico Ortega", 200000.0),
				new EmployeePayrollData(200551, "Larry Ellison", 800000.0) };
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(empArray));
		employeePayrollService.writeData(IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(IOService.FILE_IO);
		assertEquals(3, entries);
	}
}