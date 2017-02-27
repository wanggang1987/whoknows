package com.whoknows.picture;

import com.whoknows.domain.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PictureRepository {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Picture getPicture(Long id) {
		return jdbcTemplate.query(
				"select * from picture where id = ? limit 1",
				ps -> ps.setLong(1, id),
				(rs, row) -> {
					Picture picture = new Picture();
					picture.setId(rs.getLong("id"));
					picture.setName(rs.getString("name"));
					picture.setHeight(rs.getInt("height"));
					picture.setWidth(rs.getInt("width"));
					picture.setCreate_time(rs.getTimestamp("create_time"));
					picture.setStream(rs.getBytes("stream"));
					return picture;
				}).stream().findAny().orElse(null);
	}

	public Long putPicture(Picture picture) {
		jdbcTemplate.update("insert into picture(name, stream) values (?, ?) ",
				ps -> {
					ps.setString(1, picture.getName());
					ps.setBytes(2, picture.getStream());
				});

		return jdbcTemplate.query("select id from picture where name = ? order by create_time desc limit 1",
				ps -> ps.setString(1, picture.getName()),
				(rs, row) -> {
					return rs.getLong("id");
				}).stream().findAny().orElse(null);
	}
}
