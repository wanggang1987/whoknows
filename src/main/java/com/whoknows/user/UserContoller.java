package com.whoknows.user;

import com.whoknows.domain.Tag;
import com.whoknows.domain.User;
import java.util.List;
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
@RequestMapping("/user")
public class UserContoller {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/current", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity current() {
		try {
			User currentUser = userService.currentUser();
			if (currentUser != null) {
				return ResponseEntity.ok(currentUser);
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity registUser(@RequestBody User user) {
		log.info("Try to create user: {}", user == null ? "" : user);
		if (userService.createUser(user)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity editUserInfo(@RequestBody User user) {
		log.info(ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
		if (userService.editUserInfo(user)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/password/reset", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity resetPasswd(@RequestBody ResetPasswdRequest request) {
		log.info("Try to reset passwd: {}", request == null ? "" : request);
		if (userService.resetPasswd(request)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/topic/{userId}/{page}", method = RequestMethod.GET)
	public ResponseEntity getUserCreateTopics(@PathVariable("userId") Long userId, @PathVariable("page") Integer page) {
		UserTopicResponse userTopicResponse = userService.getUserCreateTopics(userId, page);
		if (userTopicResponse != null) {
			return ResponseEntity.ok(userTopicResponse);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/follow/{userId}/{page}", method = RequestMethod.GET)
	public ResponseEntity getUserFollowTopics(@PathVariable("userId") Long userId, @PathVariable("page") Integer page) {
		UserTopicResponse userTopicResponse = userService.getUserFollowTopics(userId, page);
		if (userTopicResponse != null) {
			return ResponseEntity.ok(userTopicResponse);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/reply/{userId}/{page}", method = RequestMethod.GET)
	public ResponseEntity getUserReplyTopics(@PathVariable("userId") Long userId, @PathVariable("page") Integer page) {
		UserTopicResponse userTopicResponse = userService.getUserReplyTopics(userId, page);
		if (userTopicResponse != null) {
			return ResponseEntity.ok(userTopicResponse);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/home/count/into/{userId}", method = RequestMethod.GET)
	public ResponseEntity getUserCountInfo(@PathVariable("userId") Long userId) {
		UserConutInfoResponse userConutInfoResponse = userService.getUserCountInfo(userId);
		if (userConutInfoResponse != null) {
			return ResponseEntity.ok(userConutInfoResponse);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(path = "/tag", method = RequestMethod.GET)
	public ResponseEntity getUserTagList() {
		List<Tag> tags = userService.getUserTagList();
		if (tags != null) {
			return ResponseEntity.ok(tags);
		} else {
			return ResponseEntity.badRequest().build();
		}
	} 
}
