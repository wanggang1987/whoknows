package com.whoknows.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.whoknows.domain.User;
import com.whoknows.security.autoDao.AuthDao;

@Component
public class AuthProvider implements AuthenticationProvider{

	Logger log = LoggerFactory.getLogger(AuthProvider.class);
	
	@Autowired
	private AuthDao authDao ;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Authentication authenticate(Authentication authen)throws AuthenticationException {
		if (authen instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken tkn = (UsernamePasswordAuthenticationToken) authen;
			String username = (String) tkn.getPrincipal();
			String password = (String) tkn.getCredentials();
			log.info("Attempting login username:{}.", username);

			try {
				User user = authDao.getUserByEmail(username);
				if (!encoder.matches(password, user.getePass())) {
					log.info("Password do not match.");
					throw new BadCredentialsException("Invalid username/password given.");
				}
				List<String> roles = authDao.getRolesByUserId(user.getId());
				return new UsernamePasswordAuthenticationToken(user, null, roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			} catch (EmptyResultDataAccessException ex) {
				log.warn("Could not find username : {}", username);
				throw new UsernameNotFoundException("User " + username + " not found.");
			}
		} else {
			throw new AuthenticationServiceException("Invalid token  [" + authen.getClass().getSimpleName() + "].");
		}
	}

	
	@Override
	public boolean supports(Class<?> type) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(type);
	}

}
