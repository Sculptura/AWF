package com.awf.model.dbo;

import com.awf.model.orm.dbo.WFUsers;

public class WFUsersDBO extends WFUsers {
	private Boolean activeFlag = false; 

	private String sessionID; 

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
}
