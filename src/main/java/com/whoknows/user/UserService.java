package com.whoknows.user;

import com.whoknows.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserInfo(Long id) {
		if (id == null) {
			return null;
		}
		
		try {
			return userRepository.getUserInfo(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
}
