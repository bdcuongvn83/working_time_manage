/**
 * 
 */
package net.mystudy.common.task;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import net.jsf.ExceptionUtil;
import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
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
//	
	
	@Inject @Login
	private LoginUser loginUser;
	
	public void init() {
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
	public TaskDto getTaskDto() {
		return taskDto;
	}
	public void setTaskDto(TaskDto taskDto) {
		this.taskDto = taskDto;
	}
	
}
