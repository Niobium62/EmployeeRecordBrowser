
public class Employee {
	private int empId;
	private String name;
	private String telephone;
	private int years;
	/**
	 * @param empId
	 * @param name
	 * @param telephone
	 * @param years
	 */
	public Employee(int empId, String name, String telephone, int years) {
		this.empId = empId;
		this.name = name;
		this.telephone = telephone;
		this.years = years;
	}
	@Override
	public String toString() {
		return "Employee ID: " + empId + ", Name: " + name + ", Telephone: " + telephone + ", Years: " + years;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
}
