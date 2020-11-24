package com.asenadev.sana.model.referral.history;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data{

	@SerializedName("referrals")
	private List<ReferralsItem> referrals;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	public List<ReferralsItem> getReferrals(){
		return referrals;
	}

	public Meta getMeta(){
		return meta;
	}

	public Links getLinks(){
		return links;
	}
}