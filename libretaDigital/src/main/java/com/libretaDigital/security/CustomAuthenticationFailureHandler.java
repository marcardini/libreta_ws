package com.libretaDigital.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private String DEFAULT_FAILURE_URL = "/login?error";

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		setDefaultFailureUrl(DEFAULT_FAILURE_URL);
		super.onAuthenticationFailure(request, response, exception);

		if (exception instanceof BadCredentialsException) {
			lockUser(request.getParameter("username"));
		}

	}

	private void lockUser(String username) {
		Users user = userService.getUser(username);

		if (user != null) {
			int failedCount = user.getFailedLogins() + 1;
			user.setFailedLogins(failedCount);

			if (failedCount > 4) {
				user.setLocked(true);
			}

			userService.saveUser(user);
		}
	}

}
