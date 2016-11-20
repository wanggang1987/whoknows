package com.whoknows.follow;

import com.whoknows.domain.TargetType;
import com.whoknows.hot.HotRecommend;
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
@RequestMapping("/follow")
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
	
	@RequestMapping(path = "/user/disable/{userId}/{targetId}", method = RequestMethod.POST)
	public ResponseEntity disFollowUser(@PathVariable("userId") Long userId, @PathVariable("targetId") Long targetId) {
		log.info("{} dis follow user {}", userId, targetId);
		if (followService.disFollow(userId, targetId, TargetType.user)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/tag/{userId}/{targetId}", method = RequestMethod.POST)
	public ResponseEntity followTag(@PathVariable("userId") Long userId, @PathVariable("targetId") Long targetId) {
		log.info("{} dis follow tag {}", userId, targetId);
		if (followService.follow(userId, targetId, TargetType.tag)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/tag/disable/{userId}/{targetId}", method = RequestMethod.POST)
	public ResponseEntity disFollowTag(@PathVariable("userId") Long userId, @PathVariable("targetId") Long targetId) {
		log.info("{} dis follow tag {}", userId, targetId);
		if (followService.disFollow(userId, targetId, TargetType.tag)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/topic/{userId}/{targetId}", method = RequestMethod.POST)
	public ResponseEntity followTopic(@PathVariable("userId") Long userId, @PathVariable("targetId") Long targetId) {
		log.info("{} follow topic {}", userId, targetId);
		if (followService.follow(userId, targetId, TargetType.topic)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/topic/disable/{userId}/{targetId}", method = RequestMethod.POST)
	public ResponseEntity disFollowTopic(@PathVariable("userId") Long userId, @PathVariable("targetId") Long targetId) {
		log.info("{} dis follow topic {}", userId, targetId);
		if (followService.disFollow(userId, targetId, TargetType.topic)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/recommend/{userId}", method = RequestMethod.POST)
	public  ResponseEntity followRecommend(@PathVariable("userId") Long userId, @RequestBody HotRecommend hotRecommend){
		log.info("{} follow recommend ", userId);
		if (followService.followRecommed(userId, hotRecommend)) {
			return ResponseEntity.ok().build();
		}else
			return ResponseEntity.badRequest().build();
	}
}
