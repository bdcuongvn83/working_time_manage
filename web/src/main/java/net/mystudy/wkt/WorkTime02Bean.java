/**
 * 
 */
package net.mystudy.wkt;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entity.WorkingTime;
import net.jsf.ExceptionUtil;
import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
import service.ProjectService;
import service.dto.LoginUser;
import service.dto.WorkingTimeDto;
import utils.FlashUtils;
import utils.NumberUtils;

/**
 * @author cuongbd Register Project
 *
 */
@ConversationScoped
@Named("workTime02Bean")
@MyInterceptor
public class WorkTime02Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WorkingTimeDto wktDto;

	@EJB
	private ProjectService projectService;
//	

	@Inject
	@Login
	private LoginUser loginUser;

	private Long taskIdParam;
	private WorkingTimeDto taskParam;

	/**
	 * initialize
	 */
	public void init() {
		// from link Edit from MH List
		//taskIdParam = NumberUtils.toLong(FlashUtils.getFlash("taskid"));
		if(FlashUtils.getFlash("taskParam")!=null) {
			taskParam = (WorkingTimeDto) FlashUtils.getFlash("taskParam");
		}
		if (taskParam != null) {
			// mode edit
			WorkingTime wkTime = projectService.findWorkingTimeByTaskId(taskIdParam, new Date());
			if (wkTime != null) {
				wktDto = new WorkingTimeDto(wkTime);
			} else {
//				wktDto = new WorkingTimeDto();
//				wktDto.setTaskid(taskIdParam);
				wktDto = taskParam;
			}
		} else {
			// mode new
			resetData();
		}

	}

	/**
	 * back to list
	 * @return
	 */
	public String back() {

		return "back";
	}

	/**
	 * register working time
	 * @return
	 */
	public String register() {
		try {
			projectService.registerWorkingTime(wktDto);
			
		}catch (Exception e) {
			Throwable exception = ExceptionUtil.getRootCause(e);
			if(exception instanceof IllegalArgumentException) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
				return "";
			}
			throw new RuntimeException(e);
		}

		return "wktList";
	}

	private void resetData() {
		wktDto = new WorkingTimeDto();
	}

	
	public WorkingTimeDto getWktDto() {
		return wktDto;
	}

	public void setWktDto(WorkingTimeDto wktDto) {
		this.wktDto = wktDto;
	}

}
