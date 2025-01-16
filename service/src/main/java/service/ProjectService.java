/**
 * 
 */
package service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entity.Project;
import entity.Task;
import entity.WorkingTime;
import service.dto.ProjectDto;
import service.dto.TaskDto;
import service.dto.WorkingTimeDto;

/**
 * @author cuongbd
 *
 */
@Local
public interface ProjectService {
	public void registerProject(ProjectDto projectDto);
	public void registerTask(TaskDto taskDto);
	public void registerWorkingTime(WorkingTimeDto workingTimeDto);
	
	public void updateProject(ProjectDto projectDto);
	public void updateTask(TaskDto taskDto);
	public void updaterWorkingTime(WorkingTimeDto workingTimeDto);
	
	public List<Project> findProjectList();
	public List<Task> findTaskList();
	public List<WorkingTime> findWorkingTimes();
	
	public WorkingTime findWorkingTime(Long id);
	
	public void deleteProject(Long id);
	public void deleteTask(Long id);
	public void deleteWorkingTimes(Long id);
	public List<WorkingTime> findWorkingTimes(Date date);
	public WorkingTime findWorkingTimeByTaskId(Long taskIdParam, Date date);
	public Project findProject(Long id);
	public Task findTask(Long taskid);
	
	
}
