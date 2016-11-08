package com.whoknows.follow;

import com.whoknows.domain.TargetType;
import com.whoknows.hot.HotRecommend;
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
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean disFollow(Long userId, Long tartgetId, TargetType type) {
		try {
			followRepository.disFollow(userId, tartgetId, type);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public boolean followRecommed(Long userId, HotRecommend hotRecommend) {
		try {
			hotRecommend.getTags().parallelStream().forEach(tag -> {
				followRepository.follow(userId, tag.getTagID(), TargetType.tag);
			});
			hotRecommend.getVips().parallelStream().forEach(vip -> {
				followRepository.follow(userId, vip.getUserID(), TargetType.user);
			});
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}

	public Integer followCount(Long tartgetId, TargetType type) {
		try {
			return followRepository.followCount(tartgetId, type);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}

	public Boolean isFollowed(Long userId, Long tartgetId, TargetType type) {
		try {
			return followRepository.isFollow(userId, tartgetId, type);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}
}
