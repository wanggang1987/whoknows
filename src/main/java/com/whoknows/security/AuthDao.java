package com.whoknows.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.whoknows.domain.Role;
import com.whoknows.domain.RoleType;
import com.whoknows.domain.User;

@Component
public class AuthDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String QUERY_USER_BY_USER_EMAIL = "SELECT * FROM user where email = ?";
	private final String QUERY_ROLES_BY_USER_ID = "select * from role left join user_role on role.id = user_role.role_id where user_id = ?";

	public User getUserByEmail(String username) {
		return jdbcTemplate.query(QUERY_USER_BY_USER_EMAIL,
				ps -> ps.setString(1, username),
				(rs, row) -> {
					User user = new User();
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
					user.setPicture(rs.getString("picture"));
					user.setEducation(rs.getString("education"));
					user.setSignature(rs.getString("signature"));
					user.setTitle(rs.getString("title"));
					user.setAction(rs.getString("action"));
					user.setRank(rs.getInt("rank"));
					user.setProfile(rs.getString("profile"));
					return user;
				}).stream().findAny().orElse(null);

	}

	public List<Role> getRolesByUserId(Long id) {
		List<Role> roles = new ArrayList<>();
		jdbcTemplate.query(QUERY_ROLES_BY_USER_ID, ps -> ps.setLong(1, id), (rs, row) -> {
			Role role = new Role();
			role.setRole(rs.getString("role"));
			roles.add(role);
			return null;
		});
		if (roles.size() == 0) {
			Role role = new Role();
			role.setRole(RoleType.SITE_USER.toString());
			roles.add(role);
		}
		return roles;
	}

}
