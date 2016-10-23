/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whoknows.picture;

import com.whoknows.domain.Picture;
import com.whoknows.domain.Values;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PictureRepository {

	private JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert pictureInsert;

	@Autowired
	public PictureRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		pictureInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("picture").usingGeneratedKeyColumns("id");
	}

	public Long putPicture(InputStream pictureStream) {
		Picture picture = new Picture();
		picture.setString_mb(pictureStream.toString());
		Number idVal = pictureInsert.executeAndReturnKey(Values.getValseMap(picture));
		return idVal.longValue();
	}

	public InputStream getPicture(Long id) {
		return jdbcTemplate.query(
				"select string_mb from picture where id = ? limit 1",
				ps -> ps.setLong(1, id),
				(rs, row) -> {
					return rs.getBlob("string_mb").getBinaryStream();
				}).stream().findAny().orElse(null);
	}
}
