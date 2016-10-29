package com.whoknows.reply;

import com.whoknows.domain.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyRepository {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createReply(Reply reply) {
		jdbcTemplate.update("insert into reply (user_id, topic_id, content, action, reply_id) values (?, ?, ?, ?, ?)",
				ps -> {
					ps.setLong(1, reply.getUser_id());
					ps.setLong(2, reply.getTopic_id());
					ps.setString(3, reply.getContent());
					ps.setString(4, reply.getAction());
					ps.setLong(5, reply.getReply_id());
				});
	}

	public void updateReply(Reply reply) {
		jdbcTemplate.update("update reply set content = ? , action = ? where id = ?",
				ps -> {
					ps.setString(1, reply.getContent());
					ps.setString(2, reply.getAction());
					ps.setLong(3, reply.getId());
				});
	}

	public void deleteReply(Long id) {
		jdbcTemplate.update("delete from reply where id =? ", ps -> ps.setLong(1, id));
	}

	public Reply getReply(Long id) {
		return jdbcTemplate.query("select * from reply where id = ? limit 1",
				ps -> ps.setLong(1, id),
				(rs, row) -> {
					Reply reply = new Reply();
					reply.setId(rs.getLong("id"));
					reply.setAction(rs.getString("action"));
					reply.setContent(rs.getString("content"));
					reply.setCreate_time(rs.getTimestamp("create_time"));
					reply.setReply_id(rs.getLong("reply_id"));
					reply.setTopic_id(rs.getLong("topic_id"));
					reply.setUpdate_time(rs.getTimestamp("update_time"));
					reply.setUser_id(rs.getLong("user_id"));
					return reply;
				}).stream().findAny().orElse(null);
	}
}
