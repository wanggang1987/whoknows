package com.whoknows.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.whoknows.domain.Picture;
import com.whoknows.domain.User;
import com.whoknows.domain.autoMappingBean.UserRowMapper;

@Component
public class AuthDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String QUERY_USER_BY_USER_EMAIL = "SELECT * FROM whoknows.user where email = ?";
	
	public User getUserByEmail(String username) {
		 return jdbcTemplate.query(QUERY_USER_BY_USER_EMAIL,ps -> ps.setString(1, username),
	                new UserRowMapper()).stream().findAny().orElse(null);	
		
	}
	
	
}
