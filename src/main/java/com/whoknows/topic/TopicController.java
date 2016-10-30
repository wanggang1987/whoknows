package com.whoknows.topic;

import com.whoknows.domain.Topic;
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
@RequestMapping("/topic")
public class TopicController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TopicService topicService;

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity createTopic(@RequestBody Topic topic) {
		log.info(ToStringBuilder.reflectionToString(topic, ToStringStyle.MULTI_LINE_STYLE));
		if (topicService.createTopic(topic)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateTopic(@RequestBody Topic topic) {
		log.info(ToStringBuilder.reflectionToString(topic, ToStringStyle.MULTI_LINE_STYLE));
		if (topicService.updateTopic(topic)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteTopic(@PathVariable("id") Long id) {
		log.info("delete topic : {}", id);
		if (topicService.deleteTopic(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getTopic(@PathVariable("id") Long id) {
		log.info("get topic : {}", id);
		TopicDetail topicDetail = topicService.getTopic(id);
		if (topicDetail != null) {
			return ResponseEntity.ok(topicDetail);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
