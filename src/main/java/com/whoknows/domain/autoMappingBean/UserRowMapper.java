package com.whoknows.domain.autoMappingBean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.whoknows.domain.User;

public class UserRowMapper implements RowMapper<User>{  
	  
    @Override  
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {  
    		User user=new User();
    		user.setId(rs.getLong("id"));
    		user.setEmail(rs.getString("email"));
    		user.setPhone(rs.getString("phone"));
    		user.setPasswd(rs.getString("passwd"));
    		user.setePass(rs.getString("e_pass"));
    		user.setFirstName(rs.getString("first_name"));
    		user.setLastName(rs.getString("last_name"));
    		user.setCompanyName(rs.getString("company_name"));
    		user.setProvince(rs.getString("province"));
    		user.setCity(rs.getString("city"));
    		user.setAddress(rs.getString("address"));
    		user.setCreateTime(rs.getTimestamp("create_time"));
    		user.setUpdateTime(rs.getTimestamp("update_time"));
    		user.setVip(rs.getBoolean("vip"));
    		user.setPicture(rs.getString("picture"));
    		user.setEducation(rs.getString("education"));
    		user.setSignatureId(rs.getLong("signature_id"));
    		user.setTitle(rs.getString("title"));
    		user.setAction(rs.getString("action"));
        return user;  
    }

}  
