/**
 * 
 */
package student.dto;

import entity.Employee;

/**
 * @author cuongbd
 *
 */
public class EmployeeDto {
	
	private Long empId;
	
	private String userId;
	
	private String empName;
	
	private String email;
	
	private String password;

	
	/**
	 * 
	 */
	public EmployeeDto() {
		super();
	}

	public EmployeeDto(Employee emp) {
		
		this.setEmail(emp.getEmail());
		this.setEmpName(emp.getEmpName());
		this.setUserId(emp.getUserId());
		this.setPassword(emp.getPassword());
		this.setEmpId(emp.getEmpId());
	}
	
	public void copyToEntity(Employee emp) {
		if (emp == null) {
			return;
		}
		emp.setEmail(this.getEmail());
		emp.setEmpName(this.getEmpName());
		emp.setUserId(this.getUserId());
		emp.setPassword(this.getPassword());
		emp.setEmpId(this.getEmpId());
	}
	
	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
