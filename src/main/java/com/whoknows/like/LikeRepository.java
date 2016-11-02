package com.whoknows.like;

import com.whoknows.domain.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LikeRepository {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean isLike(Long userId, Long tartgetId, TargetType type) {
		return jdbcTemplate.query("select id from `like` where user_id = ? and target_type = ? and target_id = ? ",
				ps -> {
					ps.setLong(1, userId);
					ps.setString(2, type.name());
					ps.setLong(3, tartgetId);
				},
				(rs, row) -> {
					return true;
				}).stream().findAny().orElse(false);
	}

	public boolean notLike(Long userId, Long tartgetId, TargetType type) {
		return isLike(userId, tartgetId, type) ? false : true;
	}

	public void like(Long userId, Long tartgetId, TargetType type) {
		if (notLike(userId, tartgetId, type)) {
			jdbcTemplate.update("insert into `like` (user_id, target_type, target_id) values (?, ?, ?) ",
					ps -> {
						ps.setLong(1, userId);
						ps.setString(2, type.name());
						ps.setLong(3, tartgetId);
					});
		}
	}
}
