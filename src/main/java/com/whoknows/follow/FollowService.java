package com.whoknows.follow;

import com.whoknows.domain.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FollowRepository followRepository;

	public boolean follow(Long userId, Long tartgetId, TargetType type) {
		try {
			followRepository.follow(userId, tartgetId, type);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}
}
