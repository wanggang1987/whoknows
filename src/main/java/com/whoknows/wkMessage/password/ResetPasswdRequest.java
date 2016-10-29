package com.whoknows.wkMessage.password;

public class ResetPasswdRequest {

	private String email;
	private String oldPasswd;
	private String newPasswd;
	private String repeatNewPasswd;
	
	public String getOldPasswd() {
		return oldPasswd;
	}
	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}
	public String getNewPasswd() {
		return newPasswd;
	}
	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}
	public String getRepeatNewPasswd() {
		return repeatNewPasswd;
	}
	public void setRepeatNewPasswd(String repeatNewPasswd) {
		this.repeatNewPasswd = repeatNewPasswd;
	}
	@Override
	public String toString() {
		return "ResetPasswdRequest [userName=" + email + ", oldPasswd="
				+ oldPasswd + ", newPasswd=" + newPasswd + ", repeatNewPasswd="
				+ repeatNewPasswd + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
