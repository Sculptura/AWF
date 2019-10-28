package com.awf.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;

import com.awf.model.dbo.IMConnectionsDBO;
import com.awf.model.dbo.InternalMessengerDBO;
import com.awf.model.dbo.UsersDBO;
import com.awf.model.dbo.WFUsersDBO;
import com.awf.model.mapper.IMConnectionsMapper;
import com.awf.model.mapper.InternalMessengerMapper;
import com.awf.model.mapper.WFUsersMapper;
import com.awf.utilities.SqlConnection;

@ManagedBean(name = "imessengerMB")
@ViewScoped
public class InternalMessengerMB implements Serializable {

	@ManagedProperty(value = "#{usersMB}")
	private UsersMB usersMB;

	private static final long serialVersionUID = -36758796333458940L;
	private InternalMessengerDBO imessengerDBO;
	private List<InternalMessengerDBO> imessagesList;

	private List<InternalMessengerDBO> recentConversations;

	private List<WFUsersDBO> usersList;
	private UsersDBO selectedUser;

	IMConnectionsDBO imConnection;

	private String headerName;
	private String headerStatus;

	private Boolean sendBtn = false;

	private Boolean messengerRunning = false;
	private Boolean messageThreadOpen = false;

	private int rowNum;

	private Date today = new Date();

	volatile Logger log = Logger.getLogger(InternalMessengerMB.class);

	public InternalMessengerMB() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void initialize() {

		fetchAllUsers();
	}

