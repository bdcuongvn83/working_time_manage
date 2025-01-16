/**
 * 
 */
package student;

import java.io.Console;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Employee;
import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
import service.EmployeeService;
import service.dto.LoginUser;
import student.dto.EmployeeDto;
import utils.FlashUtils;
import utils.NumberUtils;

/**
 * @author cuongbd
 *
 */
@ConversationScoped
@Named("emp02Bean")
@MyInterceptor
public class Emp02Bean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeDto employeeDto;
	private String mode = "new";
	
	@EJB
	private EmployeeService empployeeService;
//	
	
	@Inject @Login
	private LoginUser loginUser;
	
	public void init() {
		Long empId = NumberUtils.toLong(FlashUtils.getFlash("empId"));
		mode = "new";
		if (empId != null) {
			// mode edit
			employeeDto = new EmployeeDto(empployeeService.getEmployee(empId));
			mode = "update";
		} else {
			System.out.println("mode update modify 2");
			System.out.println("mode update modify 3");
			
			// mode new
			resetData();
		}
	}
	private void resetData() {
		employeeDto = new EmployeeDto();
	}
	public String back() {
		
		return "back";
	}
	
	public String register() {
		Employee emp = new Employee();
		employeeDto.copyToEntity(emp);
		empployeeService.registerEmployee(emp);
		
		//return "/facelets/empList.xhtml?faces-redirect=true";
		return "empList";
	}
	
	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}
	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
}
