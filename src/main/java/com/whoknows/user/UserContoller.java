package com.whoknows.user;

import com.whoknows.framework.TopicView;
import com.whoknows.framework.UserView;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserContoller {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/info", method = RequestMethod.GET)
	public ResponseEntity getUserInfo(Long id) {
		UserView userView = userService.getUserInfo(id);
		if (userView != null) {
			return ResponseEntity.ok(userView);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/follow/topic", method = RequestMethod.GET)
	public ResponseEntity getUserFollowTopic(Long id) {
		List<TopicView> topicViews = new ArrayList<>();
		return ResponseEntity.ok(topicViews);
	}

	@RequestMapping(path = "/topic", method = RequestMethod.GET)
	public ResponseEntity getUserTopic(Long id) {
		List<TopicView> topicViews = userService.getUserTopic(id);
		if (topicViews != null) {
			return ResponseEntity.ok(topicViews);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(path = "/reply/topic", method = RequestMethod.GET)
	public ResponseEntity getUserReplyTopic(Long id) {
		List<TopicView> topicViews = new ArrayList<>();
		return ResponseEntity.ok(topicViews);
	}
}
