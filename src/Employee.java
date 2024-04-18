public class Employee {
	private int empID;
	private String empName;
	private String empAddress;
	private String empBirthDate;
	private double salary;
	public int count;

	public Employee(int empID, String empName, String empAddress, String empBirthDate, double salary) {
		this.empID = empID;
		this.empName = empName;
		this.empAddress = empAddress;
		this.empBirthDate = empBirthDate;
		this.salary = salary;
		count=0;
	}

	public int getEmpID() {
		return empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getEmpBirthDate() {
		return empBirthDate;
	}

	public void setEmpBirthDate(String empBirthDate) {
		this.empBirthDate = empBirthDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	

	public int getCount() {
		return count;
	}

	public void setCount() {
		count++;
	}

	@Override
	public String toString() {
		return  "empID=" + empID + ", empName='" + empName + ", empAddress='" + empAddress
				+ ", empBirthDate='" + empBirthDate + ", salary=" + salary;
	}
}