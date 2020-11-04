package employee_payroll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cg.empdatabase.EmployeePayrollDBService;
import com.cg.empdatabase.EmployeePayrollException;

public class EmployeePayrollService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO;
	}

	private List<EmployeePayrollData> employeePayrollList;
	private EmployeePayrollDBService employeePayrollDBService;

	public EmployeePayrollService() {
		employeePayrollDBService = EmployeePayrollDBService.getInstance();
	}

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
		return employeePayrollList.size();
	}

	public void printData(IOService ioService) {
		if(ioService.equals(IOService.FILE_IO))
			new EmployeePayrollFileIOOperations().printEmployeePayrollData();
		else System.out.println(employeePayrollList);

	}

	public List<EmployeePayrollData> readData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new EmployeePayrollFileIOOperations().readEmployeePayrollData();
		else
			return null;
	}

	public List<EmployeePayrollData> readEmpPayrollData(IOService ioService) throws EmployeePayrollException {
		if (ioService.equals(IOService.DB_IO))
			employeePayrollList = employeePayrollDBService.readData();
		return employeePayrollList;
	}

	public boolean checkEmployeePayrollInSyncWithDB(String name) {
		List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getEmployeePayrollData(name);
		return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
	}

	public void updateEmployeeSalary(String name, double salary) {
		int result = employeePayrollDBService.updateEmployeeData(name, salary);
		if (result == 0)
			return;
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if (employeePayrollData != null)
			employeePayrollData.salary = salary;
	}

	private EmployeePayrollData getEmployeePayrollData(String name) {
		EmployeePayrollData employeePayrollDataItem;
		employeePayrollDataItem = this.employeePayrollList.stream()
				.filter(employeePayrollData -> employeePayrollData.getName().equals(name)).findFirst().orElse(null);
		return employeePayrollDataItem;
	}

	public List<EmployeePayrollData> getEmployeePayrollDataForDateRange(LocalDate startDate, LocalDate endDate) {
		return employeePayrollDBService.getEmployeePayrollDataForDateRange(startDate, endDate);
	}

	public double getSumByGender(IOService ioService, String gender) throws EmployeePayrollException {
		double sum = 0.0;
		if (ioService.equals(IOService.DB_IO))
			return employeePayrollDBService.getSumByGender(gender);
		return sum;
	}

	public double getEmpDataGroupedByGender(IOService ioService, String column, String operation, String gender) {
		if (ioService.equals(IOService.DB_IO))
			return employeePayrollDBService.getEmpDataGroupedByGender(column, operation, gender);
		return 0.0;
	}

	public void addEmployeeToPayroll(String name, double salary, LocalDate start, String gender,
			List<String> deptList) {
		employeePayrollList.add(employeePayrollDBService.addEmpToPayroll(name, salary, start, gender, deptList));

	}

	public void addEmployeeToPayroll(List<EmployeePayrollData> empPayrollList) {
		empPayrollList.forEach(empPayrollData -> {
		this.addEmployeeToPayroll(empPayrollData.getName(), empPayrollData.getSalary(),
					empPayrollData.getStartDate(), empPayrollData.getGender(), empPayrollData.getDeptList());
		System.out.println("Emp Added:" + empPayrollData.getName());
		});
		//System.out.println(this.employeePayrollList);
	}

	public void remove(String name) throws EmployeePayrollException {
		employeePayrollDBService.remove(name);
	}

	public void addEmpToPayrollWithThreads(List<EmployeePayrollData> employeePayrollDataList) throws EmployeePayrollException {
		Map<Integer, Boolean> employeeAdditionStatus = new HashMap<Integer, Boolean>();
	    employeePayrollDataList.forEach(employeePayrollData -> {
	    	employeeAdditionStatus.put(employeePayrollData.hashCode(), false);
	    	Runnable task = () -> {
	    	  System.out.println("Employee Being Added : "+ Thread.currentThread().getName());
	    	  this.addEmployeeToPayroll(employeePayrollData.getName(), employeePayrollData.getSalary(), employeePayrollData.getStartDate(),
					  employeePayrollData.getGender(), employeePayrollData.getDeptList());
			   employeeAdditionStatus.put(employeePayrollData.hashCode(), true);
	    	  System.out.println("Employee Added : "+ Thread.currentThread().getName());
	    };
	    Thread thread = new Thread(task, employeePayrollData.getName());
	    thread.start();
	      });
	    while(employeeAdditionStatus.containsValue(false)) {
	    	try{Thread.sleep(10);
	    	}catch(InterruptedException e) {
	    		e.printStackTrace();
	    	}
	    }
	    System.out.println(this.employeePayrollList);
	    }
}

