package org.minxc.emp.bpm.rest.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.minxc.emp.base.api.aop.annotion.CatchErr;
import org.minxc.emp.base.core.util.BeanUtils;
import org.minxc.emp.base.core.util.StringUtil;
import org.minxc.emp.base.rest.GenericController;
import org.minxc.emp.base.rest.util.RequestUtil;
import org.minxc.emp.bpm.api.constant.BpmConstants;
import org.minxc.emp.bpm.api.engine.action.button.ButtonFactory;
import org.minxc.emp.bpm.api.model.def.BpmDataModel;
import org.minxc.emp.bpm.api.model.def.BpmVariableDef;
import org.minxc.emp.bpm.api.model.nodedef.BpmNodeDef;
import org.minxc.emp.bpm.api.model.nodedef.Button;
import org.minxc.emp.bpm.api.service.BpmProcessDefService;
import org.minxc.emp.bpm.core.manager.BpmDefinitionManager;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.bus.api.model.IBusinessColumn;
import org.minxc.emp.bus.api.model.IBusinessObject;
import org.minxc.emp.bus.api.service.IBusinessDataService;
import org.minxc.emp.bus.api.service.IBusinessObjectService;
import org.minxc.emp.org.api.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 流程定义管理
 *
 * @author jeff
 */
@RestController
@RequestMapping("/bpm/processDef/")
public class BpmProcessDefController extends GenericController {
    @Resource
    BpmDefinitionManager bpmDefinitionManager;
    @Resource
    BpmProcessDefService bpmProcessDefService;
    @Resource
    IBusinessDataService bizDataService;
    @Resource
    GroupService userGroupService;
	@Autowired
	IBusinessObjectService businessObjectService;


    @RequestMapping("getDefaultNodeBtns")
    public List<Button> getDefaultNodeBtns(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nodeId = RequestUtil.getString(request, "nodeId");
        String defId = RequestUtil.getString(request, "defId");
        Boolean isDefault = RequestUtil.getBoolean(request, "isDefault", false);

        BpmNodeDef nodeDef = bpmProcessDefService.getBpmNodeDef(defId, nodeId);

        return ButtonFactory.generateButtons(nodeDef, isDefault);
    }


    /**
     * 流程运行时可用变量
     */
    @RequestMapping("variablesTree")
    public Object variablesTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String defId = RequestUtil.getString(request, "defId");
        String flowKey = RequestUtil.getString(request, "flowKey");

        if (StringUtil.isEmpty(defId)) {
            BpmDefinition definition = bpmDefinitionManager.getByKey(flowKey);
            defId = definition.getId();
        }

        // 获取流程定义
        DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) bpmProcessDefService.getBpmProcessDef(defId);
        com.alibaba.fastjson.JSONArray treeJA = new com.alibaba.fastjson.JSONArray();

        // 获取表单BO树
        List<BpmDataModel> boDefs = bpmProcessDef.getDataModelList();
        if (BeanUtils.isNotEmpty(boDefs)) {
            for (BpmDataModel boDef : boDefs) {
                List<JSONObject> jsonObject = businessObjectService.boTreeData(boDef.getCode());
                treeJA.addAll(jsonObject);
            }
        }

        // 获取流程变量
        JSONObject flowVarJson = getFlowVarJson(bpmProcessDef);
        if (flowVarJson != null) {
            treeJA.add(flowVarJson);
        }

        return treeJA;
    }

    private JSONObject getFlowVarJson(DefaultBpmProcessDef procDef) {
        List<BpmVariableDef> variables = procDef.getVariableList();
        JSONObject flowVariable = JSONObject.parseObject("{name:\"流程变量\",icon:\"fa fa-bold dark\",\"nodeType\":\"root\"}");

        JSONArray varList = new JSONArray();
        if (BeanUtils.isNotEmpty(variables)) {
            for (BpmVariableDef variable : variables) {
                String name = variable.getName();
                variable.setName(variable.getKey()); // @ 前端流程变量都是 取name，
                // 而名字为desc
                JSONObject obj = (JSONObject) JSONObject.toJSON(variable);
                obj.put("nodeType", "var");
                varList.add(obj);
            }
        }

        flowVariable.put("children", varList);
        return flowVariable;
    }


    @RequestMapping("getGroupTypes")
    @CatchErr
    public void getGroupTypes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        writeSuccessData(response, userGroupService.getGroupTypes());
    }
    
    
    @RequestMapping("getSubjectVariable")
    public JSONArray getSubjectVariable(@RequestParam String defId) throws Exception {
    	JSONArray jsonArray = new JSONArray();
    	
    	 DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) bpmProcessDefService.getBpmProcessDef(defId);
    	 List<BpmDataModel> boDefs = bpmProcessDef.getDataModelList();
    	 if (BeanUtils.isNotEmpty(boDefs)) {
             for (BpmDataModel boDef : boDefs) {
                 IBusinessObject bo = businessObjectService.getFilledByKey(boDef.getCode());
                 
                 for(IBusinessColumn column :  bo.getRelation().getTable().getColumns()) {
                	 JSONObject json = new JSONObject();
                	 json.put("name",bo.getName() +"-"+column.getComment());
                	 json.put("key", bo.getKey()+"."+column.getKey());
                	 jsonArray.add(json);
                 }
             }
         }
    	 
    	 JSONObject json = JSONObject.parseObject("{name:\"发起人\",key:\"startorName\"}") ;
    	 jsonArray.add(json);
    	 
    	 JSONObject json1 = JSONObject.parseObject("{name:\"发起时间\",key:\"startDate\"}") ;
    	 jsonArray.add(json1);
    	 
    	 JSONObject json2 = JSONObject.parseObject("{name:\"流程标题\",key:\"title\"}") ;
    	 jsonArray.add(json2);
    	
    	return jsonArray;
    }
}
