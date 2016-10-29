package com.whoknows.domain.autoMappingBean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.whoknows.domain.Tag;


public class TagRowMapper implements RowMapper<Tag>{

	@Override
	public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
		Tag tag = new Tag();
		tag.setId(rs.getLong("id"));
		tag.setAction(rs.getString("action"));
		tag.setName(rs.getString("name"));
		tag.setRank(rs.getLong("rank"));
		return tag;
	}

}
