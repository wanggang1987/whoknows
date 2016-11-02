package com.whoknows.follow;

import com.whoknows.domain.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FollowRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean isFollow(Long userId, Long tartgetId, TargetType type) {
		return jdbcTemplate.query("select id from follow where user_id = ? and target_type = ? and target_id = ? ",
				ps -> {
					ps.setLong(1, userId);
					ps.setString(2, type.name());
					ps.setLong(3, tartgetId);
				},
				(rs, row) -> {
					return true;
				}).stream().findAny().orElse(false);
	}

	public boolean notFollow(Long userId, Long tartgetId, TargetType type) {
		return isFollow(userId, tartgetId, type) ? false : true;
	}

	public void follow(Long userId, Long tartgetId, TargetType type) {
		if (notFollow(userId, tartgetId, type)) {
			jdbcTemplate.update("insert into follow (user_id, target_type, target_id) values (?, ?, ?) ",
					ps -> {
						ps.setLong(1, userId);
						ps.setString(2, type.name());
						ps.setLong(3, tartgetId);
					});
		}
	}
}

