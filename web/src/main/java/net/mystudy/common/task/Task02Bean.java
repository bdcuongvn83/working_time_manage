/**
 * 
 */
package net.mystudy.common.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Employee;
import entity.Project;
import net.jsf.ExceptionUtil;
import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
import service.EmployeeService;
import service.ProjectService;
import service.dto.LoginUser;
import service.dto.TaskDto;

/**
 * @author cuongbd
 * Register Project
 *
 */
@ConversationScoped
@Named("task02Bean")
@MyInterceptor
public class Task02Bean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaskDto taskDto;
	
	@EJB
	private ProjectService projectService;
	
	
	@EJB
	private EmployeeService employeeService;
//	
	
	@Inject @Login
	private LoginUser loginUser;
	
	private List<SelectItem> itemProjectList; // Danh sách các SelectItem
	private List<SelectItem> itemEmpList; // Danh sách các SelectItem
	
	public void init() {
		List<Project> projectList = projectService.findProjectList();
		List<Employee> empList = employeeService.getEmployeeList();
		itemProjectList = initProjectItemList(projectList);
		itemEmpList = initEmpItemList(empList);
		
		resetData();
	}
	
	
	
	private void resetData() {
		taskDto = new TaskDto();
	}
	public String back() {
		
		return "back";
	}
	
	public String register() {
		try {
			projectService.registerTask(taskDto);
		}catch (Exception e) {
			Throwable exception = ExceptionUtil.getRootCause(e);
			if(exception instanceof IllegalArgumentException) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
				return "";
			}
			throw new RuntimeException(e);
		}
		
		return "taskList";
	}
	
	private List<SelectItem> initEmpItemList(List<Employee> list) {
		List<SelectItem> result = new ArrayList<>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Employee task = (Employee) iterator.next();
			result.add(new SelectItem(task.getEmpId(), task.getEmpName()));
		}
		
		return result;
	}
	private List<SelectItem> initProjectItemList(List<Project> projectList) {
		List<SelectItem> result = new ArrayList<>();
		for (Iterator iterator = projectList.iterator(); iterator.hasNext();) {
			Project item = (Project) iterator.next();
			result.add(new SelectItem(item.getId(), item.getProjectName()));
		}
		
		return result;
	}
	
	public TaskDto getTaskDto() {
		return taskDto;
	}
	public void setTaskDto(TaskDto taskDto) {
		this.taskDto = taskDto;
	}
	public List<SelectItem> getItemProjectList() {
		return itemProjectList;
	}
	public void setItemProjectList(List<SelectItem> itemProjectList) {
		this.itemProjectList = itemProjectList;
	}
	public List<SelectItem> getItemEmpList() {
		return itemEmpList;
	}
	public void setItemEmpList(List<SelectItem> itemEmpList) {
		this.itemEmpList = itemEmpList;
	}
	
}
