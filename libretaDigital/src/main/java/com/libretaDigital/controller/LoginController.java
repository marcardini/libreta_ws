package com.libretaDigital.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.services.LoginServiceImpl;
import com.libretaDigital.services.UserServiceImpl;

@Controller
@SessionAttributes("user")
public class LoginController extends SimpleUrlAuthenticationFailureHandler implements AuthenticationSuccessHandler {

	@Autowired
	private LoginServiceImpl loginService;

	// @Autowired
	private UserServiceImpl userService;

	private static Logger log = Logger.getLogger(LoginController.class);

	private ObjectMapper mapper = new ObjectMapper();

	private Professor user;
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String getPage() {
		return "login";
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		// setDefaultFailureUrl(DEFAULT_FAILURE_URL);
		super.onAuthenticationFailure(request, response, exception);

		/*
		 * if (exception instanceof BadCredentialsException)
		 * lockUser(request.getParameter("username"));
		 */
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Professor loginUser = (Professor) authentication.getPrincipal();

		user = loginService.validateUser(loginUser.getEmail(), loginUser.getPassword());
		// user.setFailedLogins(0);
		// user.setLastLoginDate(new Date());
		loginService.saveUser(user);

		userService.saveUser(user);

		response.sendRedirect("home");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin() {
		log.debug(String.format("El usuario " + this.getPrincipal() + " ha sido logueado."));
		return "redirect:/";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public String authenticate() {

		String ret = null;
		Professor professor = loginService.validateUser(user.getEmail(), user.getPassword());
		System.out.println("El usuario " + professor.getEmail() + " ha sido logueado.");
		try {
			ret = mapper.writeValueAsString(professor);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.debug(String.format("El usuario " + this.getPrincipal() + " estaba logueado."));
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public LoginServiceImpl getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginServiceImpl loginService) {
		this.loginService = loginService;
	}
}
