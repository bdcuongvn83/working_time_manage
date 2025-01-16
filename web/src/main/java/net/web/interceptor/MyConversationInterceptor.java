/**
 * 
 */
package net.web.interceptor;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import net.service.interceptor.anotation.MyInterceptor;

@Dependent
@MyInterceptor
@Interceptor

/**
 * @author cuongbd
 *
 */
public class MyConversationInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4913197597797300546L;
	
public static long CONVERSATION_DEFAULT_TIME_OUT_VALUE = 600000; // 10分
	
	@Inject
	private Conversation conversation;
	/**
	 * 
	 */
	public MyConversationInterceptor() {
		
		System.out.println("ConversationInterceptor2 construct");
	}
	
	@AroundConstruct
	public Object initConversation(InvocationContext ic) throws Exception {

		initConversation();
		Object result = ic.proceed();
		System.out.println("initConversation : "+ ic.getConstructor().getDeclaringClass()+" id=" + conversation.getId());

		return result;
	}
	
	public void initConversation() {
		if ((null == FacesContext.getCurrentInstance() || !FacesContext.getCurrentInstance().isPostback())
				&& conversation.isTransient()) {
			conversation.begin();
			conversation.setTimeout(CONVERSATION_DEFAULT_TIME_OUT_VALUE);
		}
	}

	@AroundInvoke
	public Object  invokeMethod(InvocationContext invocationCtx) throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("Start method: " + invocationCtx.getMethod().getName());
		 Object returnValue = invocationCtx.proceed();
         System.out.println("End method: " + invocationCtx.getMethod().getName());
         
         long endTime = System.currentTimeMillis();
         System.out.println(invocationCtx.getMethod().getName() +": " + (endTime-startTime)+"ms");
         
         return returnValue;
	}
	
	public String endConversation(){
        if(!conversation.isTransient()){
            conversation.end();
        }
        return "login.jsf?faces-redirect=true";
    }
}
