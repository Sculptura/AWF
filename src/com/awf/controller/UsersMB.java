package com.awf.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import com.awf.model.dbo.UsersDBO;
import com.awf.model.dbo.WFUsersDBO;
import com.awf.model.mapper.WFUsersMapper;
import com.awf.utilities.SqlConnection;

@ManagedBean(name = "usersMB")
@SessionScoped
public class UsersMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 559679058509770040L;

	private String userName;
	private String password;
	
	
	private WFUsersDBO currentUser;
	private WFUsersDBO user;

	private Boolean saveBtn = true;
	private Boolean undoBtn = true;
	private Boolean deleteBtn = false;
	private Boolean searchBtn = false;
	private List<WFUsersDBO> userList;	
	private List<WFUsersDBO> userListFilter;	
	volatile Logger log = Logger.getLogger(UsersMB.class);

	@PostConstruct
	public void initialize() {

		currentUser = new WFUsersDBO();
		
				
	}
	
	
	
	
	public WFUsersDBO fetchUserByUserCode(Integer ucode) {
		SqlSession session = null;
		try {
			FacesContext contextF = FacesContext.getCurrentInstance();
			

			session = SqlConnection.getInstance().openSession();
			WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);
			
			return userMap.fetchUserByCode(ucode);  
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			session.close();
		}
		return null;
		
		
		
	}

	
	private Integer loginCount = 0;

	public String checkAuthentication() {
		FacesContext contextF = FacesContext.getCurrentInstance();
		

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			String dateInString = "01-07-2019 10:20:56";
			Date dateInst = sdf.parse(dateInString); 

			Date today = new Date(); 
			if (today.compareTo(dateInst) > 0) {

				System.out.println("Error 89E42Tx");
				return "error";
			}

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SqlSession session = null;
		String result = "";

		try {

			ExternalContext externalContext = contextF.getExternalContext();

			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			request.getSession(false);

			currentUser = new WFUsersDBO();
			currentUser.setUsername(userName);
			currentUser.setPassword(password);
			

			session = SqlConnection.getInstance().openSession();
			WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);

			/*
			 * // ADMIN RESET if (userName.equals("safe") && password.equals("getin")) {
			 * 
			 * userMap.resetAdmin(); session.commit();
			 * System.out.println("SG reset performed on " + new Date()); return "reset"; }
			 */

			currentUser = userMap.selectByUser(currentUser);

			if (currentUser != null && usersOnline()) {

				/*
				 * InetAddress ip = InetAddress.getLocalHost(); NetworkInterface network =
				 * NetworkInterface.getByInetAddress(ip); byte[] mac =
				 * network.getHardwareAddress();
				 * 
				 * StringBuilder sb = new StringBuilder(); for (int i = 0; i < mac.length; i++)
				 * { sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" :
				 * "")); } System.out.println("CAc : " + sb.toString());
				 * System.out.println("System name :" + request.getRemoteUser());
				 * 
				 * if (currentUser.getSysname().equals("0") ||
				 * currentUser.getSysname().equals(sb.toString())) {
				 * 
				 * if (currentUser.getLastloginsrc() != null &&
				 * !currentUser.getLastloginsrc().equals(request.getRemoteAddr())) {
				 * 
				 * if (currentUser.getAccess().equals("Y")) { contextF.addMessage(null, new
				 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Login not allowed",
				 * "Login already in use"));
				 * PrimeFaces.current().ajax().update("loginForm:payrollStat");
				 * 
				 * System.out.println("Login failed for " + userName); return "error";
				 * 
				 * }
				 * 
				 * }
				 * 
				 * } else {
				 * 
				 * contextF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				 * "Login not allowed", "Login Violation"));
				 * PrimeFaces.current().ajax().update("loginForm:payrollStat");
				 * 
				 * System.out.println("Login failed for " + userName + ". Violation!"); return
				 * "error"; }
				 */

				FacesContext fCtx = FacesContext.getCurrentInstance();
				HttpSession session2 = (HttpSession) fCtx.getExternalContext().getSession(false);
				currentUser.setSessionID(session2.getId());

				//setQualified(currentUser.getUsercode().equals(1) ? true : false);

				currentUser.setLastloginsrc(request.getRemoteAddr()); 
				currentUser.setLastlogin(new java.util.Date());
				//currentUser.setSysname(sb.toString());
				System.out.println("HTTP REQUEST came from " + request.getRemoteAddr());

				updateUserOnlineStatus();
				//fetchUserRightsList(currentUser);

				result = "/secured/masterLayout";

			} else {
				loginCount = loginCount + 1;
				System.out.println("Failed attempts" + loginCount + " for user " + userName);

				result = "failure";
				contextF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication Failed",
						"Credentials were incorrect"));
				PrimeFaces.current().ajax().update("loginForm:payrollStat");

			}

		} catch (NullPointerException | PersistenceException |

				IOException e) {
			// TODO Auto-generated catch block
			log.info(e.toString());
		} finally {

			try {
				session.close();
				log.info("SqlSession Closed ");
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				log.info(e.toString());
			}

		}

		return result;

	}
	
	public void fetchActiveUsers() {
		
		SqlSession session = null;

		try {

			session = SqlConnection.getInstance().openSession();
			WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);
			setUserList(userMap.fetchActiveUsers());

			PrimeFaces.current().ajax().update("centralForm:userSearchForm:userTable");

		} catch ( NullPointerException | PersistenceException | IOException e) {
			// TODO Auto-generated catch block
			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				log.info(e.toString());
			}

		}

	}
	
	
	private boolean usersOnline() {

		Integer onlineCount = 0;
		SqlSession session = null;
		try {

			session = SqlConnection.getInstance().openSession();
			WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);

			onlineCount = userMap.findUsersOnline();

			if (onlineCount > 5) {
				System.out.println(onlineCount + " online.");
				return false;
			} else {
				System.out.println(onlineCount + " online.");
				return true;
			}

		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public void resetUserLogin() {

		SqlSession session = null;
		try {
			FacesContext contextF = FacesContext.getCurrentInstance();
			

			if (currentUser.getUsercode().equals(1) && !user.getUsercode().equals(1)) {

				session = SqlConnection.getInstance().openSession();
				WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);

				userMap.resetUserLogin(user.getUsercode());
				session.commit();

				contextF.addMessage(null,
						new FacesMessage("Update Successful", "Login for " + user.getUsercode() + " has been reset"));
				PrimeFaces.current().ajax().update("centralForm:userAccForm:payrollStat");

			} else {
				contextF.addMessage(null, new FacesMessage("Not Allowed", ""));
				PrimeFaces.current().ajax().update("centralForm:userAccForm:payrollStat");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void sessionTimeout() {
		
		FacesContext contextF = FacesContext.getCurrentInstance();
		
		
		setExpCount(10); 
		PrimeFaces.current().executeScript("PF('sessionInvDlg').show();");
		PrimeFaces.current().executeScript("PF('onlinePoll').stop();"); 			
		setInvSessionFlag(true);
		PrimeFaces.current().ajax().update("headerForm:invPanel");
		
	}

	public void checkOnlineStatus() {

		SqlSession session = null;
		try {
			FacesContext contextF = FacesContext.getCurrentInstance();
			

			session = SqlConnection.getInstance().openSession(); 
			WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);
			String onlineStat = userMap.checkOnlineStatus(currentUser.getUsercode());
			
			System.out.println("ping 4m " +currentUser.getUsercode()); 

			if (onlineStat.equals("N")) {
				setExpCount(10); 
				PrimeFaces.current().executeScript("PF('sessionInvDlg').show();");
				PrimeFaces.current().executeScript("PF('onlinePoll').stop();"); 			
				setInvSessionFlag(true);
				PrimeFaces.current().ajax().update("headerForm:invPanel");

			}

		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int expCount = 10;
	private Boolean invSessionFlag = false; 

	public void decrementCounter() {
		
		
		try {
			expCount--;
			System.out.println(currentUser.getUsercode()+" logging out in "+expCount +" secs");
			if (expCount == 0) {
				PrimeFaces.current().executeScript("PF('timeoutPoller').stop();"); 
				logout(); 
			}
			
			PrimeFaces.current().ajax().update("headerForm:invPanel");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private Boolean changesMadeFlag = false;
	
	public void beforeLogout() {
		try {
			
			if (changesMadeFlag) {
				PrimeFaces.current().executeScript("PF('logoutDlg').show();");

			} else {
				logout();
			}

			// PrimeFaces.current().ajax().update("centralForm:userAccForm:userAccDetails");
		} catch (IOException | NullPointerException | PersistenceException e) {
			// TODO Auto-generated catch block
			log.info(e.toString());
		}

	}

	public void logout() throws IOException {

		updateUserOfflineStatus();

		setUser(new WFUsersDBO());
		setCurrentUser(new WFUsersDBO());
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		// externalContext.invalidateSession();

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		externalContext.redirect(externalContext.getRequestContextPath() + "/index.html?faces-redirect=true");

	}
	
	public void updateUserOnlineStatus() {
		SqlSession session = null;

		try {

			
			PrimeFaces.current().executeScript("PF('timeoutPoller').stop();");
			PrimeFaces.current().executeScript("PF('onlinePoll').start();");  

			session = SqlConnection.getInstance().openSession();
			log.info("SQL Session :  Created ");
			WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);

			System.out.println(currentUser.getUsercode() + " logging in ");

			userMap.updateUserOnlineStatus(currentUser);

			System.out.println(currentUser.getUsercode() + " log in successful at "+new Date());
			session.commit();

		} catch ( NullPointerException | PersistenceException | IOException e) {
			// TODO Auto-generated catch block
			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				log.info(e.toString());
			}

		}

	}

	
	public void updateUserOfflineStatus() {
		SqlSession session = null;

		try {
 
			session = SqlConnection.getInstance().openSession();
			log.info("SQL Session :  Created ");
			WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);

			System.out.println(currentUser.getUsercode() + " logging out ");

			userMap.updateUserOfflineStatus(currentUser);
			session.commit();

			System.out.println(currentUser.getUsercode() + " log out successful at "+new Date());

		} catch (NullPointerException | PersistenceException | IOException e) {
			// TODO Auto-generated catch block
			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				log.info(e.toString());
			}

		}

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getSaveBtn() {
		return saveBtn;
	}

	public void setSaveBtn(Boolean saveBtn) {
		this.saveBtn = saveBtn;
	}

	public Boolean getUndoBtn() {
		return undoBtn;
	}

	public void setUndoBtn(Boolean undoBtn) {
		this.undoBtn = undoBtn;
	}

	public Boolean getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(Boolean deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public Boolean getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(Boolean searchBtn) {
		this.searchBtn = searchBtn;
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

	public int getExpCount() {
		return expCount;
	}

	public void setExpCount(int expCount) {
		this.expCount = expCount;
	}

	public Boolean getInvSessionFlag() {
		return invSessionFlag;
	}

	public void setInvSessionFlag(Boolean invSessionFlag) {
		this.invSessionFlag = invSessionFlag;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}


	public Boolean getChangesMadeFlag() {
		return changesMadeFlag;
	}


	public void setChangesMadeFlag(Boolean changesMadeFlag) {
		this.changesMadeFlag = changesMadeFlag;
	}


	public void setCurrentUser(WFUsersDBO currentUser) {
		this.currentUser = currentUser;
	}




	public void setUser(WFUsersDBO user) {
		this.user = user;
	}




	public WFUsersDBO getCurrentUser() {
		return currentUser;
	}




	public WFUsersDBO getUser() {
		return user;
	}




	public List<WFUsersDBO> getUserListFilter() {
		return userListFilter;
	}




	public void setUserListFilter(List<WFUsersDBO> userListFilter) {
		this.userListFilter = userListFilter;
	}




	public List<WFUsersDBO> getUserList() {
		return userList;
	}




	public void setUserList(List<WFUsersDBO> userList) {
		this.userList = userList;
	}


	
}
