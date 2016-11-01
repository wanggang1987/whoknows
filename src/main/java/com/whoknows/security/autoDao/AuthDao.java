package com.whoknows.security.autoDao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.whoknows.domain.User;
import com.whoknows.domain.autoMappingBean.UserRowMapper;

@Component
public class AuthDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String QUERY_USER_BY_USER_EMAIL = "SELECT * FROM user where email = ?";
	private final String QUERY_ROLES_BY_USER_ID = "select role.role from role left join user_role on role.id = user_role.role_id where user_id = ?";
	public User getUserByEmail(String username) {
		return jdbcTemplate.query(QUERY_USER_BY_USER_EMAIL, ps -> ps.setString(1, username),
				new UserRowMapper()).stream().findAny().orElse(null);

	}

	public List<String> getRolesByUserId(Long id) {
		List<String> roles = jdbcTemplate.queryForList(QUERY_ROLES_BY_USER_ID,  new Object[]{id}, String.class);
		if(roles.size() == 0){
			roles = Arrays.asList(new String[]{"SITE_USER"});
		}
		return roles;
	}

}
