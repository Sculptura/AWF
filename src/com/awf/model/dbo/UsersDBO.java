package com.awf.model.dbo;

import java.util.Date;

import org.primefaces.model.StreamedContent;

import com.awf.model.orm.dbo.Users;



public class UsersDBO extends Users {
	 
	StreamedContent profileimage;
	
	private String branchCode ;
	private String companyCode;
	private String branchName;	
	private String employeeName;
	private String designationName;
	private String departmentName;
	private String categoryName;
	
	private String headEmpName;
	 
	private String CurrencyDesc ;
	private String currencySymbol;
	private String currencyCode;
	private String companyName;	
	private String countryName;
	private String country;
	private Date systemStartDate;
	private String fyear;
	
	private Integer upMonthFrom;
	private Integer upMonthTo;
	
	private Integer upYearFrom;
	private Integer upYearTo;
	
	
	
	private String userCategoryName;
	
	private Boolean activeFlag = false; 

	private String sessionID;
	

	/**
	 * @return the fyear
	 */
	public String getFyear() {
		return fyear;
	}



	/**
	 * @param fyear the fyear to set
	 */
	public void setFyear(String fyear) {
		this.fyear = fyear;
	}



	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}


	public StreamedContent getProfileimage() {
		return profileimage;
	}



	public void setProfileimage(StreamedContent profileimage) {
		this.profileimage = profileimage;
	}



	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}


	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	

	/**
	 * @return the CurrencyDesc
	 */
	public String getCurrencyDesc() {
		return CurrencyDesc;
	}


	/**
	 * @param CurrencyDesc the CurrencyDesc to set
	 */
	public void setCurrencyDesc(String CurrencyDesc) {
		this.CurrencyDesc = CurrencyDesc;
	}


	/**
	 * @return the currencySymbol
	 */
	public String getCurrencySymbol() {
		return currencySymbol;
	}


	/**
	 * @param currencySymbol the currencySymbol to set
	 */
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}


	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}


	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}


	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}


	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	/**
	 * @return the systemStartDate
	 */
	public Date getSystemStartDate() {
		return systemStartDate;
	}


	/**
	 * @param systemStartDate the systemStartDate to set
	 */
	public void setSystemStartDate(Date systemStartDate) {
		this.systemStartDate = systemStartDate;
	}



	public String getUserCategoryName() {
		return userCategoryName;
	}



	public void setUserCategoryName(String userCategoryName) {
		this.userCategoryName = userCategoryName;
	}



	public String getEmployeeName() {
		return employeeName;
	}



	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	public String getDesignationName() {
		return designationName;
	}



	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}



	public String getDepartmentName() {
		return departmentName;
	}



	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getHeadEmpName() {
		return headEmpName;
	}



	public void setHeadEmpName(String headEmpName) {
		this.headEmpName = headEmpName;
	}



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



	public Integer getUpMonthFrom() {
		return upMonthFrom;
	}



	public void setUpMonthFrom(Integer upMonthFrom) {
		this.upMonthFrom = upMonthFrom;
	}



	public Integer getUpMonthTo() {
		return upMonthTo;
	}



	public void setUpMonthTo(Integer upMonthTo) {
		this.upMonthTo = upMonthTo;
	}



	public Integer getUpYearFrom() {
		return upYearFrom;
	}



	public void setUpYearFrom(Integer upYearFrom) {
		this.upYearFrom = upYearFrom;
	}



	public Integer getUpYearTo() {
		return upYearTo;
	}



	public void setUpYearTo(Integer upYearTo) {
		this.upYearTo = upYearTo;
	}
	
	

}
