package com.whoknows.bi;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BiRepository {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<BiEntry> countComment() {
		return jdbcTemplate.query("select reply_id from comment ",
				(rs, row) -> new BiEntry(null, rs.getLong("reply_id"), null));
	}

	public List<BiEntry> countReply() {
		return jdbcTemplate.query("select topic_id from reply ",
				(rs, row) -> new BiEntry(null, rs.getLong("topic_id"), null));
	}

	public List<BiEntry> countTopic() {
		return jdbcTemplate.query("select user_id from topic ",
				(rs, row) -> new BiEntry(null, rs.getLong("user_id"), null));
	}

	public List<BiEntry> countPaper() {
		return jdbcTemplate.query("select user_id from paper ",
				(rs, row) -> new BiEntry(null, rs.getLong("user_id"), null));
	}

	public List<BiEntry> countLike() {
		return jdbcTemplate.query("select target_id, target_type from `like`",
				(rs, row) -> new BiEntry(null, rs.getLong("target_id"), rs.getString("target_type")));
	}

	public List<BiEntry> countFollow() {
		return jdbcTemplate.query("select target_id, target_type from follow",
				(rs, row) -> new BiEntry(null, rs.getLong("target_id"), rs.getString("target_type")));
	}

	public void flushUserRank(Map<Long, BiEntry> map) {
		final List<BiEntry> list = map.values().stream().collect(Collectors.toList());
		jdbcTemplate.batchUpdate("update user set rank =? where id = ?  ", new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BiEntry biEntry = list.get(i);
				ps.setLong(1, biEntry.getCount());
				ps.setLong(2, biEntry.getTarget_id());
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
	}

	public void flushTagRank(Map<Long, BiEntry> map) {
		final List<BiEntry> list = map.values().stream().collect(Collectors.toList());
		jdbcTemplate.batchUpdate("update tag set rank =? where id = ?  ", new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BiEntry biEntry = list.get(i);
				ps.setLong(1, biEntry.getCount());
				ps.setLong(2, biEntry.getTarget_id());
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
	}

	public void flushPaperRank(Map<Long, BiEntry> map) {
		final List<BiEntry> list = map.values().stream().collect(Collectors.toList());
		jdbcTemplate.batchUpdate("update paper set rank =? where id = ?  ", new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BiEntry biEntry = list.get(i);
				ps.setLong(1, biEntry.getCount());
				ps.setLong(2, biEntry.getTarget_id());
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
	}

	public void flushTopicRank(Map<Long, BiEntry> map) {
		final List<BiEntry> list = map.values().stream().collect(Collectors.toList());
		jdbcTemplate.batchUpdate("update topic set rank =? where id = ?  ", new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BiEntry biEntry = list.get(i);
				ps.setLong(1, biEntry.getCount());
				ps.setLong(2, biEntry.getTarget_id());
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
	}

	public void flushReplyRank(Map<Long, BiEntry> map) {
		final List<BiEntry> list = map.values().stream().collect(Collectors.toList());
		jdbcTemplate.batchUpdate("update reply set rank =? where id = ?  ", new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				BiEntry biEntry = list.get(i);
				ps.setLong(1, biEntry.getCount());
				ps.setLong(2, biEntry.getTarget_id());
			}

			@Override
			public int getBatchSize() {
				return list.size();
			}
		});
	}
}
