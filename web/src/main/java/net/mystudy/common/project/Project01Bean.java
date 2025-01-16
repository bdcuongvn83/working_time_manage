/**
 * 
 */
package net.mystudy.common.project;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Project;
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
@Named("project01Bean")
@MyInterceptor
public class Project01Bean implements Serializable {
	
	
	List<Project> projectList ;
	
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
		projectList = projectService.findProjectList();
		dataModel = new ListDataModel();
		dataModel.setWrappedData(projectList);
		
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

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
	
	
}
