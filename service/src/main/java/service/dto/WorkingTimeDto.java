package service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import entity.WorkingTime;

public class WorkingTimeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5083561621217937772L;

	// - WorkTime: id(ID), taskid(FK)(notnull),taskname (notnull) (cua task,
	// taskempId(FK) (notnull), projectid(FK) (notnull), progress(notnull cua task),
	// wktDate(*notnull), wkttime (notnull)
	private Long id;

	private Long taskid;

	private TaskDto task;

	private Date wktDate;

	private BigDecimal wkttime;

	public WorkingTimeDto() {
		task = new TaskDto();
	}

	public WorkingTimeDto(WorkingTime wkTime) {
		this.setId(wkTime.getId());
		this.setTaskid(wkTime.getId());
		this.setTask(new TaskDto(wkTime.getTask()));
		this.setWktDate(wkTime.getWktDate());
		this.setWkttime(wkTime.getWkttime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public TaskDto getTask() {
		return task;
	}

	public void setTask(TaskDto task) {
		this.task = task;
	}

	public Date getWktDate() {
		return wktDate;
	}

	public void setWktDate(Date wktDate) {
		this.wktDate = wktDate;
	}

	public BigDecimal getWkttime() {
		return wkttime;
	}

	public void setWkttime(BigDecimal wkttime) {
		this.wkttime = wkttime;
	}

	public void copyToEntity(WorkingTime wt) {
		wt.setId(this.getId());
		wt.setTaskid(this.getTaskid());
		wt.setWktDate(this.getWktDate());
		wt.setWkttime(this.getWkttime());
		wt.setTask(wt.getTask());
	}

}
