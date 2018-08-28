package org.minxc.emp.form.generator;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.minxc.emp.biz.api.constant.BusinessTableRelType;
import org.minxc.emp.biz.api.model.BusinessColumn;
import org.minxc.emp.biz.api.model.BusinessTableRel;
import org.minxc.emp.core.api.exception.BusinessException;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.minxc.emp.core.util.ThreadMapUtil;

/**
 * 自定义表单控件生成器<br>
 * input select radio dic 等等的生成<br>
 *
 */
@Component
public class PCFormElementGenerator extends AbsFormElementGenerator {

    private void handleNgModel(Element element, BusinessColumn column) {
        String boCode = (String) ThreadMapUtil.get("boCode");
        BusinessTableRel relation = (BusinessTableRel) ThreadMapUtil.get("relation");
        //如果是多行子表
        if (relation.getType().equals(BusinessTableRelType.ONE_TO_MANY.getKey())) {
            element.attr("ng-model", column.getTable().getKey() + "." + column.getKey());
            return;
        }

        element.attr("ng-model", getScopePath(relation) + "." + column.getKey());
    }


    protected String getColumnOnetext(BusinessColumn column) {

        Element element = getElement("input").attr("type", "text").addClass("form-control");

        handleNgModel(element, column);
        handlePermission(element, column);
        handleValidateRules(element, column);

        return element.toString();
    }


    protected String getColumnDate(BusinessColumn column) {
        Element element = getElement("input").addClass("form-control");

        handleNgModel(element, column);
        handleValidateRules(element, column);
        handlePermission(element, column);

        String configStr = column.getCtrl().getConfig();
        if (StringUtils.isEmpty(configStr)) {
            throw new BusinessException(String.format("表【%s】日期字段  【%s】解析失败,配置信息不能为空", column.getTable().getKey(), column.getComment()));
        }
        element.attr("ab-date", JSON.parseObject(configStr).getString("format"));

        return element.toString();
    }

    protected String getColumnDic(BusinessColumn column) {
        Element element = getElement("span").attr("type", "text").addClass("input-div");

        handleNgModel(element, column);
        handleValidateRules(element, column);
        handlePermission(element, column);

        JSONObject config = JSON.parseObject(column.getCtrl().getConfig());
        if (!config.containsKey("key")) {
            throw new BusinessException(String.format("表【%s】数据字典  字段【%s】解析失败,alias 配置信息不能为空", column.getTable().getKey(), column.getComment()));
        }

        element.attr("ab-combox", element.attr("ng-model"));
        element.attr("dict-key", config.getString("key"));

        return element.toString();
    }

    protected String getColumnIdentity(BusinessColumn column) {
        Element element = getElement("input").attr("type", "text").addClass("form-control");
        handleNgModel(element, column);
        handlePermission(element, column);
        handleValidateRules(element, column);

        JSONObject config = JSON.parseObject(column.getCtrl().getConfig());
        if (!config.containsKey("alias")) {
            throw new BusinessException(String.format("表【%s】流水号  字段【%s】解析失败,alias 配置信息不能为空", column.getTable().getKey(), column.getComment()));
        }
        element.attr("ab-serial-no", config.getString("alias"));
        return element.toString();
    }

    protected String getColumnMultitext(BusinessColumn column) {
        Element element = getElement("textarea").attr("type", "text").addClass("form-control");

        handleNgModel(element, column);
        handlePermission(element, column);
        handleValidateRules(element, column);

        return element.toString();
    }

