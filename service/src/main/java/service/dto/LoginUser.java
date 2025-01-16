/**
 * 
 */
package service.dto;

import java.io.Serializable;

import entity.Employee;

/**
 * @author cuongbd
 *
 */
public class LoginUser implements Serializable{
	
	private Employee employee;
	//cac thong tin chung common setting: locale
	
	/**
	 * 
	 */
	public LoginUser() {
		super();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
}
