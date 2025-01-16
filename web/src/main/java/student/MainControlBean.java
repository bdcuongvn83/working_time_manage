/**
 * 
 */
package student;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.service.interceptor.anotation.Login;
import net.service.interceptor.anotation.MyInterceptor;
import service.dto.LoginUser;

/**
 * @author cuongbd
 *
 */

//@ConversationScoped
@SessionScoped
@Named("mainControlBean")
@MyInterceptor
public class MainControlBean implements Serializable {
	
	@Login
	@Inject
	private LoginUser loginUser;
	
	public void init() {
		System.out.println("");
	}
	
	public String logout() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		return "login";
	}
	
	public String toRegister() {
		
		return "register";
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	
}
