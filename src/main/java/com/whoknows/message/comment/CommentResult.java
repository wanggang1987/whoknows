package com.whoknows.message.comment;

import java.sql.Timestamp;

import com.whoknows.message.user.UserSummaryInfo;

public class CommentResult {

	private Long id ;
	private UserSummaryInfo author;
	private Long reply_id;
	private String content;
	private String action;
	private Timestamp create_time;
	private Timestamp update_time;
}
