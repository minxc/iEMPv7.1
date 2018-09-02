package org.minxc.emp.system.rest.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.rest.CommonController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.system.impl.manager.WorkbenchLayoutManager;
import org.minxc.emp.system.impl.manager.WorkbenchPanelManager;
import org.minxc.emp.system.impl.model.WorkbenchLayout;
import org.minxc.emp.system.impl.model.WorkbenchPanel;
import org.minxc.emp.system.util.ContextUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;


@RestController
@RequestMapping("/sys/workbenchPanel") 
public class WorkbenchPanelController extends CommonController<WorkbenchPanel>{
	@Resource
	WorkbenchPanelManager workbenchPanelManager;
	@Resource
	WorkbenchLayoutManager workbenchLayoutMananger;
	
	@RequestMapping("getPanelData")
	@ErrorCatching
	public  ResultMessage getPanelData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String panelId = RequestUtil.getString(request, "panelId_");
		String dataSource = RequestUtil.getString(request,"dataSource_");
		String dataType = RequestUtil.getString(request,"dataType_");
		
		JSON json = null ;
		// 调用自定义查询的数据
		//由于自定义查询数据源存在切换，故逻辑写此处即可
		if(WorkbenchPanel.DATA_TYPE_CUST_QUERY.equals(dataType)){
			json = getCustQueryData(request,dataSource);
		}else{
			DefaultQueryFilter filter = (DefaultQueryFilter) getQueryFilter(request);
			//将request请求的参数都put进去。用的时候可以用
			filter.getParams().putAll(RequestUtil.getParameterValueMap(request,false,true));
			json = workbenchPanelManager.getDataByInterFace(filter,dataSource);
		}
		
		return new ResultMessage(json);
	}


	/**
	 * 调用自定义查询
	 * @param request
	 * @param dataSource
	 * @return
	 */
	private JSON getCustQueryData(HttpServletRequest request, String dataSource) {
		/*String queryData = RequestUtil.getString(request, "querydata");
		int page = RequestUtil.getInt(request, "page", 1);
		String needPage = RequestUtil.getString(request, "needPage","");

		CustomQuery customQuery = customQueryManager.getByAlias(dataSource);
		if (customQuery==null) { return null; }
		int pageSize = RequestUtil.getInt(request, "pageSize", customQuery.getPageSize());

		String dbType= SysDataSourceUtil.getDbType(customQuery.getDsalias());
		
		if(StringUtil.isNotEmpty(needPage)){
			customQuery.setNeedPage("false".equals(needPage)?0:1);
		}
		
		// 切换这次进程的数据源
		DbContextHolder.setDataSource(customQuery.getDsalias(),dbType);
		Page pageList = (Page)customQueryManager.getData(customQuery, queryData,dbType, page, pageSize);
		
		return (JSON) JSONObject.toJSON(new PageJson(pageList));*/
		return null;
	}
	
	
	@RequestMapping("removeMyPanel")
	@ErrorCatching
	public  ResultMessage removeMyPanel(HttpServletRequest request,HttpServletResponse response,String layoutId) throws Exception{
		workbenchLayoutMananger.remove(layoutId);
		return new ResultMessage("移除成功");
	}
	
	@RequestMapping("saveMyPanel")
	@ErrorCatching
	public  ResultMessage saveMyPanel(HttpServletRequest request,HttpServletResponse response,String layoutId) throws Exception{
		String layoutKey = RequestUtil.getString(request, "layoutKey",ContextUtil.getCurrentUserId());
		String layoutListStr = RequestUtil.getString(request, "layoutList");
		
		List<WorkbenchLayout> layOutList = JSON.parseArray(layoutListStr, WorkbenchLayout.class);
		workbenchLayoutMananger.savePanelLayout(layOutList,layoutKey);
		return new ResultMessage("布局更新成功");
	}
	
	
	@RequestMapping("getMyWorkbench")
	@ErrorCatching
	public  ResultMessage getMyWorkbench(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String layoutKey = RequestUtil.getString(request, "layoutKey");
		
		List<WorkbenchPanel> workbenchPanelList = null;
		
		if(StringUtils.isNotEmpty(layoutKey) && ContextUtil.currentUserIsAdmin()){
			workbenchPanelList = workbenchPanelManager.getBylayoutKey(layoutKey);
		}else{
			workbenchPanelList = workbenchPanelManager.getByUserId(ContextUtil.getCurrentUserId());
		}
		
		return new ResultMessage(workbenchPanelList);
	}


	@Override
	protected String getModelDesc() {
		return "工作台面板";
	}

}
