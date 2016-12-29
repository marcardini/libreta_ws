package com.libretaDigital.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libretaDigital.DAO.StudentDAO;
import com.libretaDigital.entities.Professor;
import com.libretaDigital.services.LoginServiceImpl;

@Controller
@SessionAttributes("user")
public class LoginController {

	@Autowired
	private LoginServiceImpl loginService;
	
	private static Logger log = Logger.getLogger(LoginController.class);

	private ObjectMapper mapper = new ObjectMapper();

	 @RequestMapping(value = "/login", method = { RequestMethod.GET })
	    public String getPage() {
	        return "login";
	    }

//	 @RequestMapping(value = "/login", method = RequestMethod.POST)
//	 public String postLogin() {		
//		 log.debug(String.format("El usuario " + this.getPrincipal() + " ha sido logueado."));
//	     return "redirect:/";
//	 }	

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public String authenticate() {

		String ret = null;
		Professor user = loginService.validateUser("maria.tarigo@gmail.com", "admin");
		System.out.println("El usuario " + user.getEmail() + " ha sido logueado.");
		try {
			ret = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.debug(String.format("El usuario " + this.getPrincipal() + " estaba logueado."));
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
	
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
 

}
