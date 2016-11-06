package com.whoknows.message.comment;

import com.whoknows.user.UserDetail;
import java.sql.Timestamp;


public class CommentResult {

	private Long id ;
	private UserDetail author;
	private Long reply_id;
	private String content;
	private String action;
	private Timestamp create_time;
	private Timestamp update_time;
}
