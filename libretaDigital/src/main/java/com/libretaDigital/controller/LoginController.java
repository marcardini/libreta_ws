package com.libretaDigital.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.services.UserServiceImpl;

@Controller
@SessionAttributes("user")
public class LoginController{
	
	// @Autowired
	private UserServiceImpl userService;

	private static Logger log = Logger.getLogger(LoginController.class);
	private ObjectMapper mapper = new ObjectMapper();
	private Professor user;
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String getPage() {
		return "login";
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
	
}
