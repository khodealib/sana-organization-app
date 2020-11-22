package com.asenadev.sana.model.profile.get;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProfileResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private ProfileData profileData;

	@SerializedName("messages")
	private List<String> messages;

	@SerializedName("errors")
	private List<String> errors;

	public int getCode(){
		return code;
	}

	public ProfileData getProfileData(){
		return profileData;
	}

	public List<String> getMessages(){
		return messages;
	}

	public List<String> getErrors(){
		return errors;
	}

	@Override
	public String toString() {
		return "GetProfileResponse{" +
				"code=" + code +
				", profileData=" + profileData +
				", messages=" + messages +
				", errors=" + errors +
				'}';
	}
}