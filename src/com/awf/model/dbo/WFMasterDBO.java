package com.awf.model.dbo;

import java.util.List;

import com.awf.model.orm.dbo.WFMaster;

public class WFMasterDBO extends WFMaster {
	
	private List<WFStepDBO> steps;
	
	private List<WFReferencesDBO> distRefs;
	
	private Boolean showSummary;

	public List<WFStepDBO> getSteps() {
		return steps;
	}

	public void setSteps(List<WFStepDBO> steps) {
		this.steps = steps;
	}

	public Boolean getShowSummary() {
		return showSummary;
	}

	public void setShowSummary(Boolean showSummary) {
		this.showSummary = showSummary;
	}

	public List<WFReferencesDBO> getDistRefs() {
		return distRefs;
	}

	public void setDistRefs(List<WFReferencesDBO> distRefs) {
		this.distRefs = distRefs;
	}



}
