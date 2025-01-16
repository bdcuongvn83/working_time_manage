/**
 * 
 */
package net.mystudy.common.task;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Task;
import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
import service.ProjectService;
import service.dto.LoginUser;

/**
 * MH List project
 * @author cuongbd
 *
 */

@ConversationScoped
@Named("task01Bean")
@MyInterceptor
public class Task01Bean implements Serializable {
	
	
	List<Task> taskList ;
	
	@EJB
	private ProjectService projectService;
	
	private DataModel dataModel;
	
	@Login
	@Inject
	private LoginUser loginUser;
	
	public void init() {
		search();
	}
	
	public void search() {
		System.out.println("EmpName:" + loginUser.getEmployee().getEmpName());
		taskList = projectService.findTaskList();
		dataModel = new ListDataModel();
		dataModel.setWrappedData(taskList);
		
	}
	
	public String toRegister() {
		
		return "register";
	}

	
	/**
	 * @return the dataModel
	 */
	public DataModel getDataModel() {
		return dataModel;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	
	
}
