package com.awf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.awf.model.dbo.UsersDBO;
import com.awf.model.dbo.WFContentDBO;
import com.awf.model.dbo.WFMasterDBO;
import com.awf.model.dbo.WFReferencesDBO;
import com.awf.model.dbo.WFStepDBO;
import com.awf.model.mapper.WFContentMapper;
import com.awf.model.mapper.WFMasterMapper;
import com.awf.model.mapper.WFProcessMapper;
import com.awf.model.mapper.WFReferencesMapper;
import com.awf.model.mapper.WFStatusMapper;
import com.awf.model.mapper.WFStepMapper;
import com.awf.model.mapper.WFUsersMapper;
import com.awf.model.orm.dbo.WFContent;
import com.awf.model.orm.dbo.WFMaster;
import com.awf.model.orm.dbo.WFProcess;
import com.awf.model.orm.dbo.WFReferences;
import com.awf.model.orm.dbo.WFStatus;
import com.awf.model.orm.dbo.WFStep;
import com.awf.model.orm.dbo.WFUsers;
import com.awf.utilities.SqlConnection;

@ManagedBean(name = "wfmasterMB")
@SessionScoped
public class WorkflowMasterMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8545503613991056209L;

	private List<WFMasterDBO> workflows;
	private WFMasterDBO workflow;
	private int wfRowNum;
	private int stepRowNum;
	private int contentRowNum; 

	@ManagedProperty(value = "#{usersMB}")
	private UsersMB usersMB;

	volatile Logger log = Logger.getLogger(WorkflowMasterMB.class);

	@PostConstruct
	public void initialize() {

		fetchWorkflows();
		fetchAllWFProcess();
		fetchStatusList();
		// fetchActiveUsers();
		// initiateUser();
		initiateWorkflow();

		
		PrimeFaces.current().executeScript("PF('wfdefDlg').hide();");

	}

	private WFUsers currentUser;

	public void initiateUser() {

		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFUsersMapper userMap = session.getMapper(WFUsersMapper.class);
			setCurrentUser(userMap.selectByPrimaryKey(1));

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}

	}

	public void generateStepSequence() {

		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		String lower = upper.toLowerCase(Locale.ROOT);

		String digits = "0123456789";

		String chars = upper + lower + digits;

		Random rand = new Random();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			buf.append(chars.charAt(rand.nextInt(chars.length())));
		}

		workflow.setStepsequence(buf.toString());

	}

	public void fetchWorkflows() {

		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFMasterMapper masterMap = session.getMapper(WFMasterMapper.class);
			setWorkflows(masterMap.fetchWorkflows());

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}
		
		
		FacesContext contextF = FacesContext.getCurrentInstance();

		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:wfdefpanel");
		PrimeFaces.current().ajax().update("centralForm:wfform:wftable");

	}

	private List<WFProcess> processList;

	public void fetchAllWFProcess() {

		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFProcessMapper processMap = session.getMapper(WFProcessMapper.class);
			setProcessList(processMap.selectByExample(null));

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}

	}

	List<WFStatus> statusList;

	public void fetchStatusList() {

		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFStatusMapper statusMap = session.getMapper(WFStatusMapper.class);
			setStatusList(statusMap.selectByExample(null));

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}

	}

	public void initiateWorkflow() {

		workflow = new WFMasterDBO();
		stepSelected = new WFStepDBO();

		workflow.setStartdate(new Date());
		workflow.setEnddate(new Date());
		workflow.setCreateddate(new Date());
		workflow.setCreatedby(usersMB.getCurrentUser().getUsercode().toString());
		workflow.setFlow("S");
		workflow.setStepcount(0);
		workflow.setSteps(new ArrayList<WFStepDBO>());
		generateStepSequence();

		
		PrimeFaces.current().executeScript("PF('wfdefDlg').show();");
		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:wfdefpanel");

	}

	public void validateDates() {

		FacesContext contextF = FacesContext.getCurrentInstance();
		
		UIComponent component = UIComponent.getCurrentComponent(contextF);

		String pageId = component.getId();

		if (pageId.equals("dateF")) {

			if (workflow.getStartdate().after(workflow.getEnddate())) {

				workflow.setEnddate(workflow.getStartdate());
			}

		} else if (pageId.equals("dateT")) {
			if (stepSelected.getEnddate().before(stepSelected.getStartdate())) {

				stepSelected.setStartdate(stepSelected.getEnddate());
			}

		}

		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:wfdefpanel");

		deactivator();

	}

	public boolean beforeStepStatusUpdate() {

		FacesContext contextF = FacesContext.getCurrentInstance();
		

		if (workflow.getFlow().equals("S")) {

			for (int i = 0; i < workflow.getSteps().size(); i++) {

				if (workflow.getSteps().get(i).getSerialno().equals(stepSelected.getSerialno()) && i > 0) {

					if (workflow.getSteps().get(i - 1).getPercentage() < 100) {

						contextF.addMessage(null,
								new FacesMessage("Previous step is incomplete. Can not proceed",
										"Please update the status of step "
												+ workflow.getSteps().get(i - 1).getSerialno() + " - "
												+ workflow.getSteps().get(i - 1).getTitle()));
						PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");

						return false;
					}

				}

			}

		}

		return true;
	}

	public void selectWorkflow(WFMasterDBO wf) {

		workflow = wf;

		populateSteps();
		
		PrimeFaces.current().executeScript("PF('wfdefDlg').show();");
		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:wfdefpanel");

	}

	public void populateSteps() {

		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFStepMapper stepMap = session.getMapper(WFStepMapper.class);
			workflow.setSteps(stepMap.fetchWorkflowSteps(workflow));

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}

		for (WFStepDBO stepRow : workflow.getSteps()) {
			stepRow.setReferences(populateReferences(stepRow));
			stepRow.setContents(populateContents(stepRow));

		}

		
		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:wfdefpanel");
		PrimeFaces.current().ajax().update("centralForm:stepForm:contriPanel");

	}

	private List<WFReferencesDBO> populateReferences(WFStepDBO stepRow) {

		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFReferencesMapper refMap = session.getMapper(WFReferencesMapper.class);
			return refMap.fetchReferences(stepRow.getStepcode(), stepRow.getSerialno());

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}

		return null;
	}

	private List<WFContentDBO> populateContents(WFStepDBO stepRow) {

		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFContentMapper contentsMap = session.getMapper(WFContentMapper.class);
			return contentsMap.fetchContents(stepRow.getStepcode(), stepRow.getSerialno());

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}

		return null;
	}

	private boolean disableStepAdd = false;

	public void selectStep(WFStepDBO step) {
		stepSelected = step;

		FacesContext contextF = FacesContext.getCurrentInstance();
		
		UIComponent component = UIComponent.getCurrentComponent(contextF);
		setStepRowNum(contextF.getApplication().evaluateExpressionGet(contextF, "#{stepRowNum}", Integer.class));

		setDisableStepAdd(true);

		PrimeFaces.current().ajax().update("centralForm:stepForm:steppanel");
		PrimeFaces.current().ajax().update("centralForm:steppopup");
		PrimeFaces.current().executeScript("PF('stepDlg').show();");

	}

	public void updateStep() {

		FacesContext contextF = FacesContext.getCurrentInstance();
		

		if (stepSelected.getMarkerlabel() != null && stepSelected.getMarkerlabel().length() > 0) {

			if (stepSelected.getMarkedby() == null || stepSelected.getMarkedby().length() < 1) {
				contextF.addMessage(null, new FacesMessage("Holder was not specified", ""));
				PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");
				return;

			}

		}

		if (stepSelected.getPercentage() > 0 && stepSelected.getMarkedby() == null) {
			contextF.addMessage(null, new FacesMessage("Holder was not specified", ""));
			PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");
			return;
		}

		if (!beforeStepStatusUpdate()) {

			return;
		}
		;

		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFStepMapper stepMap = session.getMapper(WFStepMapper.class);

			WFStep stepInst = stepMap.selectByPrimaryKey(stepSelected);
			if (stepInst == null) {
				stepMap.insert(stepSelected);
			} else {
				stepMap.updateByPrimaryKeyWithBLOBs(stepSelected);
			}

			session.commit();

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
			contextF.addMessage(null, new FacesMessage("Unexpected Error", ""));
			PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");
			return;

		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}

		// update references
		if (stepSelected.getReferences() != null && !stepSelected.getReferences().isEmpty()) {

			try {
				session = SqlConnection.getInstance().openSession();
				WFReferencesMapper refMap = session.getMapper(WFReferencesMapper.class);

				for (WFReferencesDBO refRow : stepSelected.getReferences()) {

					WFReferences refInst = refMap.selectByPrimaryKey(refRow);
					if (refInst == null) {
						refMap.insertSelective(refRow);
					} else {
						refMap.updateByPrimaryKeySelective(refRow);
					}

				}

				session.commit();

			} catch (IOException | NullPointerException | PersistenceException e) {

				log.info(e.toString());
			} finally {

				try {
					session.close();
				} catch (NullPointerException e) {

					log.info(e.toString());
				}
			}

		}

		// update contents
		if (stepSelected.getContents() != null && !stepSelected.getContents().isEmpty()) {

			try {
				session = SqlConnection.getInstance().openSession();
				WFContentMapper contentMap = session.getMapper(WFContentMapper.class);

				for (WFContentDBO contentRow : stepSelected.getContents()) {

					WFContent contentInst = contentMap.selectByPrimaryKey(contentRow);
					if (contentInst == null) {
						contentMap.insertSelective(contentRow);
					} else {
						contentMap.updateByPrimaryKeySelective(contentRow);
					}

				}

				session.commit();

			} catch (IOException | NullPointerException | PersistenceException e) {

				log.info(e.toString());
			} finally {

				try {
					session.close();
				} catch (NullPointerException e) {

					log.info(e.toString());
				}
			}
		}

		contextF.addMessage(null, new FacesMessage(" step update successful", ""));
		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");

		populateSteps();

	}

	public void selectContent(WFContentDBO content) {
		selectedContent = content;

		
		PrimeFaces.current().executeScript("PF('downloadDlg').show();");

		/*
		 * try { fileAttachmentDownload(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

	private WFContentDBO selectedContent;

	private WFReferencesDBO selectedRef;

	public void initiateReference() {

		selectedRef = new WFReferencesDBO();
		selectedRef.setType("E");
		// selectedRef.setRefheader("Email");
		
		PrimeFaces.current().executeScript("PF('refDlg').show();");
		PrimeFaces.current().ajax().update("centralForm:stepForm:refpanel");
	}

	public void addRefToWFREF() {

		
		FacesContext contextF = FacesContext.getCurrentInstance();
		if (selectedRef.getRefheader() == null || selectedRef.getRefheader().isEmpty()) {
			contextF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name was not specified", ""));
			PrimeFaces.current().ajax().update("centralForm:stepForm:payrollStat");
			return;
		}
		
		if (selectedRef.getReffield()== null || selectedRef.getReffield().isEmpty()) {
			contextF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Value was not specified", ""));
			PrimeFaces.current().ajax().update("centralForm:stepForm:payrollStat");
			return;
		}


		if (selectedRef.getType() != null && selectedRef.getType().equals("E")) {

			boolean result = true;
			try {
				InternetAddress emailAddr = new InternetAddress(selectedRef.getReffield());
				emailAddr.validate();
			} catch (AddressException ex) {
				result = false;
			}

			if (!result) {
				System.out.println("Email invalid");

				contextF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email was invalid",
						"Please define a valid Email"));
				PrimeFaces.current().ajax().update("centralForm:stepForm:payrollStat");
				return;
			}

		}

		else if (selectedRef.getType() != null && selectedRef.getType().equals("C")) {

			String number = selectedRef.getReffield().toString();
			Pattern pattern = Pattern.compile("^[0-9]{10,15}$");
			Matcher matcher = pattern.matcher(number);
			if (!matcher.matches()) {
				System.out.println("Phone Number invalid");

				contextF.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contact was invalid",
						"Please define a valid Contact"));
				PrimeFaces.current().ajax().update("centralForm:stepForm:payrollStat");
				return;
			}
		}

		selectedRef.setType(selectedRef.getType() == null ? "O" : selectedRef.getType());

		selectedRef.setStepcode(stepSelected.getStepcode());
		selectedRef.setSerialno(stepSelected.getSerialno());

		selectedRef.setAddedby(usersMB.getCurrentUser().getUsercode().toString());
		selectedRef.setAddedon(new Date());

		stepSelected.getReferences().add(selectedRef);
		PrimeFaces.current().executeScript("PF('refDlg').hide();");
		PrimeFaces.current().ajax().update("centralForm:stepForm:steppanel");

	}

	private StreamedContent file;

	public void fileAttachmentDownload() throws IOException {

		
		PrimeFaces.current().executeScript("PF('downloadDlg').hide();");

		if (selectedContent != null) {

			String filepath = selectedContent.getFilepath();
			File aFile = new File(filepath);
			InputStream stream = new FileInputStream(aFile);

			file = new DefaultStreamedContent(stream, selectedContent.getType(), selectedContent.getFilename());

			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();

			ec.responseReset();
			String ctype = selectedContent.getType();
			// ctype = ctype.substring(0, ctype.indexOf(";"));

			ec.setResponseContentType(ctype);
			ec.setResponseContentType(ec.getMimeType(aFile.getName()));
			// ec.setResponseContentLength(contentLength);
			ec.setResponseHeader("Content-Disposition", "attachment;filename=" + file.getName());

			OutputStream output = ec.getResponseOutputStream();

			InputStream fileInputStream = new FileInputStream(aFile);

			byte[] bytesBuffer = new byte[2048];
			int bytesRead;
			while ((bytesRead = fileInputStream.read(bytesBuffer)) > 0) {
				output.write(bytesBuffer, 0, bytesRead);
			}

			output.flush();

			fileInputStream.close();
			output.close();

			stream.close();

			fc.responseComplete();

		}

	}

	private WFStepDBO stepSelected;

	public void initiateStep() {
		FacesContext contextF = FacesContext.getCurrentInstance();
		

		workflow.setSteps(workflow.getSteps() == null ? new ArrayList<WFStepDBO>() : workflow.getSteps());
		if (workflow.getStepcount().compareTo(workflow.getSteps().size() + 1) < 0) {

			contextF.addMessage(null, new FacesMessage("limit exceeded", ""));
			PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");
			return;
		}

		stepSelected = new WFStepDBO();

		stepSelected.setStartdate(new Date());
		stepSelected.setStepcode(workflow.getStepsequence());
		stepSelected.setSerialno(workflow.getSteps().size() + 1);

		if (workflow.getSteps() != null && workflow.getSteps().size() > 0) {

			stepSelected.setStartdate(workflow.getSteps().get(workflow.getSteps().size() - 1).getStartdate());
			stepSelected.setEnddate(workflow.getSteps().get(workflow.getSteps().size() - 1).getStartdate());
		} else {
			workflow.setSteps(new ArrayList<WFStepDBO>());
			stepSelected.setStartdate(workflow.getStartdate());
			stepSelected.setEnddate(workflow.getStartdate());

		}

		setDisableStepAdd(false);

		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:stepGrid");
		PrimeFaces.current().ajax().update("centralForm:stepForm:steppanel");
		PrimeFaces.current().executeScript("PF('stepDlg').show();");

	}

	public void insertStepToWorkflow() {

		workflow.getSteps().add(stepSelected);

		
		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:stepGrid");

		PrimeFaces.current().executeScript("PF('stepDlg').hide();");

	}

	public void beforeSaveWorflow() {

		FacesContext contextF = FacesContext.getCurrentInstance();
		

		if (workflow.getTitle() == null || workflow.getTitle().length() < 2) {

			contextF.addMessage(null, new FacesMessage(" Title not specified", ""));
			PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");
			return;
		}

		if (workflow.getAppliesto() == null) {

			contextF.addMessage(null, new FacesMessage(" Applies To was not set", ""));
			PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");
			return;
		}

		if (workflow.getStepcount() > 0 && workflow.getSteps().isEmpty()) {
			contextF.addMessage(null, new FacesMessage(" No steps defined", ""));
			PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");
			return;
		}

		for (int i = 0; i < workflow.getSteps().size(); i++) {

			int rowIndex = i + 1;

			if (workflow.getSteps().get(i).getTitle().length() < 2) {
				contextF.addMessage(null, new FacesMessage(" Title not specified", "at row" + rowIndex));
				PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");
				return;
			}

		}

		saveWorkflow();
		fetchWorkflows();

	}

	private Boolean newBtn;
	private Boolean saveBtn;

	public void deactivator() {
		
		setSaveBtn(false);
		setNewBtn(false);
		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:wfdefpanel");
	}

	public void saveWorkflow() {

		
		FacesContext contextF = FacesContext.getCurrentInstance();
		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFMasterMapper masterMap = session.getMapper(WFMasterMapper.class);
			WFMaster wmasterInst = masterMap.selectByPrimaryKey(workflow.getStepsequence());

			workflow.setStepcount(workflow.getSteps().size());
			calcWorkflowPerc();

			if (wmasterInst == null) {

				/*
				 * Integer maxDocNum = masterMap.getMaxDocNumber(); if (maxDocNum == null) {
				 * maxDocNum = 00; } maxDocNum++; String formatted = String.format("%013d",
				 * maxDocNum); workflow.setWfcode("WF" + formatted);
				 */

				masterMap.insert(workflow);

			} else {

				masterMap.updateByPrimaryKeyWithBLOBs(workflow);
			}

			if (workflow.getSteps() != null && !workflow.getSteps().isEmpty()) {

				WFStepMapper stepMap = session.getMapper(WFStepMapper.class);

				for (WFStepDBO stepRow : workflow.getSteps()) {

					WFStep stepInst = stepMap.selectByPrimaryKey(stepRow);
					if (stepInst == null) {
						stepMap.insert(stepRow);
					} else {
						stepMap.updateByPrimaryKeyWithBLOBs(stepRow);
					}

				}
			}

			session.commit();

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
			contextF.addMessage(null, new FacesMessage("Unexpected Error", ""));
			PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");
			return;

		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}

		contextF.addMessage(null, new FacesMessage(" saved successfully", ""));
		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");

		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:wfdefpanel");
		PrimeFaces.current().ajax().update("centralForm:wfform:wftable");

	}

	private void calcWorkflowPerc() {
		if (workflow.getSteps() == null || workflow.getSteps().isEmpty()) {
			return;
		}

		Integer totperc = 0;
		Integer totR = 0;

		for (WFStepDBO stepRow : workflow.getSteps()) {
			totperc = totperc + (stepRow.getPercentage() == null ? 0 : stepRow.getPercentage());
			totR = totR + (stepRow.getRating() == null ? 0 : stepRow.getRating());

		}

		// Integer totalSteps = workflow.getSteps().size() + 1;
		workflow.setPercentage(totperc / workflow.getSteps().size());
		workflow.setRating(totR / workflow.getSteps().size());

	}

	public void deleteWorkflow() {
		
		FacesContext contextF = FacesContext.getCurrentInstance();
		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFMasterMapper masterMap = session.getMapper(WFMasterMapper.class);
			masterMap.deleteByPrimaryKey(workflow.getStepsequence());

		} catch (IOException | NullPointerException | PersistenceException e) {

			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}
		
		initiateWorkflow();
		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:wfdefpanel");
		PrimeFaces.current().ajax().update("centralForm:wfform:wftable");

	}

	private List<UsersDBO> users;
	private UsersDBO user;

	public void prepUsers() {

		FacesContext contextF = FacesContext.getCurrentInstance();
		
		UIComponent component = UIComponent.getCurrentComponent(contextF);

		setUserSelectSrc(component.getId());
		usersMB.fetchActiveUsers();

		PrimeFaces.current().executeScript("PF('userSearchDlg').show();");
		PrimeFaces.current().ajax().update("centralForm:userSearchForm:userTable");

	}

	private WFContentDBO currentContent;
	InputStream docStream;

	public void addDocToContents() {

		FacesContext contextF = FacesContext.getCurrentInstance();
		

		if (currentContent.getFilename() == null || currentContent.getFilename() == null) {
			// message and return
			contextF.addMessage(null, new FacesMessage("File not found", "no file was uploaded"));
			PrimeFaces.current().ajax().update("centralForm:payrollStat");
			return;

		}

		String userHomeDirectory = System.getProperty("user.home");
		currentContent.setFilepath(userHomeDirectory + File.separator + "AWF" + File.separator
				+ currentUser.getUsername() + File.separator + "Documents");

		File destFile = new File(currentContent.getFilepath());
		try {
			FileUtils.forceMkdir(destFile);

			Path folder = Paths.get(currentContent.getFilepath());
			String filename = FilenameUtils.getBaseName(currentContent.getFilename());
			String extension = FilenameUtils.getExtension(currentContent.getFilename());
			Path file = Files.createTempFile(folder, filename + "-", "." + extension);

			Files.copy(docStream, file, StandardCopyOption.REPLACE_EXISTING);

			// Last minute props
			currentContent.setFilepath(file.toString());
			currentContent.setType("FI");

			currentContent.setStepcode(stepSelected.getStepcode());
			currentContent.setSerialno(stepSelected.getSerialno());

			currentContent.setAddedby(currentUser.getUsercode().toString());
			currentContent.setAddedon(new Date());

			stepSelected.setContents(
					stepSelected.getContents() == null ? new ArrayList<WFContentDBO>() : stepSelected.getContents());

			stepSelected.getContents().add(currentContent);

			PrimeFaces.current().ajax().update("centralForm:stepForm:steppanel");
			PrimeFaces.current().executeScript("PF('contentDlg').hide();");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void selectUser(SelectEvent event) {

		user = (UsersDBO) event.getObject();

	}

	private String userSelectSrc;

	public void addUsersToStep() {
		
		if (userSelectSrc.equals("holderBtn")) {

			stepSelected.setMarkedby(user.getUsername());
			PrimeFaces.current().ajax().update("centralForm:stepForm:holder");
			return;
		}

		if (user == null) {

			return;
		}

		WFReferencesDBO refInst = new WFReferencesDBO();
		refInst.setRefcode(user.getUsercode().toString());
		refInst.setRefname(user.getUsername());
		refInst.setStepcode(stepSelected.getStepcode());
		refInst.setSerialno(stepSelected.getSerialno());
		refInst.setType("U");

		stepSelected.setReferences(
				stepSelected.getReferences() == null ? new ArrayList<WFReferencesDBO>() : stepSelected.getReferences());

		boolean dupfound = false;
		for (WFReferencesDBO refRow : stepSelected.getReferences()) {
			if (refRow.getStepcode().equals(refInst.getStepcode())
					&& refRow.getRefcode().equals(refInst.getRefcode())) {

				refRow = refInst;
				dupfound = true;
				break;
			}
		}

		if (!dupfound) {
			stepSelected.getReferences().add(refInst);
		}

		PrimeFaces.current().ajax().update("centralForm:stepForm:steppanel");

	}

	StreamedContent imgStr;

	public void handleFileUpload(FileUploadEvent eventUp) throws IOException {

		FacesContext contextF = FacesContext.getCurrentInstance();
		

		docStream = eventUp.getFile().getInputstream();
		currentContent = new WFContentDBO();

		currentContent.setFilename(eventUp.getFile().getFileName());
		currentContent.setFileextension(eventUp.getFile().getContentType());

		Double m = ((eventUp.getFile().getSize() / 1024.0) / 1024.0);

		DecimalFormat df = new DecimalFormat("#.###");
		m = Double.valueOf(df.format(m));

		currentContent.setFilesize(m + " MB");

		// setUploadSaveBtn(false);
		contextF.addMessage(null, new FacesMessage("Upload Complete", "ready to save file"));
		PrimeFaces.current().ajax().update("centralForm:wfmasterForm:payrollStat");

		PrimeFaces.current().ajax().update("centralForm:stepForm:uploadDetails");
		// PrimeFaces.current().ajax().update("centralForm:upSaveBtn");

	}

	// summary

	public void workflowSummary(WFMasterDBO work) {
		
		FacesContext contextF = FacesContext.getCurrentInstance();
		UIComponent component = UIComponent.getCurrentComponent(contextF);

		setWfRowNum(contextF.getApplication().evaluateExpressionGet(contextF, "#{wfRowNum}", Integer.class));
		work.setShowSummary(true);

		// cont
		SqlSession session = null;
		try {
			session = SqlConnection.getInstance().openSession();
			WFReferencesMapper refMap = session.getMapper(WFReferencesMapper.class);
			work.setDistRefs(refMap.findDistReferences(work.getStepsequence()));

		} catch (IOException | NullPointerException | PersistenceException e) {
			log.info(e.toString());
		} finally {

			try {
				session.close();
			} catch (NullPointerException e) {

				log.info(e.toString());
			}
		}
						
		PrimeFaces.current().ajax().update("centralForm:wfform:wftable:" + wfRowNum + ":summaryGrid");

	}

	public List<WFMasterDBO> getWorkflows() {
		return workflows;
	}

	public void setWorkflows(List<WFMasterDBO> workflows) {
		this.workflows = workflows;
	}

	public WFMasterDBO getWorkflow() {
		return workflow;
	}

	public void setWorkflow(WFMasterDBO workflow) {
		this.workflow = workflow;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public StreamedContent getImgStr() {
		return imgStr;
	}

	public void setImgStr(StreamedContent imgStr) {
		this.imgStr = imgStr;
	}

	public Boolean getNewBtn() {
		return newBtn;
	}

	public void setNewBtn(Boolean newBtn) {
		this.newBtn = newBtn;
	}

	public Boolean getSaveBtn() {
		return saveBtn;
	}

	public void setSaveBtn(Boolean saveBtn) {
		this.saveBtn = saveBtn;
	}

	public List<WFProcess> getProcessList() {
		return processList;
	}

	public void setProcessList(List<WFProcess> processList) {
		this.processList = processList;
	}

	public List<WFStatus> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<WFStatus> statusList) {
		this.statusList = statusList;
	}

	public WFStepDBO getStepSelected() {
		return stepSelected;
	}

	public void setStepSelected(WFStepDBO stepSelected) {
		this.stepSelected = stepSelected;
	}

	public WFUsers getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(WFUsers currentUser) {
		this.currentUser = currentUser;
	}

	public int getWfRowNum() {
		return wfRowNum;
	}

	public void setWfRowNum(int wfRowNum) {
		this.wfRowNum = wfRowNum;
	}

	public int getStepRowNum() {
		return stepRowNum;
	}

	public void setStepRowNum(int stepRowNum) {
		this.stepRowNum = stepRowNum;
	}

	public int getContentRowNum() {
		return contentRowNum;
	}

	public void setContentRowNum(int contentRowNum) {
		this.contentRowNum = contentRowNum;
	}

	public boolean isDisableStepAdd() {
		return disableStepAdd;
	}

	public void setDisableStepAdd(boolean disableStepAdd) {
		this.disableStepAdd = disableStepAdd;
	}

	public List<UsersDBO> getUsers() {
		return users;
	}

	public void setUsers(List<UsersDBO> users) {
		this.users = users;
	}

	public UsersDBO getUser() {
		return user;
	}

	public void setUser(UsersDBO user) {
		this.user = user;
	}

	public String getUserSelectSrc() {
		return userSelectSrc;
	}

	public void setUserSelectSrc(String userSelectSrc) {
		this.userSelectSrc = userSelectSrc;
	}

	public WFContentDBO getCurrentContent() {
		return currentContent;
	}

	public void setCurrentContent(WFContentDBO currentContent) {
		this.currentContent = currentContent;
	}

	public InputStream getDocStream() {
		return docStream;
	}

	public void setDocStream(InputStream docStream) {
		this.docStream = docStream;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public WFContentDBO getSelectedContent() {
		return selectedContent;
	}

	public void setSelectedContent(WFContentDBO selectedContent) {
		this.selectedContent = selectedContent;
	}

	public UsersMB getUsersMB() {
		return usersMB;
	}

	public void setUsersMB(UsersMB usersMB) {
		this.usersMB = usersMB;
	}

	public WFReferencesDBO getSelectedRef() {
		return selectedRef;
	}

	public void setSelectedRef(WFReferencesDBO selectedRef) {
		this.selectedRef = selectedRef;
	}

}
