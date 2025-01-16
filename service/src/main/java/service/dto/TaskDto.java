package service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import entity.Employee;
import entity.Project;
import entity.Task;

public class TaskDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4386798679422917734L;

	private Long id;
	
	private String taskName;
	
	private Long taskEmpId;
	private Employee taskEmp;
	
	private Long projectid;
	private Project project;
	
	private Date startdate;
	
	private Date enddate;
	
	private Date actualStartDate;
	
	private Date actualEndDate;
	
	private int progress;
	
	private BigDecimal estTime;
	
	private String note;

	
	public TaskDto() {
		project = new Project();
		taskEmp = new Employee();
	}

	public TaskDto(Task task) {
		this.setId(task.getId());
		this.setTaskName(task.getTaskName());
		this.setTaskEmpId(task.getTaskEmpId());
		this.setTaskEmp(task.getTaskemp());
		this.setProjectid(task.getProjectid());
		this.setProject(task.getProject());
		this.setStartdate(task.getActualStartDate());
		
		this.setEnddate(task.getActualEndDate());
		this.setActualStartDate(task.getActualStartDate());
		this.setActualEndDate(task.getActualEndDate());
		this.setProgress(task.getProgress());
		this.setEstTime(task.getEstTime());
		this.setNote(task.getNote());
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public Long getTaskEmpId() {
		return taskEmpId;
	}

	public void setTaskEmpId(Long taskEmpId) {
		this.taskEmpId = taskEmpId;
	}

	public Long getProjectid() {
		return projectid;
	}

	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}

	public void setEstTime(BigDecimal estTime) {
		this.estTime = estTime;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}


	public BigDecimal getEstTime() {
		return estTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Employee getTaskEmp() {
		return taskEmp;
	}

	public void setTaskEmp(Employee taskEmp) {
		this.taskEmp = taskEmp;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void copyToEntity(Task task) {
		task.setActualEndDate(this.getActualEndDate());
		task.setActualStartDate(this.getActualEndDate());
		task.setEnddate(this.getActualEndDate());
		task.setEstTime(this.getEstTime());
		task.setId(this.getId());
		task.setNote(this.getNote());
		task.setProgress(this.getProgress());
		task.setProjectid(this.getProjectid());
		task.setStartdate(this.getStartdate());
		task.setTaskEmpId(this.getTaskEmpId());
		task.setTaskName(this.getTaskName());
		
	}
	
	
	
	
	
}
