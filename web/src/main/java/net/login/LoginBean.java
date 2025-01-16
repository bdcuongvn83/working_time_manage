/**
 * 
 */
package net.login;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Employee;
import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
import service.EmployeeService;
import service.dto.LoginUser;
import utils.StringUtils;

/**
 * @author cuongbd
 *
 */

@ConversationScoped
@Named("loginBean")
@MyInterceptor
public class LoginBean implements Serializable {
	
	
	
	@EJB
	private EmployeeService empployeeService;
	
	private String userId;
	
	private String password;
	
	private String errorMessage;
	
	private LoginUser loginUser;
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	
	//neu scope RequestScope thi param khong duoc luu giu trang thai o 2: khi init(1) ->submit login button(2)
	private String param_p;
	private String convId = null;
	private Long timeout=null;
	
	public void init() {
		request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Object valueP = map.get("p");
		if(valueP!=null) {
			this.param_p = (String) valueP;
		}
		//search();
	}
	
	
	public String login() throws IOException {
		
		// 1. kiem tra login ton tai DB
		if (StringUtils.nullOrblank(userId) || StringUtils.nullOrblank(password)) {
			errorMessage = "userid or password is required not null";
			
			FacesContext.getCurrentInstance().addMessage(null, 
		                new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
			 
			return "";
		}
		Employee emp = empployeeService.findEmployee(userId, password);
		if (emp == null) {
			errorMessage = "loginEmp is not exits, please input again";
			FacesContext.getCurrentInstance().addMessage(null, 
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
		 
			return "";
		}
		// 2. Neu ton tai=> login sucesss
		loginUser = new LoginUser();
		loginUser.setEmployee(emp);
		request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		HttpSession session = request.getSession(true);
		String jsessionID = session.getId();
		
		Object value = session.getAttribute("jsession");
		Set<String> jsessionSet = null;
		if(value ==null) {
			jsessionSet = new HashSet<String>();
			jsessionSet.add(jsessionID);
		}else {
			jsessionSet = (Set<String>) value;
			jsessionSet.add(jsessionID);
		}
		session.setAttribute("jsession", jsessionSet);
		
		// 3. luu lai thong tin login , LoginUser vao session
		session.setAttribute("loginUser", loginUser);

		// 4. generrate jseessionid cho client qua cookie
		Cookie cookie = new Cookie("jsessionID", jsessionID);
		cookie.setMaxAge(60 * 60);
		response.addCookie(cookie);
		// 5. client luu cooki jssessionid

		// 6.client request URL can chung thuc
		// 7. check neu jsesssionid ton tai cho login, khong yeu cau dang nhap
		FacesContext facesContext = FacesContext.getCurrentInstance();
		//redirect to page jsf portal/or page user request
		//Object paramP = map.get("p");
		if(!StringUtils.nullOrblank(this.param_p)) {
			
			response.sendRedirect(param_p);
			if (facesContext != null) {
                facesContext.responseComplete();
            }
			
			return null;
		}else {
			//page default
			return "main";
		}
	}
	
	

	public String logout() {
		this.loginUser = null;
		
		request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		HttpSession session = request.getSession(false);
		
//		session.removeAttribute("jsession");
//		session.removeAttribute("loginUser");
		session.invalidate();
		
		return "login";
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * Neu scopse cua LoginBean: sesssion scopse, thi return this.loginUser (vi scopse phai lon hon cac UserBean thi inject du lieu moi co duoc, neu khong se null)
	 * Neu scopse cua LoginBean: conversation scopse, thi return tu session.getAttribute("loginUser")
	 * @return
	 */
	@Produces @Login LoginUser getCurrentLogin() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession(true);
		LoginUser loginUser = null;
		if (session.getAttribute("loginUser") != null) {
			loginUser = (LoginUser) session.getAttribute("loginUser");
		}

		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}
	
	public LoginUser getLoginUser() {
		return loginUser;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
}
