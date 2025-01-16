/**
 * 
 */
package student;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.inject.Named;

import service.EmployeeService;
import student.dto.EmployeeDto;

/**
 * @author cuongbd
 *
 */

@SessionScoped
@Named("test01Bean")
public class Test01Bean implements Serializable {
	
	
	private EmployeeDto employeeDto = new EmployeeDto();
	
	@EJB
	private EmployeeService empployeeService;
	
	private DataModel dataModel;
	
	public void search() {
		employeeDto = new EmployeeDto();
	}
	
	
	/**
	 * @return the employeeDto
	 */
	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	/**
	 * @param employeeDto the employeeDto to set
	 */
	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}
	
	public String getMessage() {
	      return "Hi there!";
	  }

	  public Date getTime() {
	      return new Date();
	  }
	
}
