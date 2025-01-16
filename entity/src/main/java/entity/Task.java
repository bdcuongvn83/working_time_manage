package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tb_task")
public class Task  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -772215857380733816L;

	//- task: id(PK),taskname(notnull), taskempId, projectid(FK), startdate,enddate, actualStartDate, actualEndDate, progress, estTime (notnull), spendTime(*) ,note
	@Id
    @Column(name="id", unique=true, nullable=false)
    @GeneratedValue(generator="tb_task_id", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="tb_task_id",sequenceName="tb_task_id_seq" , initialValue=1,allocationSize=1)
	private Long id;
	
	@Column(name="taskname", nullable = false)
	private String taskName;
	
	@Column(name="taskempid", nullable = false)
	private Long taskEmpId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="taskempid", unique=true, nullable=false, updatable=false,insertable = false)
	private Employee taskemp;
	
	@Column(name="projectid", nullable = false)
	private Long projectid;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="projectid", unique=true, nullable=false, updatable=false,insertable = false)
	private Project project;
	
	@Column(name="startdate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	private Date startdate;
	
	@Column(name="enddate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	private Date enddate;
	
	
	@Column(name="actualstartdate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	private Date actualStartDate;
	
	@Column(name="actualenddate")
	@Temporal(value = javax.persistence.TemporalType.DATE)
	private Date actualEndDate;
	
	@Column(name="progress")
	private int progress;
	
	@Column(name="esttime", nullable = false)
	private BigDecimal estTime;
	
	@Column(name="note")
	private String note;

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

	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getTaskemp() {
		return taskemp;
	}

	public void setTaskemp(Employee taskemp) {
		this.taskemp = taskemp;
	}
	
	
	
	
	
}