	public void fetchAllUsers() {

		SqlSession session = null;

		try {
			session = SqlConnection.getInstance().openSession(); 
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
					.getValue(elContext, null, "usersMB");

			WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);
			setUsersList(userMap.fetchUsersForMessenger(usersMB.getCurrentUser().getUsercode()));

		} catch (NullPointerException | PersistenceException | IOException e) {

			log.info(e.toString());

		} finally {
			session.close();

		}
		
		
		PrimeFaces.current().ajax().update("internalIMForm:contactGrid");
	}

	public void openConversation(InternalMessengerDBO openthread) {

		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "usersMB");

		UsersDBO userInst = new UsersDBO();
		userInst.setUsercode(openthread.getUsercode().equals(usersMB.getCurrentUser().getUsercode())
				? openthread.getRecipientusercode()
				: openthread.getUsercode());

		// get a connection if exists at all
		checkConnection(userInst);

		imessengerDBO = new InternalMessengerDBO();
		imessengerDBO.setUsercode(usersMB.getCurrentUser().getUsercode()); 
		imessengerDBO.setRecipientusercode(userInst.getUsercode());

		if (recentConversations != null && recentConversations.size() > 0) {
			// imConnection = new IMConnectionsDBO();
			// imConnection.setConnectionid(openthread.getConnectionid());

			refreshMessageThread();
			setHeaderName(openthread.getUserName());
			setHeaderStatus("get status of current user");
		}

		setMessageThreadOpen(true);
		setMessengerRunning(false);

		PrimeFaces.current().ajax().update("internalIMForm:internalIMDetails");
		PrimeFaces.current().executeScript("$('.ui-scrollpanel-native').scrollTop(100000)");

	}

	public void beginConversation(UsersDBO userIntended) {

		// Find/Establish connection (must return a connection id)
		checkConnection(userIntended);

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "usersMB");

		// start message thread...
		if (imessengerDBO == null) {

			imessengerDBO = new InternalMessengerDBO();
		}

		imessengerDBO.setUsercode(usersMB.getCurrentUser().getUsercode());
		imessengerDBO.setRecipientusercode(userIntended.getUsercode());
		imessengerDBO.setConnectionid(imConnection.getConnectionid());

		setImessagesList(new ArrayList<InternalMessengerDBO>());

		setHeaderName(userIntended.getUsername());
		setHeaderStatus("get status of current user");

		setMessageThreadOpen(true);
		// setMessengerRunning(false);
		
		PrimeFaces.current().ajax().update("internalIMForm:internalIMDetails");
	}

	public void controlMessengerPoll() {
		
		if (messengerRunning) {
			fetchRecentConversations();
		}

		if (messageThreadOpen) {
			refreshMessageThread();
		}

		PrimeFaces.current().ajax().update("internalIMForm:internalIMDetails");
		PrimeFaces.current().ajax().update("internalIMForm:msgThreadDetails");

	}

	public void backToRecent() {
		setMessengerRunning(true);
		setMessageThreadOpen(false);

		if (imessagesList == null || imessagesList.size() < 1) {

			// deleteConnection(imConnection);
		}

	}

	public void fetchRecentConversations() {

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "usersMB");

		// find active connections of the current user...

		
		SqlSession session = null;
		try {
			session = SqlConnection.getIMSession().openSession();

			IMConnectionsMapper imCMap = session.getMapper(IMConnectionsMapper.class);
			List<IMConnectionsDBO> imcList = new ArrayList<IMConnectionsDBO>();
			imcList = imCMap.findActiveConnections(usersMB.getCurrentUser().getUsercode());

			setRecentConversations(new ArrayList<InternalMessengerDBO>());
			if (imcList != null && imcList.size() > 0) {
				for (int i = 0; i < imcList.size(); i++) {

					InternalMessengerDBO recentMsg = new InternalMessengerDBO();
					// recentMsg.setConnectionid(imcList.get(i).getConnectionid());
					InternalMessengerMapper imConnectMap = session.getMapper(InternalMessengerMapper.class);
					recentMsg = imConnectMap.fetchRecentConversations(imcList.get(i).getConnectionid(),
							usersMB.getCurrentUser().getUsercode().toString());

					if (recentMsg != null && recentMsg.getUsercode() != null) {
						recentConversations.add(recentMsg);
					}
				}
			}

			if (recentConversations != null && recentConversations.size() > 0) {
				setRecentConversationUI(recentConversations);
			}

		} catch (NullPointerException | PersistenceException e) {
			log.info(e.toString());
		} finally {
			session.close();

		}

		setMessengerRunning(true);

		PrimeFaces.current().ajax().update("internalIMForm:recentGrid");
		PrimeFaces.current().ajax().update("internalIMForm:internalIMDetails");
	}

	private void setRecentConversationUI(List<InternalMessengerDBO> recentList) {

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "usersMB");

		for (int i = 0; i < recentList.size(); i++) {
			WFUsersDBO userInst = new WFUsersDBO();

			if (recentList.get(i).getUsercode().equals(usersMB.getCurrentUser().getUsercode())) {

				recentList.get(i).setUsercode(recentList.get(i).getRecipientusercode());
				recentList.get(i).setMessagebody("Me : " + recentList.get(i).getMessagebody());
			}

			userInst.setUsercode(recentList.get(i).getUsercode());
			userInst = usersMB.fetchUserByUserCode(userInst.getUsercode());

			recentList.get(i).setUserName(userInst.getUsername());
			//recentList.get(i).setDepartmentName(userInst.getDepartmentName());
			//recentList.get(i).setDesignationName(userInst.getDesignationName());

		}

	}

	private void checkConnection(UsersDBO userIntended) {

		SqlSession session = null;
		try {
			
	
			session = SqlConnection.getIMSession().openSession();
			
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
					.getValue(elContext, null, "usersMB");

			imConnection = new IMConnectionsDBO();

			IMConnectionsMapper imConnectMap = session.getMapper(IMConnectionsMapper.class);
			setImConnection(
					imConnectMap.checkConnection(usersMB.getCurrentUser().getUsercode(), userIntended.getUsercode()));

			if (imConnection == null) {

				setImConnection(creatNewConnection(usersMB.getCurrentUser().getUsercode(), userIntended.getUsercode()));

				imConnectMap.insert(imConnection);
				session.commit();
			}

		} catch (NullPointerException | PersistenceException e) {
			log.info(e.toString());
		} finally {
			session.close();

		}
		
		
		
		PrimeFaces.current().ajax().update("internalIMForm:recentMsgsScrollPanel");

	}

	// generates random messageID
	private IMConnectionsDBO creatNewConnection(Integer currentUser, Integer intendedUser) {

		IMConnectionsDBO newIM = new IMConnectionsDBO();
		// Connection ID generator
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		String lower = upper.toLowerCase(Locale.ROOT);

		String digits = "0123456789";

		String chars = upper + lower + digits;

		Random rand = new Random();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < 15; i++) {
			buf.append(chars.charAt(rand.nextInt(chars.length())));
		}

		newIM.setConnectionid(buf.toString());
		newIM.setEstablishedon(new Date());
		newIM.setStatus("A");
		newIM.setUser1(currentUser);
		newIM.setUser2(intendedUser);

		return newIM;

	}

	public void sendMessage() {

		

		if (imessengerDBO == null) {
			imessengerDBO = new InternalMessengerDBO();

			imessengerDBO.setUsercode(imConnection.getUser1());
			imessengerDBO.setRecipientusercode(imConnection.getUser2());

		}

		if (imessengerDBO.getMessagebody() == null || StringUtils.isBlank(imessengerDBO.getMessagebody())) {
			return;
		}

		imessengerDBO.setConnectionid(imConnection.getConnectionid());
		imessengerDBO.setMessagedatetime(new Date());
		imessengerDBO.setMessageid(messageIDGenerator(imessengerDBO));
		imessengerDBO.setDeletestat("A");

		SqlSession session = null;
		try {
			session = SqlConnection.getIMSession().openSession();
			log.info("SQL Session :  Created ");
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
					.getValue(elContext, null, "usersMB");

			InternalMessengerMapper imMap = session.getMapper(InternalMessengerMapper.class);
			imMap.insert(imessengerDBO);
			session.commit();

		} catch (NullPointerException | PersistenceException e) {
			log.info(e.toString());
		} finally {
			session.close();

		}
		setSendBtn(true);
		refreshMessageThread();
		imessengerDBO.setMessagebody(null);
		PrimeFaces.current().executeScript("$('.ui-scrollpanel-native').scrollTop(100000)");

		PrimeFaces.current().ajax().update("internalIMForm:threadGrid");
		PrimeFaces.current().ajax().update("internalIMForm:msgThreadDetails");

		PrimeFaces.current().ajax().update("internalIMForm:sendBtn");

	}

	public void deleteConversation() {
		FacesContext contextF = FacesContext.getCurrentInstance();
		

		if (imessengerDBO == null) {
			imessengerDBO = new InternalMessengerDBO();

			imessengerDBO.setUsercode(imConnection.getUser1());
			imessengerDBO.setRecipientusercode(imConnection.getUser2());

		}

		imessengerDBO.setConnectionid(imConnection.getConnectionid());

		if (imessengerDBO.getConnectionid() == null) {

			contextF.addMessage(null, new FacesMessage("Not found!", "cannot verify Conversation"));
			PrimeFaces.current().ajax().update("internalIMForm:payrollStat");
			return;
		}

		SqlSession session = null;
		try {
			session = SqlConnection.getIMSession().openSession();
			InternalMessengerMapper imMap = session.getMapper(InternalMessengerMapper.class);

			if (imessagesList == null) {

			} else {

				for (int i = 0; i < imessagesList.size(); i++) {
					InternalMessengerDBO msgInst = new InternalMessengerDBO();
					msgInst = imMap.findMessage(imessagesList.get(i));

					if (msgInst != null && !msgInst.getDeletestat().equals("A")) {
						imMap.deleteConversationForBoth(imessagesList.get(i));

					} else {
						imMap.deleteConversation(imessagesList.get(i));
					}
				}

			}

			session.commit();

		} catch (NullPointerException | PersistenceException e) {
			log.info(e.toString());
		} finally {
			session.close();

		}
		setSendBtn(true);
		refreshMessageThread();
		imessengerDBO.setMessagebody(null);
		PrimeFaces.current().executeScript("$('.ui-scrollpanel-native').scrollTop(100000)");
		PrimeFaces.current().executeScript("PF('msgThreadDlg').hide();");

		PrimeFaces.current().ajax().update("internalIMForm:threadGrid");
		PrimeFaces.current().ajax().update("internalIMForm:msgThreadDetails");

	}

	public void deactivator() {
		setSendBtn(false);
		
		PrimeFaces.current().ajax().update("internalIMForm:sendBtn");
	}

	public void activator() {
		setSendBtn(true);
		
		PrimeFaces.current().ajax().update("internalIMForm:sendBtn");

	}

	private String messageIDGenerator(InternalMessengerDBO imessengerDBO2) {

		SqlSession session = null;
		try {
			List<InternalMessengerDBO> tempList = new ArrayList<InternalMessengerDBO>();

			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
					.getValue(elContext, null, "usersMB");

			session = SqlConnection.getIMSession().openSession();
			InternalMessengerMapper imMap = session.getMapper(InternalMessengerMapper.class);
			tempList = imMap.refreshMessageThread(imConnection.getConnectionid(),
					usersMB.getCurrentUser().getUsercode().toString());

			setImessagesList(tempList == null ? new ArrayList<InternalMessengerDBO>() : tempList);

			Integer maxMsgNum = imMap.getMaxMessgeID(imessengerDBO2);
			maxMsgNum++;

			String formatted = String.format("%010d", maxMsgNum);
			return "IM" + formatted;

		} catch (NullPointerException | PersistenceException e) {
			log.info(e.toString());
		} finally {
			session.close();

		}

		return null;
	}

	public void messengerClosed(CloseEvent event) {
		setMessengerRunning(false);
		setMessageThreadOpen(false);

		

		PrimeFaces.current().executeScript("PF('messengerPoller').stop();");

	}

	public void closeActionIM() {
		

		PrimeFaces.current().executeScript("PF('internalIMDlg').hide();");

	}

	public void messageThreadClosed(CloseEvent event) {

		setMessageThreadOpen(false);

		// check for any conversations... if none .. remove the connection
		if (imessagesList == null || imessagesList.size() < 1) {

			// deleteConnection(imConnection);
		}

	}

	private void deleteConnection(IMConnectionsDBO connection) {

		SqlSession session = null;
		try {
			session = SqlConnection.getIMSession().openSession();
			log.info("SQL Session :  Created ");
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
					.getValue(elContext, null, "usersMB");

			IMConnectionsMapper connectMap = session.getMapper(IMConnectionsMapper.class);
			connectMap.deleteConnection(connection);

		} catch (NullPointerException | PersistenceException e) {
			log.info(e.toString());
		} finally {
			session.close();

		}
		
		PrimeFaces.current().ajax().update("internalIMForm:threadGrid");
		PrimeFaces.current().ajax().update("internalIMForm:recentGrid");

	}

	public void closeActionMessageThread() {
		

		PrimeFaces.current().executeScript("PF('msgThreadDlg').hide();");

	}

	public void refreshMessageThread() {
		

		SqlSession session = null;
		try {
			session = SqlConnection.getIMSession().openSession(); 
			InternalMessengerMapper imMap = session.getMapper(InternalMessengerMapper.class);
			setImessagesList(imMap.refreshMessageThread(imConnection.getConnectionid(),
					usersMB.getCurrentUser().getUsercode().toString()));
			settingMessagePosition(imessagesList);

		} catch (NullPointerException | PersistenceException e) {
			log.info(e.toString());
		} finally {
			session.close();

		}
		
		fetchRecentConversations();

		System.out.print("ping from" + usersMB.getCurrentUser().getUsercode());
		PrimeFaces.current().ajax().update("internalIMForm:threadGrid");
		PrimeFaces.current().ajax().update("internalIMForm:namePanel");
		PrimeFaces.current().ajax().update("internalIMForm:msgThreadDetails");
		PrimeFaces.current().ajax().update("internalIMForm:msgThreadPopup");

	}

	private void settingMessagePosition(List<InternalMessengerDBO> threadList) {

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		UsersMB usersMB = (UsersMB) FacesContext.getCurrentInstance().getApplication().getELResolver()
				.getValue(elContext, null, "usersMB");

		for (int i = 0; i < threadList.size(); i++) {
			if (threadList.get(i).getUsercode().equals(usersMB.getCurrentUser().getUsercode())) {
				threadList.get(i).setMessageFloatLeft(true);
			} else {
				threadList.get(i).setMessageFloatLeft(false);
			}

		}
	}

	public InternalMessengerDBO getImessengerDBO() {
		return imessengerDBO;
	}

	public void setImessengerDBO(InternalMessengerDBO imessengerDBO) {
		this.imessengerDBO = imessengerDBO;
	}

	public List<InternalMessengerDBO> getImessagesList() {
		return imessagesList;
	}

	public void setImessagesList(List<InternalMessengerDBO> imessagesList) {
		this.imessagesList = imessagesList;
	}


	public UsersDBO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UsersDBO selectedUser) {
		this.selectedUser = selectedUser;
	}

	public IMConnectionsDBO getImConnection() {
		return imConnection;
	}

	public void setImConnection(IMConnectionsDBO imConnection) {
		this.imConnection = imConnection;
	}

	public List<InternalMessengerDBO> getRecentConversations() {
		return recentConversations;
	}

	public void setRecentConversations(List<InternalMessengerDBO> recentConversations) {
		this.recentConversations = recentConversations;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderStatus() {
		return headerStatus;
	}

	public void setHeaderStatus(String headerStatus) {
		this.headerStatus = headerStatus;
	}

	public Boolean getMessengerRunning() {
		return messengerRunning;
	}

	public void setMessengerRunning(Boolean messengerRunning) {
		this.messengerRunning = messengerRunning;
	}

	public Boolean getMessageThreadOpen() {
		return messageThreadOpen;
	}

	public void setMessageThreadOpen(Boolean messageThreadOpen) {
		this.messageThreadOpen = messageThreadOpen;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public Boolean getSendBtn() {
		return sendBtn;
	}

	public void setSendBtn(Boolean sendBtn) {
		this.sendBtn = sendBtn;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public UsersMB getUsersMB() {
		return usersMB;
	}

	public void setUsersMB(UsersMB usersMB) {
		this.usersMB = usersMB;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<WFUsersDBO> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<WFUsersDBO> usersList) {
		this.usersList = usersList;
	}

}
