package com.cisdijob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping(value = "/")
	public ModelAndView indexPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/login/index");
		return mv;
	}
}
