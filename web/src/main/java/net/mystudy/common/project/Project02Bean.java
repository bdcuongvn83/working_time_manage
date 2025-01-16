/**
 * 
 */
package net.mystudy.common.project;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

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
	
	
	@Inject @Login
	private LoginUser loginUser;
	
	public void init() {
		
		resetData();
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
	
	
	
}
