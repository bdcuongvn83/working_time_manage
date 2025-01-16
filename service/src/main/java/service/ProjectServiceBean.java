/**
 * 
 */
package service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Project;
import entity.Task;
import entity.WorkingTime;
import service.dto.ProjectDto;
import service.dto.TaskDto;
import service.dto.WorkingTimeDto;
import utils.StringUtils;

/**
 * @author cuongbd
 *
 */
@Stateless
public class ProjectServiceBean implements ProjectService {
	
	@PersistenceContext(unitName = "pe4jPU")
	private EntityManager em;
	
	@EJB
	private EmployeeService empSerivce;

	@Override
	public void registerProject(ProjectDto projectDto) {
		checkProjectParam(projectDto);
		
		Project project = new Project();
		projectDto.copyToEntity(project);
		em.persist(project);

	}

	@Override
	public void registerTask(TaskDto taskDto) {
		checkTaskParam(taskDto);
		if(empSerivce.getEmployee(taskDto.getTaskEmpId())==null) {
			throw new IllegalArgumentException("param emppId not exits");
		}
		if(findProject(taskDto.getProjectid())==null) {
			throw new IllegalArgumentException("param projectId not exits");
		}
		
		Task task = new Task();
		taskDto.copyToEntity(task);
		em.persist(task);		
	}

	

	@Override
	public void registerWorkingTime(WorkingTimeDto workingTimeDto) {
		checkWorkingTimeParam(workingTimeDto);
		if(findTask(workingTimeDto.getTaskid())==null) {
			throw new IllegalArgumentException("Taskid is not exits");
		}
		if (workingTimeDto.getId() == null) {
			WorkingTime wt = new WorkingTime();
			workingTimeDto.copyToEntity(wt);
			
			em.persist(wt);		
		}else {
			//update
			updaterWorkingTime(workingTimeDto);
		}
	}

	

	@Override
	public void updateProject(ProjectDto projectDto) {
		checkProjectParam(projectDto);
		if (projectDto.getId() == null) {
			throw new IllegalArgumentException("id is not exits");
		}
		Project project = em.find(Project.class, projectDto.getId());
		if (project == null) {
			throw new IllegalArgumentException("Project is not exits");
		}
		projectDto.copyToEntity(project);
		em.merge(project);
	}

	@Override
	public void updateTask(TaskDto taskDto) {
		checkTaskParam(taskDto);
		
		if (taskDto.getId() == null) {
			throw new IllegalArgumentException("id is not exits");
		}
		Task task = em.find(Task.class, taskDto.getId());
		if (task == null) {
			throw new IllegalArgumentException("Task is not exits");
		}
		taskDto.copyToEntity(task);
		em.merge(task);		
		
	}

	@Override
	public void updaterWorkingTime(WorkingTimeDto workingTimeDto) {
		checkWorkingTimeParam(workingTimeDto);
		
		if (workingTimeDto.getId() == null) {
			throw new IllegalArgumentException("id is not exits");
		}
		WorkingTime wktime = em.find(WorkingTime.class, workingTimeDto.getId());
		if (wktime == null) {
			throw new IllegalArgumentException("workingTime is not exits");
		}
		workingTimeDto.copyToEntity(wktime);
		em.merge(wktime);		
	}

	@Override
	public List<Project> findProjectList() {
		String jpql = "SELECT t FROM Project t";
		Query query = em.createQuery(jpql, Project.class);
		
		return query.getResultList();
	}

	@Override
	public List<Task> findTaskList() {
		String jpql = "SELECT t FROM Task t";
		Query query = em.createQuery(jpql, Task.class);
		
		return query.getResultList();
	}

	@Override
	public List<WorkingTime> findWorkingTimes() {
		String jpql = "SELECT t FROM WorkingTime t";
		Query query = em.createQuery(jpql, WorkingTime.class);
		
		return query.getResultList();
	}

