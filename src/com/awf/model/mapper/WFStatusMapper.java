package com.awf.model.mapper;

import com.awf.model.orm.dbo.WFStatus;
import com.awf.model.orm.dbo.WFStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WFStatusMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	long countByExample(WFStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int deleteByExample(WFStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int deleteByPrimaryKey(Integer code);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int insert(WFStatus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int insertSelective(WFStatus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	List<WFStatus> selectByExample(WFStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	WFStatus selectByPrimaryKey(Integer code);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int updateByExampleSelective(@Param("record") WFStatus record, @Param("example") WFStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int updateByExample(@Param("record") WFStatus record, @Param("example") WFStatusExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int updateByPrimaryKeySelective(WFStatus record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFStatus
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int updateByPrimaryKey(WFStatus record);
}