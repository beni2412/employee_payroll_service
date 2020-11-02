package employee_payroll;

import java.time.LocalDate;

public class EmployeePayrollData {
	private int id;
	private String name;
	public double salary;
	private LocalDate startDate;

	public EmployeePayrollData(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public EmployeePayrollData(int id, String name, double salary, LocalDate startDate) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.startDate = startDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", salary=" + salary + ", startDate=" + startDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EmployeePayrollData that = (EmployeePayrollData) o;
		return id == that.id && Double.compare(that.salary, salary) == 0 && name.equals(that.name);
	}
}
