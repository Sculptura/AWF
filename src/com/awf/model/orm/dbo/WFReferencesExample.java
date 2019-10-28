package com.awf.model.orm.dbo;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class WFReferencesExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public WFReferencesExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andCodeIsNull() {
			addCriterion("Code is null");
			return (Criteria) this;
		}

		public Criteria andCodeIsNotNull() {
			addCriterion("Code is not null");
			return (Criteria) this;
		}

		public Criteria andCodeEqualTo(Integer value) {
			addCriterion("Code =", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotEqualTo(Integer value) {
			addCriterion("Code <>", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThan(Integer value) {
			addCriterion("Code >", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThanOrEqualTo(Integer value) {
			addCriterion("Code >=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThan(Integer value) {
			addCriterion("Code <", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThanOrEqualTo(Integer value) {
			addCriterion("Code <=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeIn(List<Integer> values) {
			addCriterion("Code in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotIn(List<Integer> values) {
			addCriterion("Code not in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeBetween(Integer value1, Integer value2) {
			addCriterion("Code between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotBetween(Integer value1, Integer value2) {
			addCriterion("Code not between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andStepcodeIsNull() {
			addCriterion("StepCode is null");
			return (Criteria) this;
		}

		public Criteria andStepcodeIsNotNull() {
			addCriterion("StepCode is not null");
			return (Criteria) this;
		}

		public Criteria andStepcodeEqualTo(String value) {
			addCriterion("StepCode =", value, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeNotEqualTo(String value) {
			addCriterion("StepCode <>", value, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeGreaterThan(String value) {
			addCriterion("StepCode >", value, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeGreaterThanOrEqualTo(String value) {
			addCriterion("StepCode >=", value, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeLessThan(String value) {
			addCriterion("StepCode <", value, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeLessThanOrEqualTo(String value) {
			addCriterion("StepCode <=", value, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeLike(String value) {
			addCriterion("StepCode like", value, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeNotLike(String value) {
			addCriterion("StepCode not like", value, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeIn(List<String> values) {
			addCriterion("StepCode in", values, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeNotIn(List<String> values) {
			addCriterion("StepCode not in", values, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeBetween(String value1, String value2) {
			addCriterion("StepCode between", value1, value2, "stepcode");
			return (Criteria) this;
		}

		public Criteria andStepcodeNotBetween(String value1, String value2) {
			addCriterion("StepCode not between", value1, value2, "stepcode");
			return (Criteria) this;
		}

		public Criteria andSerialnoIsNull() {
			addCriterion("SerialNo is null");
			return (Criteria) this;
		}

		public Criteria andSerialnoIsNotNull() {
			addCriterion("SerialNo is not null");
			return (Criteria) this;
		}

		public Criteria andSerialnoEqualTo(Integer value) {
			addCriterion("SerialNo =", value, "serialno");
			return (Criteria) this;
		}

		public Criteria andSerialnoNotEqualTo(Integer value) {
			addCriterion("SerialNo <>", value, "serialno");
			return (Criteria) this;
		}

		public Criteria andSerialnoGreaterThan(Integer value) {
			addCriterion("SerialNo >", value, "serialno");
			return (Criteria) this;
		}

		public Criteria andSerialnoGreaterThanOrEqualTo(Integer value) {
			addCriterion("SerialNo >=", value, "serialno");
			return (Criteria) this;
		}

		public Criteria andSerialnoLessThan(Integer value) {
			addCriterion("SerialNo <", value, "serialno");
			return (Criteria) this;
		}

		public Criteria andSerialnoLessThanOrEqualTo(Integer value) {
			addCriterion("SerialNo <=", value, "serialno");
			return (Criteria) this;
		}

		public Criteria andSerialnoIn(List<Integer> values) {
			addCriterion("SerialNo in", values, "serialno");
			return (Criteria) this;
		}

		public Criteria andSerialnoNotIn(List<Integer> values) {
			addCriterion("SerialNo not in", values, "serialno");
			return (Criteria) this;
		}

		public Criteria andSerialnoBetween(Integer value1, Integer value2) {
			addCriterion("SerialNo between", value1, value2, "serialno");
			return (Criteria) this;
		}

		public Criteria andSerialnoNotBetween(Integer value1, Integer value2) {
			addCriterion("SerialNo not between", value1, value2, "serialno");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("Type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("Type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(String value) {
			addCriterion("Type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(String value) {
			addCriterion("Type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(String value) {
			addCriterion("Type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(String value) {
			addCriterion("Type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(String value) {
			addCriterion("Type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(String value) {
			addCriterion("Type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLike(String value) {
			addCriterion("Type like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotLike(String value) {
			addCriterion("Type not like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<String> values) {
			addCriterion("Type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<String> values) {
			addCriterion("Type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(String value1, String value2) {
			addCriterion("Type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(String value1, String value2) {
			addCriterion("Type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andRefcodeIsNull() {
			addCriterion("RefCode is null");
			return (Criteria) this;
		}

		public Criteria andRefcodeIsNotNull() {
			addCriterion("RefCode is not null");
			return (Criteria) this;
		}

		public Criteria andRefcodeEqualTo(String value) {
			addCriterion("RefCode =", value, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeNotEqualTo(String value) {
			addCriterion("RefCode <>", value, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeGreaterThan(String value) {
			addCriterion("RefCode >", value, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeGreaterThanOrEqualTo(String value) {
			addCriterion("RefCode >=", value, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeLessThan(String value) {
			addCriterion("RefCode <", value, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeLessThanOrEqualTo(String value) {
			addCriterion("RefCode <=", value, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeLike(String value) {
			addCriterion("RefCode like", value, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeNotLike(String value) {
			addCriterion("RefCode not like", value, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeIn(List<String> values) {
			addCriterion("RefCode in", values, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeNotIn(List<String> values) {
			addCriterion("RefCode not in", values, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeBetween(String value1, String value2) {
			addCriterion("RefCode between", value1, value2, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefcodeNotBetween(String value1, String value2) {
			addCriterion("RefCode not between", value1, value2, "refcode");
			return (Criteria) this;
		}

		public Criteria andRefnameIsNull() {
			addCriterion("RefName is null");
			return (Criteria) this;
		}

		public Criteria andRefnameIsNotNull() {
			addCriterion("RefName is not null");
			return (Criteria) this;
		}

		public Criteria andRefnameEqualTo(String value) {
			addCriterion("RefName =", value, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameNotEqualTo(String value) {
			addCriterion("RefName <>", value, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameGreaterThan(String value) {
			addCriterion("RefName >", value, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameGreaterThanOrEqualTo(String value) {
			addCriterion("RefName >=", value, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameLessThan(String value) {
			addCriterion("RefName <", value, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameLessThanOrEqualTo(String value) {
			addCriterion("RefName <=", value, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameLike(String value) {
			addCriterion("RefName like", value, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameNotLike(String value) {
			addCriterion("RefName not like", value, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameIn(List<String> values) {
			addCriterion("RefName in", values, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameNotIn(List<String> values) {
			addCriterion("RefName not in", values, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameBetween(String value1, String value2) {
			addCriterion("RefName between", value1, value2, "refname");
			return (Criteria) this;
		}

		public Criteria andRefnameNotBetween(String value1, String value2) {
			addCriterion("RefName not between", value1, value2, "refname");
			return (Criteria) this;
		}

		public Criteria andRefheaderIsNull() {
			addCriterion("RefHeader is null");
			return (Criteria) this;
		}

		public Criteria andRefheaderIsNotNull() {
			addCriterion("RefHeader is not null");
			return (Criteria) this;
		}

		public Criteria andRefheaderEqualTo(String value) {
			addCriterion("RefHeader =", value, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderNotEqualTo(String value) {
			addCriterion("RefHeader <>", value, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderGreaterThan(String value) {
			addCriterion("RefHeader >", value, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderGreaterThanOrEqualTo(String value) {
			addCriterion("RefHeader >=", value, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderLessThan(String value) {
			addCriterion("RefHeader <", value, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderLessThanOrEqualTo(String value) {
			addCriterion("RefHeader <=", value, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderLike(String value) {
			addCriterion("RefHeader like", value, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderNotLike(String value) {
			addCriterion("RefHeader not like", value, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderIn(List<String> values) {
			addCriterion("RefHeader in", values, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderNotIn(List<String> values) {
			addCriterion("RefHeader not in", values, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderBetween(String value1, String value2) {
			addCriterion("RefHeader between", value1, value2, "refheader");
			return (Criteria) this;
		}

		public Criteria andRefheaderNotBetween(String value1, String value2) {
			addCriterion("RefHeader not between", value1, value2, "refheader");
			return (Criteria) this;
		}

		public Criteria andReffieldIsNull() {
			addCriterion("RefField is null");
			return (Criteria) this;
		}

		public Criteria andReffieldIsNotNull() {
			addCriterion("RefField is not null");
			return (Criteria) this;
		}

		public Criteria andReffieldEqualTo(String value) {
			addCriterion("RefField =", value, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldNotEqualTo(String value) {
			addCriterion("RefField <>", value, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldGreaterThan(String value) {
			addCriterion("RefField >", value, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldGreaterThanOrEqualTo(String value) {
			addCriterion("RefField >=", value, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldLessThan(String value) {
			addCriterion("RefField <", value, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldLessThanOrEqualTo(String value) {
			addCriterion("RefField <=", value, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldLike(String value) {
			addCriterion("RefField like", value, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldNotLike(String value) {
			addCriterion("RefField not like", value, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldIn(List<String> values) {
			addCriterion("RefField in", values, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldNotIn(List<String> values) {
			addCriterion("RefField not in", values, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldBetween(String value1, String value2) {
			addCriterion("RefField between", value1, value2, "reffield");
			return (Criteria) this;
		}

		public Criteria andReffieldNotBetween(String value1, String value2) {
			addCriterion("RefField not between", value1, value2, "reffield");
			return (Criteria) this;
		}

		public Criteria andAddedbyIsNull() {
			addCriterion("AddedBy is null");
			return (Criteria) this;
		}

		public Criteria andAddedbyIsNotNull() {
			addCriterion("AddedBy is not null");
			return (Criteria) this;
		}

		public Criteria andAddedbyEqualTo(String value) {
			addCriterion("AddedBy =", value, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyNotEqualTo(String value) {
			addCriterion("AddedBy <>", value, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyGreaterThan(String value) {
			addCriterion("AddedBy >", value, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyGreaterThanOrEqualTo(String value) {
			addCriterion("AddedBy >=", value, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyLessThan(String value) {
			addCriterion("AddedBy <", value, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyLessThanOrEqualTo(String value) {
			addCriterion("AddedBy <=", value, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyLike(String value) {
			addCriterion("AddedBy like", value, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyNotLike(String value) {
			addCriterion("AddedBy not like", value, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyIn(List<String> values) {
			addCriterion("AddedBy in", values, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyNotIn(List<String> values) {
			addCriterion("AddedBy not in", values, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyBetween(String value1, String value2) {
			addCriterion("AddedBy between", value1, value2, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedbyNotBetween(String value1, String value2) {
			addCriterion("AddedBy not between", value1, value2, "addedby");
			return (Criteria) this;
		}

		public Criteria andAddedonIsNull() {
			addCriterion("AddedOn is null");
			return (Criteria) this;
		}

		public Criteria andAddedonIsNotNull() {
			addCriterion("AddedOn is not null");
			return (Criteria) this;
		}

		public Criteria andAddedonEqualTo(Date value) {
			addCriterion("AddedOn =", value, "addedon");
			return (Criteria) this;
		}

		public Criteria andAddedonNotEqualTo(Date value) {
			addCriterion("AddedOn <>", value, "addedon");
			return (Criteria) this;
		}

		public Criteria andAddedonGreaterThan(Date value) {
			addCriterion("AddedOn >", value, "addedon");
			return (Criteria) this;
		}

		public Criteria andAddedonGreaterThanOrEqualTo(Date value) {
			addCriterion("AddedOn >=", value, "addedon");
			return (Criteria) this;
		}

		public Criteria andAddedonLessThan(Date value) {
			addCriterion("AddedOn <", value, "addedon");
			return (Criteria) this;
		}

		public Criteria andAddedonLessThanOrEqualTo(Date value) {
			addCriterion("AddedOn <=", value, "addedon");
			return (Criteria) this;
		}

		public Criteria andAddedonIn(List<Date> values) {
			addCriterion("AddedOn in", values, "addedon");
			return (Criteria) this;
		}

		public Criteria andAddedonNotIn(List<Date> values) {
			addCriterion("AddedOn not in", values, "addedon");
			return (Criteria) this;
		}

		public Criteria andAddedonBetween(Date value1, Date value2) {
			addCriterion("AddedOn between", value1, value2, "addedon");
			return (Criteria) this;
		}

		public Criteria andAddedonNotBetween(Date value1, Date value2) {
			addCriterion("AddedOn not between", value1, value2, "addedon");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.WFReferences
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dbo.WFReferences
     *
     * @mbg.generated do_not_delete_during_merge Sun Dec 09 14:43:41 GST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}