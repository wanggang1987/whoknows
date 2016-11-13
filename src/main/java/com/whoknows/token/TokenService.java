package com.whoknows.token;

import com.whoknows.user.UserService;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private UserService userService;

	public String genToken() {
		return UUID.randomUUID().toString().toUpperCase();
	}

	public boolean storeToken(Long user_id, String token) {
		try {
			tokenRepository.storeToken(user_id, token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean continueRegister(Long userId, String token) {
		if (userId == null
				|| StringUtils.isEmpty(token)) {
			return false;
		}

		try {
			if (!tokenRepository.checkToken(userId, token)) {
				return false;
			}
			userService.activeUser(userId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
