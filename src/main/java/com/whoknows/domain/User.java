package com.whoknows.domain;

import java.sql.Timestamp;

public class User {

    private Long id;
    private String email;
    private String phone;
    private String passwd;
    private String ePass;
    private String firstName;
    private String lastName;
    private String companyName;
    private String province;
    private String city;
    private String address;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Boolean vip;
    private String picture;
    private String education;
    private Long signatureId;
    private Long roleId;
    private String title;
    private String action;

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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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



    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", phone=" + phone
				+ ", passwd=" + passwd + ", ePass=" + ePass + ", firstName="
				+ firstName + ", lastName=" + lastName + ", companyName="
				+ companyName + ", province=" + province + ", city=" + city
				+ ", address=" + address + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", vip=" + vip + ", picture="
				+ picture + ", education=" + education + ", signatureId="
				+ signatureId + ", roleId=" + roleId + ", title=" + title
				+ ", action=" + action + "]";
	}

	public String getePass() {
		return ePass;
	}

	public void setePass(String ePass) {
		this.ePass = ePass;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Long getSignatureId() {
		return signatureId;
	}

	public void setSignatureId(Long signatureId) {
		this.signatureId = signatureId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
