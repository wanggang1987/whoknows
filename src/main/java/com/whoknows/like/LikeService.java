package com.whoknows.like;

import com.whoknows.domain.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LikeRepository likeRepository;
	
	public boolean like(Long userId, Long tartgetId, TargetType type) {
		try {
			likeRepository.like(userId, tartgetId, type);
			return true;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return false;
		}
	}
	
	public Integer likeCount(Long targetId, TargetType type){
		try {
			return likeRepository.likeCount(targetId, type);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}
}
