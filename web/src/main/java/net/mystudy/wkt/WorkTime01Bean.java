/**
 * 
 */
package net.mystudy.wkt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import entity.Task;
import entity.WorkingTime;
import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
import service.ProjectService;
import service.dto.LoginUser;
import service.dto.TaskDto;
import service.dto.WorkingTimeDto;
import utils.DateUtils;
import utils.FlashUtils;

/**
 * MH List project
 * @author cuongbd
 *
 */

@ConversationScoped
@Named("workTime01Bean")
@MyInterceptor
public class WorkTime01Bean implements Serializable {
	
	
	List<WorkingTime> wktTodayList ;
	List<Task> taskList ;
	List<WorkingTimeDto> wktDtoList ;
	
	private Date selectedDate; // 
	
	

	@EJB
	private ProjectService projectService;
	
	private DataModel dataModel;
	
	@Login
	@Inject
	private LoginUser loginUser;
	
	public void init() {
		//condition search giu lai
		if (selectedDate == null) {
			selectedDate = new Date();
		}
		search();
	}
	
	public void search() {
		//1. tim danh sach task cho emp,chua close
		//2. lap tren danh sach task, hien thi ban dau workingtime
		//3. find danh sach working time ngay hom nay.
		// 3.1 Tu2 => tao danh sach hien thi wktDtoDispList hom nay.
		// 3.2 Ket hop ds working time o 3, edit vao wktDtoDispList neu trung taskId.
		
		//3. o man hinh hien thi nut edit, de nhap logtime
		
		System.out.println("EmpName:" + loginUser.getEmployee().getEmpName());
		
		wktTodayList = projectService.findWorkingTimes(selectedDate);
		wktDtoList = new ArrayList<>();
		//1.
		taskList =  projectService.findTaskList();
		//2.
		for (Task task : taskList) {
			WorkingTimeDto dto = new WorkingTimeDto();
			dto.setTask(new TaskDto(task));
			dto.setTaskid(task.getId());
			WorkingTime loged_wk = findWorkingTimeById(task.getId(), wktTodayList);
			//dto.setWktDate(new Date());
			if (loged_wk != null) { 
				dto.setWkttime(loged_wk.getWkttime());
				dto.setWktDate(loged_wk.getWktDate());
				dto.setId(loged_wk.getId());
			}else {
				dto.setWktDate(selectedDate);
				dto.setWkttime(BigDecimal.ZERO);
			}
			wktDtoList.add(dto);
			
		}
		
		dataModel = new ListDataModel();
		dataModel.setWrappedData(wktDtoList);
		
	}
	
	public void nextDate() {
		selectedDate = DateUtils.nextDate(selectedDate);
		search();

	}
	
	public void prevDate() {
		selectedDate = DateUtils.prevDate(selectedDate);
		search();
	}
	
	public String editWorkingTime(WorkingTimeDto item){
		
		//FlashUtils.putFlash("taskid", taskid);
		FlashUtils.putFlash("taskParam", item);
		return "register";
	}
	
	private WorkingTime findWorkingTimeById(Long id, List<WorkingTime> wktList) {
		if(wktList==null) {
			return null;
		}
		for (WorkingTime wk : wktList) {
			if(wk.getTaskid().equals(id)) {
				return wk;
			}
		}
		
		return null;
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

	public List<WorkingTime> getWktList() {
		return wktTodayList;
	}

	public void setWktList(List<WorkingTime> wktList) {
		this.wktTodayList = wktList;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}
}
