package employee_payroll;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cg.empdatabase.EmployeePayrollException;

import employee_payroll.EmployeePayrollService.IOService;

public class EmployeePayrollDBTest {
	@Test
	public void givenEmployeePayrollDB_shouldReturnCount() throws EmployeePayrollException {
		EmployeePayrollService empPayRollService = new EmployeePayrollService();
		List<EmployeePayrollData> empPayrollList = empPayRollService.readEmpPayrollData(IOService.DB_IO);
		Assert.assertEquals(3, empPayrollList.size());
	}
	@Test
	public void givenNewSalayForEmployee_WhenUpdated_shouldMatch() throws EmployeePayrollException{
		EmployeePayrollService empPayRollService = new EmployeePayrollService();
		List<EmployeePayrollData> empPayrollList = empPayRollService.readEmpPayrollData(IOService.DB_IO);
		 empPayRollService.updateEmployeeSalary("Terisa",3000000.0);
		 boolean result = empPayRollService.checkEmployeePayrollInSyncWithDB("Terisa");
		 Assert.assertTrue(result);
	}
}