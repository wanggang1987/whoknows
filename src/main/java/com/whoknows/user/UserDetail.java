package com.whoknows.user;

import com.whoknows.domain.Role;
import com.whoknows.domain.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetail {

	private Long id;
	private String email;
	private String phone;
	private String firstName;
	private String lastName;
	private String companyName;
	private String province;
	private String city;
	private String address;
	private Timestamp createTime;
	private String picture;
	private String education;
	private String signature;
	private String title;
	private Integer rank;
	private String profile;
	private String name;
	private Timestamp loginime;
	private List<String> roles;

	public UserDetail() {

	}

	public UserDetail(User user, List<Role> roles) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.companyName = user.getCompanyName();
		this.province = user.getProvince();
		this.city = user.getCity();
		this.address = user.getAddress();
		this.createTime = user.getCreateTime();
		this.picture = user.getPicture();
		this.education = user.getEducation();
		this.signature = user.getSignature();
		this.title = user.getTitle();
		this.rank = user.getRank();
		this.profile = user.getProfile();
		this.loginime = user.getLogin_time();
		this.roles = roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
	}

	public Timestamp getLoginime() {
		return loginime;
	}

	public void setLoginime(Timestamp loginime) {
		this.loginime = loginime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setUser(User user, List<Role> roles) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.companyName = user.getCompanyName();
		this.province = user.getProvince();
		this.city = user.getCity();
		this.address = user.getAddress();
		this.createTime = user.getCreateTime();
		this.picture = user.getPicture();
		this.education = user.getEducation();
		this.signature = user.getSignature();
		this.title = user.getTitle();
		this.rank = user.getRank();
		this.profile = user.getProfile();
		this.roles = roles.stream().map(role -> role.getRole()).collect(Collectors.toList());
	}

}
