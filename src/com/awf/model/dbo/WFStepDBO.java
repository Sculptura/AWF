package com.awf.model.dbo;

import java.util.List;

import com.awf.model.orm.dbo.WFContent;
import com.awf.model.orm.dbo.WFStep;

public class WFStepDBO extends WFStep {
	

	private List<WFContentDBO> contents;
	private List<WFReferencesDBO> references;
	private String percColor;


	public List<WFReferencesDBO> getReferences() {
		return references;
	}

	public void setReferences(List<WFReferencesDBO> references) {
		this.references = references;
	}

	public String getPercColor() {
		return percColor;
	}

	public void setPercColor(String percColor) {
		this.percColor = percColor;
	}

	public List<WFContentDBO> getContents() {
		return contents;
	}

	public void setContents(List<WFContentDBO> contents) {
		this.contents = contents;
	}

}