    protected String getColumnCheckBox(BusinessColumn column) {
        JSONObject config = JSON.parseObject(column.getCtrl().getConfig());
        if (!config.containsKey("options")) {
            throw new BusinessException(String.format("表【%s】CheckBox 字段  【%s】解析失败,options 配置信息不能为空", column.getTable().getKey(), column.getComment()));
        }

        Element permissionElement = getElement("div");
        handleNgModel(permissionElement, column);
        permissionElement.attr("ab-checked-permission", getPermissionPath(column));
        handleValidateRules(permissionElement, column);

        JSONArray options = config.getJSONArray("options");
        for (int i = 0; i < options.size(); i++) {
            JSONObject option = options.getJSONObject(i);

            Element element = permissionElement.appendElement("label").addClass("checkbox-inline");
            Element child = element.appendElement("input").attr("type", "checkbox");
            child.attr("ab-checkbox", "");
            handleNgModel(child, column);
            child.attr("value", option.getString("key"));
            element.appendText(option.getString("txt"));
        }

        return permissionElement.toString();
    }

    protected String getColumnRadio(BusinessColumn column) {
        JSONObject config = JSON.parseObject(column.getCtrl().getConfig());
        if (!config.containsKey("options")) {
            throw new BusinessException(String.format("表【%s】Radio 字段  【%s】解析失败,options 配置信息不能为空", column.getTable().getKey(), column.getComment()));
        }

        Element permissionElement = getElement("div");
        handleNgModel(permissionElement, column);
        permissionElement.attr("ab-checked-permission", getPermissionPath(column));
        handleValidateRules(permissionElement, column);

        JSONArray options = config.getJSONArray("options");
        for (int i = 0; i < options.size(); i++) {
            JSONObject option = options.getJSONObject(i);

            Element element = permissionElement.appendElement("label").addClass("radio-inline");
            Element child = element.appendElement("input").attr("type", "radio");
            handleNgModel(child, column);
            child.attr("value", option.getString("key"));
            element.appendText(option.getString("txt"));
        }

        return permissionElement.toString();
    }

    protected String getColumnSelect(BusinessColumn column, Boolean isMultiple) {
        JSONObject config = JSON.parseObject(column.getCtrl().getConfig());
        if (!config.containsKey("options")) {
            throw new BusinessException(String.format("表【%s】Select 字段  【%s】解析失败,options 配置信息不能为空", column.getTable().getKey(), column.getComment()));
        }

        Element permissionElement = getElement("select").addClass("form-control");
        handleNgModel(permissionElement, column);
        permissionElement.attr("ab-checked-permission", getPermissionPath(column));
        handleValidateRules(permissionElement, column);

        if (isMultiple) {
            permissionElement.attr("multiple", "true");
        }

        JSONArray options = config.getJSONArray("options");
        for (int i = 0; i < options.size(); i++) {
            JSONObject option = options.getJSONObject(i);

            Element element = permissionElement.appendElement("option");
            element.attr("value", option.getString("key"));
            element.text(option.getString("txt"));
        }

        return permissionElement.toString();
    }

    protected String getColumnFile(BusinessColumn column) {
        //<a href="javascript:void(0)" class="btn btn-primary fa-upload" ab-upload ng-model="test">指令测试</a>
        Element element = getElement("a").attr("href", "javascript:void(0)").addClass("btn btn-primary fa-upload");
        element.attr("ab-upload", "");
        handleNgModel(element, column);
        element.attr("ab-edit-permission", getPermissionPath(column));

        return element.toString();
    }

    // id="boCode-tableKey" ab-basic-permission="boCode.table.tableKey" ...
    public String getSubAttrs(BusinessTableRel rel) {
        StringBuffer sb = new StringBuffer();
        sb.append(" id=" + "\"" + rel.getBusObj().getKey() + "-" + rel.getTableKey() + "\" ");

        //一对多的三层情况下弹框展示
        if (rel.getType().equals(BusinessTableRelType.ONE_TO_MANY.getKey())
                && !rel.getParent().getType().equals(BusinessTableRelType.MAIN.getKey())) {
            sb.append(" hide ");
        }

        sb.append(" table-key=\"" + rel.getTableKey() + "\" ");

        return sb.toString();
    }


    @Override
    public String getGeneratorName() {
        return "generator";
    }
}
