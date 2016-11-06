package com.whoknows.reply;

import com.whoknows.domain.Reply;
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
@RequestMapping("/reply")
public class ReplyController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RelpyService relpyService;

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity createReply(@RequestBody Reply reply) {
		log.info(ToStringBuilder.reflectionToString(reply, ToStringStyle.MULTI_LINE_STYLE));
		if (relpyService.createReply(reply)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateReply(@RequestBody Reply reply) {
		log.info(ToStringBuilder.reflectionToString(reply, ToStringStyle.MULTI_LINE_STYLE));
		if (relpyService.updateReply(reply)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteReply(@PathVariable("id") Long id) {
		log.info("delete reply id: {}", id);

		if (relpyService.deleteReply(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getReply(@PathVariable("id") Long id) {
		log.info("get reply id: {}", id);
		ReplyDetail replyDetail = relpyService.getReplyDetail(id);
		if (replyDetail != null) {
			return ResponseEntity.ok(replyDetail);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
//	@RequestMapping(path = "/list/{topic_id}/{page}}", method = RequestMethod.GET)
//	public ResponseEntity getReplyList(@PathVariable("topic_id") Long topicId, @PathVariable("page") Long page, ){
//		
//	}
}
