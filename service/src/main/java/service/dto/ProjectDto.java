/**
 * 
 */
package service.dto;

import java.io.Serializable;
import java.util.Date;

import entity.Project;

/**
 * @author cuongbd
 *
 */
public class ProjectDto implements Serializable{
	
	private Long id;
	
	private String projectName;
	
	private Date startDate;
	
	private Date endDate;
	
	private String status;

	public ProjectDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void copyToEntity(Project project) {
		project.setEndDate(this.getEndDate());
		project.setStartDate(this.getStartDate());
		project.setStatus(this.getStatus());
		project.setProjectName(this.getProjectName());
	}
	
	
}
