/**
 * 
 */
package com.awf.utilities;

import java.io.IOException;
import java.util.Date;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * 
 */
public class LifeCycleListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LifeCycleListener() {
		
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();

		if (!event.getFacesContext().isPostback()) {
			
			System.out.println("inside postback" + new Date());
			
			if (!request.getRequestURI().endsWith("index.html")) {
				
				System.out.println("getrequestURI " +request.getRequestURI() + new Date());

				externalContext.invalidateSession();

				try {
					externalContext.redirect(externalContext
							.getRequestContextPath() + "/index.html");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			} else {
				
				System.out.println("Session Invalidated " + event.getFacesContext().isPostback());
				System.out.println("getrequestURI in else " +request.getRequestURI());
				externalContext.invalidateSession();
				externalContext.getSession(true);
				
			}
			
			

		}
		
		System.out.println("getrequestURI at last " +request.getRequestURI());
		System.out.println("END PHASE " + event.getPhaseId());

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
		System.out.println("START PHASE " + event.getPhaseId());
		
		
		

	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.RESTORE_VIEW;
	}

	private boolean isAjaxRequest() {
		PartialViewContext partialViewContext = FacesContext
				.getCurrentInstance().getPartialViewContext();
		return null != partialViewContext && partialViewContext.isAjaxRequest();
	}

}
