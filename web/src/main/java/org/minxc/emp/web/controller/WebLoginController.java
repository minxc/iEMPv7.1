/**
 * 
 */
package org.minxc.emp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Xianchang.min
 *
 */

@Slf4j
@Controller
public class WebLoginController {
	
	  	@RequestMapping(value = "/login")
	    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){

	        log.info("login page ...");
	        ModelAndView mainView = new ModelAndView("login/login");
	        return mainView;
	    } 
}
