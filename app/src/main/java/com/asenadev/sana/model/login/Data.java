package com.asenadev.sana.model.login;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("permissions")
	private List<String> permissions;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_pic")
	private String profilePic;

	@Override
	public String toString() {
		return "Data{" +
				"accessToken='" + accessToken + '\'' +
				", permissions=" + permissions +
				", name='" + name + '\'' +
				", profilePic='" + profilePic + '\'' +
				", id='" + id + '\'' +
				", tokenType='" + tokenType + '\'' +
				", expiresIn=" + expiresIn +
				'}';
	}

	@SerializedName("id")
	private String id;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("expires_in")
	private int expiresIn;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setPermissions(List<String> permissions){
		this.permissions = permissions;
	}

	public List<String> getPermissions(){
		return permissions;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setProfilePic(String profilePic){
		this.profilePic = profilePic;
	}

	public String getProfilePic(){
		return profilePic;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}

	public String getTokenType(){
		return tokenType;
	}

	public void setExpiresIn(int expiresIn){
		this.expiresIn = expiresIn;
	}

	public int getExpiresIn(){
		return expiresIn;
	}
}