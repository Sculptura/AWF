package com.awf.model.dbo;

import com.awf.model.orm.dbo.InternalMessengerWithBLOBs;

public class InternalMessengerDBO extends InternalMessengerWithBLOBs {
	
	private String userName;
	private String recipientName;
	private String departmentName;
	private String designationName;
	private String categoryName;
	
	private Boolean messageFloatLeft; 
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Boolean getMessageFloatLeft() {
		return messageFloatLeft;
	}
	public void setMessageFloatLeft(Boolean messageFloatLeft) {
		this.messageFloatLeft = messageFloatLeft;
	}

}
