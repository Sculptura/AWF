package com.awf.model.orm.dbo;

public class WFContentKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column dbo.WFContent.Code
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	private Long code;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column dbo.WFContent.StepCode
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	private String stepcode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column dbo.WFContent.SerialNo
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	private Integer serialno;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column dbo.WFContent.Type
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	private String type;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column dbo.WFContent.Code
	 * @return  the value of dbo.WFContent.Code
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column dbo.WFContent.Code
	 * @param code  the value for dbo.WFContent.Code
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column dbo.WFContent.StepCode
	 * @return  the value of dbo.WFContent.StepCode
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public String getStepcode() {
		return stepcode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column dbo.WFContent.StepCode
	 * @param stepcode  the value for dbo.WFContent.StepCode
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void setStepcode(String stepcode) {
		this.stepcode = stepcode == null ? null : stepcode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column dbo.WFContent.SerialNo
	 * @return  the value of dbo.WFContent.SerialNo
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public Integer getSerialno() {
		return serialno;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column dbo.WFContent.SerialNo
	 * @param serialno  the value for dbo.WFContent.SerialNo
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void setSerialno(Integer serialno) {
		this.serialno = serialno;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column dbo.WFContent.Type
	 * @return  the value of dbo.WFContent.Type
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column dbo.WFContent.Type
	 * @param type  the value for dbo.WFContent.Type
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
}