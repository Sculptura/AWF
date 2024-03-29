package com.awf.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.awf.model.dbo.WFUsersDBO;
import com.awf.model.orm.dbo.WFUsers;
import com.awf.model.orm.dbo.WFUsersExample;

public interface WFUsersMapper {

	

	////////////////////////////////////////////////////
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	long countByExample(WFUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int deleteByExample(WFUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int deleteByPrimaryKey(Integer usercode);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int insert(WFUsers record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int insertSelective(WFUsers record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	List<WFUsers> selectByExample(WFUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	WFUsers selectByPrimaryKey(Integer usercode);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int updateByExampleSelective(@Param("record") WFUsers record, @Param("example") WFUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int updateByExample(@Param("record") WFUsers record, @Param("example") WFUsersExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int updateByPrimaryKeySelective(WFUsers record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.WFUsers
	 * @mbg.generated  Fri Apr 12 12:46:47 IST 2019
	 */
	int updateByPrimaryKey(WFUsers record);

	WFUsersDBO selectByUser(WFUsersDBO currentUser);
	
	List<WFUsersDBO> fetchActiveUsers();
	
	void resetUserLogin(@Param("usercode") Integer usercode);

	void resetAdmin();

	Integer findUsersOnline();

	void updateUserOnlineStatus(WFUsersDBO currentUser);
	

	String checkOnlineStatus(@Param("usercode") Integer usercode);

	void updateUserOfflineStatus(WFUsersDBO currentUser);

	WFUsersDBO checkDocAvailability(@Param("currentDoc")String currentDoc);

	List<WFUsersDBO> fetchUsersForMessenger(Integer usercode);

	WFUsersDBO fetchUserByCode(Integer ucode);

	
	
	
	
	
	
}