package com.whoknows.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void storeToken(Long user_id, String token) {
		jdbcTemplate.update("insert into token (user_id, token) values (? , ? ) ",
				ps -> {
					ps.setLong(1, user_id);
					ps.setString(2, token);
				});
	}

	public boolean checkToken(Long user_id, String token) {
		return jdbcTemplate.query("select id from token where user_id = ? and token = ? ",
				ps -> {
					ps.setLong(1, user_id);
					ps.setString(2, token);
				},
				(rs, row) -> {
					return true;
				}).stream().findAny().orElse(false);
	}
}
