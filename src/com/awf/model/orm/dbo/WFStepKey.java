package com.awf.model.orm.dbo;

public class WFStepKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column dbo.WFStep.StepCode
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	private String stepcode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column dbo.WFStep.SerialNo
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	private Integer serialno;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column dbo.WFStep.StepCode
	 * @return  the value of dbo.WFStep.StepCode
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public String getStepcode() {
		return stepcode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column dbo.WFStep.StepCode
	 * @param stepcode  the value for dbo.WFStep.StepCode
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void setStepcode(String stepcode) {
		this.stepcode = stepcode == null ? null : stepcode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column dbo.WFStep.SerialNo
	 * @return  the value of dbo.WFStep.SerialNo
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public Integer getSerialno() {
		return serialno;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column dbo.WFStep.SerialNo
	 * @param serialno  the value for dbo.WFStep.SerialNo
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void setSerialno(Integer serialno) {
		this.serialno = serialno;
	}
}