/**
 * 
 */
package student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Employee;
import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
import service.EmployeeService;
import service.dto.LoginUser;
import student.dto.EmployeeDto;
import utils.FlashUtils;

/**
 * @author cuongbd
 *
 */

@ConversationScoped
@Named("emp01Bean")
@MyInterceptor
public class Emp01Bean implements Serializable {
	
	
	List<Employee> emplist ;
	
	@EJB
	private EmployeeService empployeeService;
	
	private DataModel dataModel;

	
	@Login
	@Inject
	private LoginUser loginUser;
	
	public void init() {
		System.out.println("emp01Bean init");
		System.out.println("emp01Bean init2");
		search();
	}
	
	public void search() {
		System.out.println("EmpName:" + loginUser.getEmployee().getEmpName());
		emplist = empployeeService.getEmployeeList();
		List<EmployeeDto> resultList = new ArrayList<>();
		for (Employee employee : emplist) {
			resultList.add(new EmployeeDto(employee));
		}
		dataModel = new ListDataModel();
		dataModel.setWrappedData(resultList);
		
	}
	
	public String edit(Long empId) {
		FlashUtils.putFlash("empId", empId);
		
		return "register";
	}
	
	public String delete(Long empId){
		empployeeService.removeEmployee(empId);
		search();
		return "";
	}
	
	public String toRegister() {
		
		return "register";
	}

	/**
	 * @return the emplist
	 */
	public List<Employee> getEmplist() {
		return emplist;
	}

	/**
	 * @param emplist the emplist to set
	 */
	public void setEmplist(List<Employee> emplist) {
		this.emplist = emplist;
	}

	/**
	 * @return the dataModel
	 */
	public DataModel getDataModel() {
		return dataModel;
	}
	
}