	@Override
	public void deleteProject(Long id) {
		if(StringUtils.nullOrblank(id)) {
			throw new IllegalArgumentException("id is not null");
		}
		
		Project project = em.find(Project.class, id);
		if (project != null) {
			em.remove(project);
		}
		
	}

	@Override
	public void deleteTask(Long id) {
		if(StringUtils.nullOrblank(id)) {
			throw new IllegalArgumentException("id is not null");
		}
		
		Task task = em.find(Task.class, id);
		if (task != null) {
			em.remove(task);
		}
		
	}

	@Override
	public void deleteWorkingTimes(Long id) {
		if(StringUtils.nullOrblank(id)) {
			throw new IllegalArgumentException("id is not null");
		}
		
		WorkingTime wkt = em.find(WorkingTime.class, id);
		if (wkt != null) {
			em.remove(wkt);
		}		
	}
	
	@Override
	public List<WorkingTime> findWorkingTimes(Date date) {
		StringBuilder jpql = new StringBuilder("SELECT t FROM WorkingTime t ");
		jpql.append("WHERE t.wktDate =:wktDate ");
		
		Query query = em.createQuery(jpql.toString(), WorkingTime.class);
		query.setParameter("wktDate", date);
		
		return query.getResultList();
	}

	@Override
	public Project findProject(Long id) {
		if(StringUtils.nullOrblank(id)) {
			throw new IllegalArgumentException("id is not null");
		}
		
		return em.find(Project.class, id);
	}
	
	@Override
	public Task findTask(Long taskid) {
		if(StringUtils.nullOrblank(taskid)) {
			throw new IllegalArgumentException("taskid is not null");
		}
		
		return em.find(Task.class, taskid);
	}
	
	@Override
	public WorkingTime findWorkingTime(Long id) {
		if(StringUtils.nullOrblank(id)) {
			throw new IllegalArgumentException("id is not null");
		}
		
		return em.find(WorkingTime.class, id);
		
	}
	
	@Override
	public WorkingTime findWorkingTimeByTaskId(Long taskIdParam, Date date) {
		if(StringUtils.nullOrblank(taskIdParam)) {
			throw new IllegalArgumentException("taskIdParam is not null");
		}
		if(StringUtils.nullOrblank(date)) {
			throw new IllegalArgumentException("date is not null");
		}
		
		StringBuilder jpql = new StringBuilder("SELECT t FROM WorkingTime t ");
		jpql.append("WHERE t.wktDate =:wktDate ");
		jpql.append("AND t.taskid =:taskid ");
		
		Query query = em.createQuery(jpql.toString(), WorkingTime.class);
		query.setParameter("wktDate", date);
		query.setParameter("taskid", taskIdParam);
		
		List<WorkingTime> wktList = query.getResultList();
		if(!wktList.isEmpty()) {
			return wktList.get(0);
		}
		
		return null;
	}
	
	
	private void checkWorkingTimeParam(WorkingTimeDto workingTimeDto) {
		if(workingTimeDto.getTask()==null) {
			throw new IllegalArgumentException("Taskid is not null");
		}
		
		if(StringUtils.nullOrblank(workingTimeDto.getWkttime())) {
			throw new IllegalArgumentException("Wkttime is not null");
		}
	}
	
	private void checkProjectParam(ProjectDto projectDto) {
		if(StringUtils.nullOrblank(projectDto.getProjectName())) {
			throw new IllegalArgumentException("projectName is not null");
		}
	}
	
	private void checkTaskParam(TaskDto taskDto) {
		if(StringUtils.nullOrblank(taskDto.getTaskName())) {
			throw new IllegalArgumentException("tasktName is not null");
		}
		
		if(StringUtils.nullOrblank(taskDto.getProjectid())) {
			throw new IllegalArgumentException("Projectid is not null");
		}
		
		if(StringUtils.nullOrblank(taskDto.getTaskEmpId())) {
			throw new IllegalArgumentException("TaskEmpId is not null");
		}
		
		if(StringUtils.nullOrblank(taskDto.getEstTime())){
			throw new IllegalArgumentException("EstTime is not null");
		}
	}

	

	
}
