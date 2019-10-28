package com.awf.model.dbo;

import java.io.InputStream;

import com.awf.model.orm.dbo.WFReferences;



public class WFReferencesDBO extends WFReferences {

	private InputStream imgStream; 

	public InputStream getImgStream() {
		return imgStream;
	}

	public void setImgStream(InputStream imgStream) {
		this.imgStream = imgStream;
	}
	
	
}	
