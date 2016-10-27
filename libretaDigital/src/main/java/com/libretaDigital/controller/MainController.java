package com.libretaDigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {

		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {

		return new ModelAndView("home");
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView helloWorld() {

		String message = "<br><div style='text-align:center;'>" + "<h3>********** Hello World **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}

}
