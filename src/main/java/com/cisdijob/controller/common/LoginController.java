package com.cisdijob.controller.common;

//gjp:2015/9/5
/*
 * update 2015/10/20
 */
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cisdijob.controller.common.ViewController;
import com.cisdijob.model.entity.User;
import com.cisdijob.service.pages.UserService;
import com.cisdijob.utils.HttpServletUtil;

@Controller
@RequestMapping("/login")
public class LoginController extends ViewController {
	@Resource
	private UserService userService;
	private static Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@RequestMapping("/index")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping("/loginIn")
	@ResponseBody
	public Map<String, Object> loginIn(HttpServletRequest request,
			HttpServletResponse response,
			@RequestBody Map<String, Object> params) {
		String userName = params.get("username").toString();
		String password = params.get("password").toString();
		Map<String, Object> result = userService.login(userName, password);
		// 记录日志
		logger.debug("TEST");
		return result;
	}

}
