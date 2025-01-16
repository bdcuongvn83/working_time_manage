/**
 * 
 */
package service;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * @author cuongbd
 *
 */
public class JSFPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent phaseEvent) {
		// TODO Auto-generated method stub
		System.out.println("AFTER PHASE ID:- " + phaseEvent.getPhaseId().toString());
		boolean commitTran = false;
		if (PhaseId.INVOKE_APPLICATION.equals(phaseEvent.getPhaseId())
				|| phaseEvent.getFacesContext().getRenderResponse()
				|| // TODO: no need to commit the tx if we failed to restore
					// the view
				phaseEvent.getFacesContext().getResponseComplete()
				|| PhaseId.RENDER_RESPONSE.equals(phaseEvent.getPhaseId())) {
			commitTran = true;
		}
		if (commitTran) {
			System.out.println("AFTER PHASE ID:- " + phaseEvent.getPhaseId().toString());
			commitOrRollback();
		}
	}

	private void commitOrRollback() {
		UserTransaction ut = getUserTransaction();
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

	@Override
	public void beforePhase(PhaseEvent phaseEvent) {
		// TODO Auto-generated method stub
//		if (em == null) {
//			em = createEntityManager();
//		}
		System.out.println("BEFORE PHASE ID:- " + phaseEvent.getPhaseId().toString());
		boolean beginTran = (phaseEvent.getPhaseId() == PhaseId.RENDER_RESPONSE)
				|| (phaseEvent.getPhaseId() == PhaseId.RESTORE_VIEW);
		if(beginTran) {
			begin();
		}
	}

//	private EntityManager createEntityManager() {
//		InitialContext ic;
//		EntityManager em;
//		try {
//			ic = new InitialContext();
//			EntityManagerFactory emf = Persistence.createEntityManagerFactory("pe4jPU");
//			em = emf.createEntityManager();
//		} catch (NamingException e) {
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		
//		return em;
//	}

	private void begin() {
		try {
			UserTransaction ut = getUserTransaction();
			if (!isActive(ut)) {
				ut.begin();
			}
		} catch (NotSupportedException | SystemException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public boolean isRollbackOnly(UserTransaction ut) {
		try {
			int jtaStatus = ut.getStatus();

			return (jtaStatus == Status.STATUS_MARKED_ROLLBACK || jtaStatus == Status.STATUS_ROLLEDBACK);
		} catch (SystemException ex) {
			throw new RuntimeException(ex);
		}
	}

	public boolean isActive(UserTransaction ut) {
		try {
			int jtaStatus = ut.getStatus();

			return (jtaStatus == Status.STATUS_ACTIVE);
		} catch (SystemException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void finishTransaction() {
		UserTransaction ut = getUserTransaction();
		if (isActive(ut)) {
			if (isRollbackOnly(ut)) {
				try {
					ut.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			} else {
				try {
					ut.commit();
				} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
						| HeuristicRollbackException | SystemException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
		}
	}

	private UserTransaction getUserTransaction() {
		try {
			UserTransaction ut = InitialContext.doLookup("java:comp/UserTransaction");
			return ut;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
