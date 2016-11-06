package com.whoknows.comment;

import com.whoknows.domain.Comment;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comment")
public class CommentController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity createComment(@RequestBody Comment comment) {
		log.info(ToStringBuilder.reflectionToString(comment, ToStringStyle.MULTI_LINE_STYLE));
		if (commentService.createComment(comment)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateComment(@RequestBody Comment comment) {
		log.info(ToStringBuilder.reflectionToString(comment, ToStringStyle.MULTI_LINE_STYLE));
		if (commentService.updateComment(comment)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteComment(@PathVariable("id") Long id) {
		log.info("delete comment id: {}", id);

		if (commentService.deleteComment(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getComment(@PathVariable("id") Long id) {
		log.info("get comment id: {}", id);
		Comment comment = commentService.getComment(id);
		if (comment != null) {
			return ResponseEntity.ok(comment);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
