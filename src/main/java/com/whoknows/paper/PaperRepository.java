package com.whoknows.paper;

import com.whoknows.domain.Paper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaperRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createPaper(Paper paper) {
		jdbcTemplate.update("insert into paper(user_id, title, content, action) values (?, ?, ?, ?)",
				ps -> {
					ps.setLong(1, paper.getUser_id());
					ps.setString(2, paper.getTitle());
					ps.setString(3, paper.getContent());
					ps.setString(4, paper.getAction());
				});
	}

	public Paper getPaper(Long id) {
		return jdbcTemplate.query("select * from paper where id = ? limit 1",
				ps -> ps.setLong(1, id),
				(rs, row) -> {
					Paper paper = new Paper();
					paper.setId(rs.getLong("id"));
					paper.setUser_id(rs.getLong("user_id"));
					paper.setAction(rs.getString("action"));
					paper.setTitle(rs.getString("title"));
					try {
						paper.setContent(new String(rs.getBytes("content"), "UTF-8"));
					} catch (Exception e) {
					}
					paper.setRank(rs.getLong("rank"));
					paper.setCreate_time(rs.getTimestamp("create_time"));
					paper.setUpdate_time(rs.getTimestamp("update_time"));
					return paper;
				}).stream().findAny().orElse(null);
	}

	public List<Long> getPaperList(Long user_id, int page, int pageSize) {
		return jdbcTemplate.query("select id from paper where user_id = ? "
				+ "order by rank desc , create_time desc "
				+ "limit ? OFFSET ? ",
				ps -> {
					ps.setLong(1, user_id);
					ps.setInt(2, pageSize);
					ps.setInt(3, (page - 1) * pageSize);
				},
				(rs, row) -> rs.getLong("id"));
	}

	public Integer getPaperCount(Long user_id) {
		return jdbcTemplate.query("select count(1) from paper where user_id = ?  ",
				ps -> {
					ps.setLong(1, user_id);
				},
				(rs, row) -> {
					return rs.getInt("count(1)");
				}).stream().findAny().orElse(null);
	}
}
