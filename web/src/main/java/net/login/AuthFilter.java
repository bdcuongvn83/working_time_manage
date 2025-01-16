/**
 * 
 */
package net.login;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.dto.LoginUser;

/**
 * @author cuongbd
 *
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	
//	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
//			throws IOException, ServletException {
//		
//
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//		HttpSession session = request.getSession();
////	
//		HttpSession httpSession = request.getSession();
//		LoginUser loginUser = (LoginUser) httpSession.getAttribute("loginUser");
//	        
//		if (isLogined(loginUser)) {
//			chain.doFilter(req, resp);
//			if (request.getRequestURI().contains("login.jsf") || request.getRequestURI().contains("login.xhtml")) {
//				// Sending request to next
//				// neu da co quyen authority DB , navigate main.jsf
//				// neu chua co thi vao login.jsf
//				chain.doFilter(req, resp);
//				response.sendRedirect("main.jsf");
//			}
//		} else if (request.getRequestURI().contains("login.jsf") || request.getRequestURI().contains("login.xhtml")) {
//			// Sending request to next
//			//
//			chain.doFilter(req, resp);
//
//		}
//		// Password incorrect
//		else {
//			// out.print("username or password is wrong");
//			// out.log("Unauthorized access request");
//			StringBuffer location = new StringBuffer("login.jsf");
//			location.append("?p=" + request.getRequestURI());
//
//			response.sendRedirect(request.getContextPath() + "/" + location.toString());
//		}
//	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
		    throws IOException, ServletException {
		    HttpServletRequest request = (HttpServletRequest) req;
		    HttpServletResponse response = (HttpServletResponse) resp;
		    HttpSession session = request.getSession();
		    LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");

		    if (request.getRequestURI().contains("/image/")) {
		        // Nếu yêu cầu là hình ảnh, không xử lý gì, cho phép tiếp tục với filter chain
		        chain.doFilter(req, resp);
		        return;
		    }
//		    if (request.getRequestURI().equals(request.getContextPath() + "/")) {
//		        // Chuyển hướng đến trang main.jsf
//		        response.sendRedirect(request.getContextPath() + "/main.jsf");
//		        return;
//		    }
		    
		    if (isLogined(loginUser)) {
		        if (request.getRequestURI().contains("login.jsf") 
		        		|| request.getRequestURI().contains("login.xhtml")||
		        		request.getRequestURI().equals(request.getContextPath() + "/")) {
		            response.sendRedirect("main.jsf"); // Chuyển hướng sau khi đã xử lý filter.
		        }else {
		        	chain.doFilter(req, resp); // Tiến hành tiếp tục với filter tiếp theo hoặc servlet.
		        }
		    } else if (request.getRequestURI().contains("login.jsf") || request.getRequestURI().contains("login.xhtml")) {
		        chain.doFilter(req, resp); // Nếu người dùng chưa đăng nhập, tiếp tục với filter.
		    } else {
		    	
		    	StringBuffer location = new StringBuffer("login.jsf");
				if (request.getRequestURI().equals(request.getContextPath() + "/")) {
					//khong co tham so
				} else {

					// Không cần gọi response.getWriter() tại đây. Chỉ cần chuyển hướng.
					location.append("?p=" + request.getRequestURI());
				}
		        response.sendRedirect(request.getContextPath() + "/" + location.toString());
		    }
		}
	
	private boolean isLogined(LoginUser loginUser) {
		if (loginUser != null) {
			return true;
		}

		return false;
	}

	public Optional<String> readCookie(ServletRequest reqParam, String key) {
		HttpServletRequest request = (HttpServletRequest) reqParam;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			return Arrays.stream(cookies).filter(c -> key.equals(c.getName())).map(Cookie::getValue).findAny();
		}
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
