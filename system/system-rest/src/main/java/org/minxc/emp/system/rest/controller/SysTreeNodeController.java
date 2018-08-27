package org.minxc.emp.system.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.system.impl.manager.SysTreeManager;
import org.minxc.emp.system.impl.manager.SysTreeNodeManager;
import org.minxc.emp.system.impl.model.SysTreeNode;
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
public class SysTreeNodeController extends GenericController {
    @Autowired
    SysTreeManager sysTreeManager;
    @Autowired
    SysTreeNodeManager sysTreeNodeManager;

    /**
     * <pre>
     * sysTreeEdit.html的saveNode后端
     * 保存树节点
     * </pre>
     *
     * @param request
     * @param response
     * @param sysTreeNode
     * @throws Exception
     */
    @RequestMapping("save")
    @ErrorCatching(writeErrorToResponse = true, value = "保存系统树节点失败")
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SysTreeNode sysTreeNode) throws Exception {
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
     * <pre>
     * 获取sysTreeNode的后端
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getNodes")
    @ResponseBody
    public List<SysTreeNode> getNodes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<SysTreeNode> nodes = null;
        String treeId = RequestUtil.getString(request, "treeId");
        String key = RequestUtil.getString(request, "nodeKey");
        String treeKey = RequestUtil.getString(request, "treeKey");
        if (StringUtils.isNotEmpty(treeKey) && StringUtils.isEmpty(treeId)) {
            treeId = sysTreeManager.getByKey(treeKey).getId();
        }

        if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(treeId)) {
            SysTreeNode node = sysTreeNodeManager.getByTreeIdAndKey(treeId, key);
            nodes = sysTreeNodeManager.getStartWithPath(node.getPath());
        } else if (StringUtils.isNotEmpty(treeId)) {
            nodes = sysTreeNodeManager.getByTreeId(treeId);
        }
        return nodes;
    }

    /**
     * <pre>
     * 批量删除
     * </pre>
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
        	SysTreeNode node = sysTreeNodeManager.get(id);
            sysTreeNodeManager.removeByPath(node.getPath()+"%");
        }
        writeSuccessResult(response, "删除系统树节点成功");
    }

    /**
     * <pre>
     * 处理一下新节点的数据
     * </pre>
     *
     * @param sysTreeNode
     */
    private void handleNewSysTreeNode(SysTreeNode sysTreeNode) {
        // 新增时处理一下path
        if (StringUtils.isNotEmpty(sysTreeNode.getPath())) {
            sysTreeNode.setPath(sysTreeNode.getPath() + sysTreeNode.getId() + ".");
        } else {
            sysTreeNode.setPath(sysTreeNode.getId() + ".");
        }

        // 新增处理sn
        // 获取同级节点
        List<SysTreeNode> nodes = sysTreeNodeManager.getByParentId(sysTreeNode.getParentId());
        sysTreeNode.setSn(nodes.size() + 1);
    }
}
