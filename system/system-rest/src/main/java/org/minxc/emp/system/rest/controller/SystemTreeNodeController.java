package org.minxc.emp.system.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.system.impl.manager.SystemTreeManager;
import org.minxc.emp.system.impl.manager.SystemTreeNodeManager;
import org.minxc.emp.system.impl.model.SystemTreeNodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * sysTreeNode层的controller
 */
@Controller
@RequestMapping("/sys/sysTreeNode/")
public class SystemTreeNodeController extends GenericController {
    @Autowired
    SystemTreeManager sysTreeManager;
    @Autowired
    SystemTreeNodeManager sysTreeNodeManager;

    /**
     * 
     * sysTreeEdit.html的saveNode后端
     * 保存树节点
     * 
     *
     * @param request
     * @param response
     * @param sysTreeNode
     * @throws Exception
     */
    @RequestMapping("save")
    @ErrorCatching(writeErrorToResponse = true, value = "保存系统树节点失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SystemTreeNodeEntity sysTreeNode) throws Exception {
        if (StringUtils.isEmpty(sysTreeNode.getId())) {
            sysTreeNode.setId(UniqueIdUtil.getSuid());
            handleNewSysTreeNode(sysTreeNode);
            sysTreeNodeManager.create(sysTreeNode);
        } else {
            sysTreeNodeManager.update(sysTreeNode);
        }
        writeSuccessData(response, sysTreeNode, "保存系统树节点成功");
    }

    /**
     * 
     * 获取sysTreeNode的后端
     * 
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getNodes")
    @ResponseBody
    public List<SystemTreeNodeEntity> getNodes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<SystemTreeNodeEntity> nodes = null;
        String treeId = RequestUtil.getString(request, "treeId");
        String key = RequestUtil.getString(request, "nodeKey");
        String treeKey = RequestUtil.getString(request, "treeKey");
        if (StringUtils.isNotEmpty(treeKey) && StringUtils.isEmpty(treeId)) {
            treeId = sysTreeManager.getByKey(treeKey).getId();
        }

        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(treeId)) {
            SystemTreeNodeEntity node = sysTreeNodeManager.getByTreeIdAndKey(treeId, key);
            nodes = sysTreeNodeManager.getStartWithPath(node.getPath());
        } else if (StringUtils.isNotEmpty(treeId)) {
            nodes = sysTreeNodeManager.getByTreeId(treeId);
        }
        return nodes;
    }

    /**
     * 
     * 批量删除
     * 
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("remove")
    @ErrorCatching(writeErrorToResponse = true, value = "删除系统树节点失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");
        for (String id : aryIds) {
        	SystemTreeNodeEntity node = sysTreeNodeManager.get(id);
            sysTreeNodeManager.removeByPath(node.getPath()+"%");
        }
        writeSuccessResult(response, "删除系统树节点成功");
    }

    /**
     * 
     * 处理一下新节点的数据
     * 
     *
     * @param sysTreeNode
     */
    private void handleNewSysTreeNode(SystemTreeNodeEntity sysTreeNode) {
        // 新增时处理一下path
        if (StringUtils.isNotEmpty(sysTreeNode.getPath())) {
            sysTreeNode.setPath(sysTreeNode.getPath() + sysTreeNode.getId() + ".");
        } else {
            sysTreeNode.setPath(sysTreeNode.getId() + ".");
        }

        // 新增处理sn
        // 获取同级节点
        List<SystemTreeNodeEntity> nodes = sysTreeNodeManager.getByParentId(sysTreeNode.getParentId());
        sysTreeNode.setSn(nodes.size() + 1);
    }
}
