package com.whoknows.vip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VipRespository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean isVip(String email) {
		return jdbcTemplate.query("select vip.id from vip "
				+ "left join user on vip.user_id = vip.id "
				+ "where user.email = ? ",
				ps -> {
					ps.setString(1, email);
				}, (rs, row) -> {
					return true;
				}).stream().findAny().orElse(false);
	}

	public void setVip() {
		
	}
}
