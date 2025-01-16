/**
 * 
 */
package net.jsf;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.NonexistentConversationException;
import javax.enterprise.context.RequestScoped;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * @author cuongbd
 *
 */

@RequestScoped
@Named
public class CustomExceptionHandler extends ExceptionHandlerWrapper implements Serializable{

	private ExceptionHandler exceptionHandler;
	
	public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
	
	private UserTransaction userTransaction;

	@Override
	public ExceptionHandler getWrapped() {
		return exceptionHandler;
	}
	
	@Override
    public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> queue = getUnhandledExceptionQueuedEvents().iterator();
		boolean isContextNotActiveException=false;
		boolean hasError = false;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext exContext = facesContext.getExternalContext();
		String reqContextPath = exContext.getRequestContextPath();
		
        while (queue.hasNext()){
            ExceptionQueuedEvent item = queue.next();
            ExceptionQueuedEventContext exceptionQueuedEventContext = (ExceptionQueuedEventContext)item.getSource();

            try {
                Throwable throwable = exceptionQueuedEventContext.getException();
                System.err.println("Exception: " + throwable.getMessage());

                FacesContext context = FacesContext.getCurrentInstance();
                Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
//                NavigationHandler nav = context.getApplication().getNavigationHandler();
//
//                requestMap.put("error-message", throwable.getMessage());
//                requestMap.put("error-stack", throwable.getStackTrace());
//                nav.handleNavigation(context, null, "/error");
//                context.renderResponse();
				InitialContext initContext = new InitialContext();
				userTransaction = (UserTransaction)initContext.lookup("java:comp/UserTransaction");
				commitOrRollback();
				
				isContextNotActiveException = isContextNotActiveException(throwable);
				hasError = true;
				

            } catch (NamingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} finally {
                queue.remove();
            }
            
        }
        try {
        	if(isContextNotActiveException){
        		exContext.redirect(reqContextPath + "/operationTimeout.jsf?faces-redirect=true");
        		
        	} 
//        	else if(hasError){
//        		
//        		//exContext.getRequestMap().put("javax.servlet.error.exception", throwable );
//        		exContext.redirect(reqContextPath + "/errorDisplay.jsf");
//        		//FacesContext.getCurrentInstance().getExternalContext().redirect("newPage");
//        	}
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
        
        // Let the parent handle the rest
        getWrapped().handle();
    }
	
	private void commitOrRollback() {
		UserTransaction ut = userTransaction;
		try {
			int jtaStatus = ut.getStatus();
			if (jtaStatus == Status.STATUS_MARKED_ROLLBACK || jtaStatus == Status.STATUS_ROLLEDBACK) {
				ut.rollback();
			} else if (jtaStatus == Status.STATUS_ACTIVE) {
				ut.commit();
			}
		} catch (SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private boolean isContextNotActiveException(Throwable e){
		Throwable throwable = ExceptionUtil.getRootCause(e);
		
		if(throwable instanceof ContextNotActiveException){
			return true;
		}
		
		if(throwable instanceof NonexistentConversationException){
			return true;
		}
		return false;
	}
	
	

}
