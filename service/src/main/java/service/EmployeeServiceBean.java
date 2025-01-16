/**
 * 
 */
package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Employee;
import utils.StringUtils;

/**
 * @author cuongbd
 *
 */
@Stateless
public class EmployeeServiceBean implements EmployeeService {
	
	@PersistenceContext(unitName = "pe4jPU")
	private EntityManager em;
	
	public Employee getEmployee(Long empId) {
		
		return em.find(Employee.class, empId);
	}
	
	public List<Employee> getEmployeeList(){
		String jpql = "SELECT * FROM tb_employee t";
		Query query = em.createNativeQuery(jpql, Employee.class);
		
		return query.getResultList();
	}
	
	public List<Employee> getEmployeeListByHql(){
		String jpql = "SELECT t FROM Employee t";
		Query query = em.createQuery(jpql, Employee.class);
		
		return query.getResultList();
	}
	
	
	/**
	 * 
	 * @param empParam
	 */
	public void registerEmployee(Employee empParam) {
		if(StringUtils.nullOrblank(empParam.getUserId())) {
			throw new IllegalArgumentException("userId is not null");
		}
		if(StringUtils.nullOrblank(empParam.getEmpName())) {
			throw new IllegalArgumentException("EmpName is not null");
		}
		if(StringUtils.nullOrblank(empParam.getPassword())) {
			throw new IllegalArgumentException("Password is not null");
		}
		if (empParam.getEmpId() != null) {
			Employee empployee = em.find(Employee.class, empParam.getEmpId());
			if (empployee == null) {
				throw new IllegalArgumentException("EmpId is not exits");
			}
			em.merge(empParam);
		} else {
			em.persist(empParam);

		}
		
	}
	
	/**
	 * findEmployee
	 * @param userId
	 * @param password
	 * @author cuongbd
	 */
	public Employee findEmployee(String userId, String password) {
		StringBuilder jpql = new StringBuilder("SELECT t FROM Employee t ");
		jpql.append("where t.userId =:userId ");
		jpql.append("and t.password =:password ");
		
		Query query = em.createQuery(jpql.toString(), Employee.class);
		query.setParameter("userId", userId);
		query.setParameter("password", password);
		List<Employee> list = query.getResultList();
		if(!list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}

	/**
	 *  deleteEmployee
	 *  @param empId
	 */
	public void deleteEmployee(Long empId) {
		System.out.println("deleteEmployee");
		if(StringUtils.nullOrblank(empId)) {
			throw new IllegalArgumentException("userId is not null");
		}
		Employee empployee = em.find(Employee.class, empId);
		if (empployee == null) {
			throw new IllegalArgumentException("EmpId is not exits");
		}
		em.remove(empployee);
		em.flush();
		
	}
	
	/**
	 * removeEmployee
	 * @param empParam
	 */
	public void removeEmployee(Long empId) {
		System.out.println("deleteEmployee");
		if(StringUtils.nullOrblank(empId)) {
			throw new IllegalArgumentException("userId is not null");
		}
		Employee empployee = em.find(Employee.class, empId);
		if (empployee == null) {
			throw new IllegalArgumentException("EmpId is not exits");
		}
		em.remove(empployee);
		em.flush();
	}

	
}
