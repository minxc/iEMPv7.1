package org.minxc.emp.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**

* @Title: MainController 

* @Package: org.minxc.emp.web.controller 

* @Description:  主页控制器

* @author: Xianchang.min  

* @date  2018/8/17 23:11 

* @version V1.0  

*/


@Slf4j
@Controller
public class MainController {
    @RequestMapping(value = "/home")
    public ModelAndView mainPage(HttpServletRequest request, HttpServletResponse response){

        log.info("Main page request...");
        ModelAndView mainView = new ModelAndView("home/home");
        return mainView;
    }

}
