package com.whoknows.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider{

	Logger log = LoggerFactory.getLogger(AuthProvider.class);
	
	@Autowired
	private AuthDao authDao ;
	
	@Override
	public Authentication authenticate(Authentication authen)throws AuthenticationException {
		if (authen instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken tkn = (UsernamePasswordAuthenticationToken) authen;
			String username = (String) tkn.getPrincipal();
			String password = (String) tkn.getCredentials();
			log.info("Attempting login username:{}.", username);

			authDao.getUserByEmail(username);
//			try {
//				Map<String, Object> results = jdbcTemplate.queryForMap(userSql, username);
//				log.info("[{}]", results.get("passwdMD5"));
//				if (!encoder.matches(password, (String) results.get("passwdMD5"))) {
//					log.info("Password provided does not match.");
//					throw new BadCredentialsException("Bad username/password given.");
//				}
//				final FullMoonUserDetails details = new FullMoonUserDetails((Integer)results.get("id"), username, (Long)results.get("company_id"));
//				return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
//			} catch (EmptyResultDataAccessException ex) {
//				log.warn("Could not find username of {}", username);
//				throw new UsernameNotFoundException("User " + username + " not found.");
//			}
			return null;
		} else {
			throw new AuthenticationServiceException("Incorrect token type given [" + authen.getClass().getSimpleName() + "].");
		}
	}

	
	@Override
	public boolean supports(Class<?> type) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(type);
	}

}
