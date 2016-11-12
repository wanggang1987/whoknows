package com.whoknows.domain;

import java.sql.Timestamp;

public class Vip {

	private Long id;
	private Long user_id;
	private String personal_profile;
	private Timestamp create_time;
	private Timestamp update_time;
	private Long rank;
	private String action;

	public String getPersonal_profile() {
		return personal_profile;
	}

	public void setPersonal_profile(String personal_profile) {
		this.personal_profile = personal_profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
