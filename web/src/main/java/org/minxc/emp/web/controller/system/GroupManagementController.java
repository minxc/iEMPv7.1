package org.minxc.emp.web.controller.system;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.minxc.emp.common.db.model.page.PageJson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/groupManagement")
public class GroupManagementController {

	@RequestMapping("/index")
	public ModelAndView index() {

		ModelAndView grpMgrView = new ModelAndView("system/groupManagement");
		String serviceUrl = "http://127.0.0.1:8080/rest/groupmgr/listJson";
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> requestEntity = new HttpEntity<String>(header);
		ResponseEntity<String>  json = this.restTemplate.exchange(serviceUrl, HttpMethod.GET, requestEntity, String.class);
		System.out.println(json.getBody());
		return grpMgrView;
	}

	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * 组织架构列表(分页条件查询)数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception PageJson @throws
	 */
	@RequestMapping("/listJson")
	public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String serviceUrl = "http://127.0.0.1:8080/rest/groupmgr/listJson";
		PageJson json = this.restTemplate.getForObject(serviceUrl, PageJson.class);
		return json;
	}

}
