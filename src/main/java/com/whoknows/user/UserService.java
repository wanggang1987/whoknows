package com.whoknows.user;

import com.whoknows.domain.User;
import com.whoknows.wkMessage.password.ResetPasswdRequest;
import com.whoknows.wkMessage.search.TopicResult;
import com.whoknows.wkMessage.user.UserSummaryInfo;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	public UserSummaryInfo getUserSummaryInfo(Long id) {
		if (id == null) {
			return null;
		}

		try {
			UserSummaryInfo userView = new UserSummaryInfo();
			userView.setUser(userRepository.getUserById(id), userRepository.getUserRolesByUserId(id));
			return userView;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public List<TopicResult> getUserTopic(Long id) {
		if (id == null) {
			return null;
		}

		try {
			return userRepository.getUserTopic(id).stream().map(topic -> {
				TopicResult topicView = new TopicResult();
				topicView.setTopic(topic);
				return topicView;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public boolean createUser(User user) {
		if (StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getPasswd())) {
			return false;
		}
		try {
			userRepository.createUser(user);
			log.info("Create user :{} success.", user.getEmail());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean resetPasswd(ResetPasswdRequest request) {
		if (request == null || StringUtils.isEmpty(request.getEmail())
				|| StringUtils.isEmpty(request.getOldPasswd())
				|| StringUtils.isEmpty(request.getNewPasswd())
				|| StringUtils.isEmpty(request.getRepeatNewPasswd())
				|| !StringUtils.equals(request.getNewPasswd(), request.getRepeatNewPasswd())) {
			return false;
		}

		if (!userRepository.validUserByEmailAndPasswd(request.getEmail(), request.getOldPasswd())) {
			return false;
		}

		try {
			userRepository.resetPasswd(request);
			return true;
		} catch (Exception e) {
			log.error("Reset passwd error , username:{}, {}", request.getEmail(), e);
			return false;
		}
	}

	public boolean editUserInfo(User user) {
		if (user.getId() == null) {
			return false;
		}

		try {
			userRepository.editUserInfo(user);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

}
