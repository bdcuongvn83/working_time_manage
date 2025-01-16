package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tb_project")
public class Project  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5603689687686149607L;

	//--projectname(not null),id,,startDate,endDate,status(new,closed)
	@Id
    @Column(name="id", unique=true, nullable=false)
    @GeneratedValue(generator="tb_project_id", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="tb_project_id",sequenceName="tb_project_id_seq" , initialValue=1,allocationSize=1)
	private Long id;
	
	@Column(name="projectname",nullable = false)
	private String projectName;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name="startDate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	private Date startDate;
	
	@Column(name="endDate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	private Date endDate;
	
	@Column(name="status")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
	
	
	
	
}
