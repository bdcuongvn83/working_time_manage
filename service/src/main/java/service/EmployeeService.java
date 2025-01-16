/**
 * 
 */
package service;

import java.util.List;

import javax.ejb.Local;

import entity.Employee;

/**
 * @author cuongbd
 *
 */
@Local
public interface EmployeeService {
	public Employee getEmployee(Long empId);
	public void registerEmployee(Employee empParam);
	public List<Employee> getEmployeeList();
	public Employee findEmployee(String userId, String password);
	
	public void deleteEmployee(Long empId);
	public void removeEmployee(Long empId);
	
	
}
