/**
 * 
 */
package net.mystudy.common.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Project;
import entity.Task;
import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
import service.ProjectService;
import service.dto.LoginUser;
import service.dto.ProjectDto;

/**
 * @author cuongbd
 * Register Project
 *
 */
@ConversationScoped
@Named("project02Bean")
@MyInterceptor
public class Project02Bean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProjectDto projectDto;
	
	@EJB
	private ProjectService projectService;
//	
	
	@Inject @Login
	private LoginUser loginUser;
	
	private List<SelectItem> itemProjectList; // Danh sách các SelectItem
	private List<SelectItem> itemTaskList; // Danh sách các SelectItem
	
	public void init() {
		List<Project> projectList = projectService.findProjectList();
		List<Task> taskList = projectService.findTaskList();
		itemProjectList = initProjectItemList(projectList);
		itemTaskList = initTaskItemList(taskList);
		
		resetData();
	}
	private List<SelectItem> initTaskItemList(List<Task> taskList) {
		List<SelectItem> result = new ArrayList<>();
		for (Iterator iterator = taskList.iterator(); iterator.hasNext();) {
			Task task = (Task) iterator.next();
			result.add(new SelectItem(task.getId(), task.getTaskName()));
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
	private void resetData() {
		projectDto = new ProjectDto();
	}
	public String back() {
		
		return "back";
	}
	
	public String register() {
		
		projectService.registerProject(projectDto);
		
		return "projectList";
	}
	
	
	public ProjectDto getProjectDto() {
		return projectDto;
	}
	public void setProjectDto(ProjectDto projectDto) {
		this.projectDto = projectDto;
	}
	public List<SelectItem> getItemProjectList() {
		return itemProjectList;
	}
	public void setItemProjectList(List<SelectItem> itemProjectList) {
		this.itemProjectList = itemProjectList;
	}
	public List<SelectItem> getItemTaskList() {
		return itemTaskList;
	}
	public void setItemTaskList(List<SelectItem> itemTaskList) {
		this.itemTaskList = itemTaskList;
	}
	
	
}
