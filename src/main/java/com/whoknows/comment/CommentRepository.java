package com.whoknows.comment;

import com.whoknows.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class CommentRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private void createComment(Comment comment){
		
	}
}
