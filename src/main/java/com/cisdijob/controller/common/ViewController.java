package com.cisdijob.controller.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.model.entity.User;
import com.cisdijob.utils.HttpServletUtil;
/**
 * 
 * @author gbz
 * 
 */
//modify gjp:2015/9/5
@Controller
@RequestMapping(value = "view")
public class ViewController {
	public ModelAndView createLayoutView(String path) {
		ModelAndView view = new ModelAndView();
		view.setViewName("common/ace-layout");
		view.addObject("header_path", "common/ace-header.html");
		view.addObject("content_path", path);
		view.addObject("footer_path", "common/ace-footer.html");
		 User user = (User) HttpServletUtil.getInstance().getSession().getAttribute("currentUser");
		 view.addObject("user", user);
		return view;
	}
}
