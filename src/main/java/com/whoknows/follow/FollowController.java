package com.whoknows.follow;

import com.whoknows.domain.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("follow")
public class FollowController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FollowService followService;

	@RequestMapping(path = "/user/{userId}/{targetId}", method = RequestMethod.POST)
	public ResponseEntity followUser(@PathVariable("userId") Long userId, @PathVariable("targetId") Long targetId) {
		log.info("{} follow user {}", userId, targetId);
		if (followService.follow(userId, targetId, TargetType.user)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/tag/{userId}/{targetId}", method = RequestMethod.POST)
	public ResponseEntity followTag(@PathVariable("userId") Long userId, @PathVariable("targetId") Long targetId) {
		log.info("{} follow tag {}", userId, targetId);
		if (followService.follow(userId, targetId, TargetType.tag)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/topic/{userId}/{targetId}", method = RequestMethod.POST)
	public ResponseEntity followTopic(@PathVariable("userId") Long userId, @PathVariable("targetId") Long targetId) {
		log.info("{} follow tag {}", userId, targetId);
		if (followService.follow(userId, targetId, TargetType.topic)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
