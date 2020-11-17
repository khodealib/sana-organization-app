package com.asenadev.sana.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileData {

	@SerializedName("post")
	private String post;

	@SerializedName("role_id")
	private String roleId;

	@SerializedName("permissions")
	private List<String> permissions;

	@SerializedName("referrals")
	private List<String> referrals;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_pic")
	private String profilePic;

	@SerializedName("id")
	private String id;

	@SerializedName("username")
	private String username;

	public String getPost(){
		return post;
	}

	public String getRoleId(){
		return roleId;
	}

	public List<String> getPermissions(){
		return permissions;
	}

	public List<String> getReferrals(){
		return referrals;
	}

	public String getName(){
		return name;
	}

	public Object getProfilePic(){
		return profilePic;
	}

	public String getId(){
		return id;
	}

	public String getUsername(){
		return username;
	}
}