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
@Table(name = "tb_workingtime")
public class WorkingTime  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -742274214048521799L;

	//- WorkTime: id(ID), taskid(FK)(notnull),taskname (notnull) (cua task, taskempId(FK) (notnull), projectid(FK) (notnull), progress(notnull cua task), wktDate(*notnull), wkttime (notnull)
	@Id
    @Column(name="id", unique=true, nullable=false)
    @GeneratedValue(generator="tb_workingtime_id", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="tb_workingtime_id",sequenceName="tb_workingtime_id_seq" , initialValue=1,allocationSize=1)
	private Long id;
	
	@Column(name="taskid", nullable = false)
	private Long taskid;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="taskid", unique=true, nullable=false, updatable=false,insertable = false)
	private Task task;
	
	@Column(name="wktdate", nullable = false)
	@Temporal(value = javax.persistence.TemporalType.DATE)
	private Date wktDate;
	
	@Column(name="wkttime")
	private BigDecimal wkttime;

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

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
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
	
	
}
