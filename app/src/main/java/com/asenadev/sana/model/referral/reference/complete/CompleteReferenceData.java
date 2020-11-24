package com.asenadev.sana.model.referral.reference.complete;

import com.google.gson.annotations.SerializedName;

public class CompleteReferenceData {

	@SerializedName("reference_id")
	private String referenceId;

	public String getReferenceId(){
		return referenceId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"reference_id = '" + referenceId + '\'' + 
			"}";
		}
}