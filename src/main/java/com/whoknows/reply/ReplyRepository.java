package com.whoknows.reply;

import com.whoknows.domain.Reply;
import java.util.List;
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

	public List<Long> getTopicReplys(Long topicId, int page, int pageSize) {
		return jdbcTemplate.query("select id from reply where topic_id = ? "
				+ "order by id desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setLong(1, topicId);
					ps.setInt(2, pageSize);
					ps.setInt(3, (page - 1) * pageSize);
				},
				(rs, row) -> {
					return rs.getLong("id");
				});
	}

	public Reply getHotReplyForRopic(Long topicId) {
		return jdbcTemplate.query("select * from reply where topic_id = ? order by rank desc limit 1",
				ps -> ps.setLong(1, topicId),
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

	public void createReply(Reply reply) {
		if (reply.getReply_id() == null) {
			jdbcTemplate.update("insert into reply (user_id, topic_id, content, action) values (?, ?, ?, ?)",
					ps -> {
						ps.setLong(1, reply.getUser_id());
						ps.setLong(2, reply.getTopic_id());
						ps.setString(3, reply.getContent());
						ps.setString(4, reply.getAction());
					});
		} else {
			jdbcTemplate.update("insert into reply (user_id, topic_id, content, action, reply_id) values (?, ?, ?, ?, ?)",
					ps -> {
						ps.setLong(1, reply.getUser_id());
						ps.setLong(2, reply.getTopic_id());
						ps.setString(3, reply.getContent());
						ps.setString(4, reply.getAction());
						ps.setLong(5, reply.getReply_id());
					});
		}
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
