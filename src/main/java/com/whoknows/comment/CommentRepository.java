package com.whoknows.comment;

import com.whoknows.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createComment(Comment comment) {
		jdbcTemplate.update("insert into comment ( user_id, reply_id, content, action ) values ( ?, ?, ?, ? ) ",
				ps -> {
					ps.setLong(1, comment.getUser_id());
					ps.setLong(2, comment.getReply_id());
					ps.setString(3, comment.getContent());
					ps.setString(4, comment.getAction());
				});
	}

	public void updateComment(Comment comment) {
		jdbcTemplate.update("update comment set content = ? , action = ? where id = ? ",
				ps -> {
					ps.setString(1, comment.getContent());
					ps.setString(2, comment.getAction());
					ps.setLong(3, comment.getId());
				});
	}

	public void deleteComment(Long id) {
		jdbcTemplate.update("delete from comment where id = ?", ps -> ps.setLong(1, id));
	}

	public Comment getComment(Long id) {
		return jdbcTemplate.query("select * from comment where id = ?  limit 1 ",
				ps -> {
					ps.setLong(1, id);
				}, (rs, row) -> {
					Comment comment = new Comment();
					comment.setId(rs.getLong("id"));
					comment.setUser_id(rs.getLong("user_id"));
					comment.setReply_id(rs.getLong("reply_id"));
					comment.setContent(rs.getString("content"));
					comment.setCreate_time(rs.getTimestamp("create_time"));
					comment.setUpdate_time(rs.getTimestamp("update_time"));
					return comment;
				}).stream().findAny().orElse(null);
	}
}
