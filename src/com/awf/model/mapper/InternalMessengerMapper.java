package com.awf.model.mapper;

import com.awf.model.dbo.InternalMessengerDBO;
import com.awf.model.orm.dbo.InternalMessenger;
import com.awf.model.orm.dbo.InternalMessengerExample;
import com.awf.model.orm.dbo.InternalMessengerKey;
import com.awf.model.orm.dbo.InternalMessengerWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InternalMessengerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    long countByExample(InternalMessengerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int deleteByExample(InternalMessengerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int deleteByPrimaryKey(InternalMessengerKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int insert(InternalMessengerWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int insertSelective(InternalMessengerWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    List<InternalMessengerWithBLOBs> selectByExampleWithBLOBs(InternalMessengerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    List<InternalMessenger> selectByExample(InternalMessengerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    InternalMessengerWithBLOBs selectByPrimaryKey(InternalMessengerKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int updateByExampleSelective(@Param("record") InternalMessengerWithBLOBs record, @Param("example") InternalMessengerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") InternalMessengerWithBLOBs record, @Param("example") InternalMessengerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int updateByExample(@Param("record") InternalMessenger record, @Param("example") InternalMessengerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int updateByPrimaryKeySelective(InternalMessengerWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int updateByPrimaryKeyWithBLOBs(InternalMessengerWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.InternalMessenger
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    int updateByPrimaryKey(InternalMessenger record);
    
    ///////////////////////////////////////////////////////////////////
    
    
    
    
    
    List<InternalMessengerDBO> refreshMessageThread(@Param("connectionID") String connectionID, @Param("usercode")String usercode);

	Integer getMaxMessgeID(InternalMessengerDBO imessengerDBO2);

	InternalMessengerDBO fetchRecentConversations(@Param("connectionID") String connectionID, @Param("usercode")String usercode);

	void deleteConversation(InternalMessengerDBO imessengerDBO);

	InternalMessengerDBO findMessage(InternalMessengerDBO internalMessengerDBO);

	void deleteConversationForBoth(InternalMessengerDBO internalMessengerDBO);
    
    
    
    
    
    
    
    
}