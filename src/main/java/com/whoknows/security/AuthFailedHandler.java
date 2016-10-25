package com.whoknows.security;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailedHandler extends SimpleUrlAuthenticationFailureHandler{

}
