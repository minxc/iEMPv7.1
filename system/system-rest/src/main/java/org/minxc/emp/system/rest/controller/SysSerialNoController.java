package org.minxc.emp.system.rest.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.basis.api.constant.SysStatusCode;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.system.api.service.SerialNoService;
import org.minxc.emp.system.impl.manager.SerialNoManager;
import org.minxc.emp.system.impl.model.SerialNoEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;

/**
 * 流水号生成管理
 */
@RestController
@RequestMapping("/sys/serialNo/")
public class SysSerialNoController extends GenericController {
    @Resource
    SerialNoManager serialNoManager;
    @Resource
    SerialNoService serialNoService;


    /**
     * 流水号生成列表(分页条件查询)数据
     *
     * @param request
     * @param response
     * @return
     * @throws Exception PageJson
     */
    @RequestMapping("listJson")
    public 
    PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SerialNoEntity> SerialNoList = (Page<SerialNoEntity>) serialNoManager.query(queryFilter);
        return new PageJson(SerialNoList);
    }

    public static void main(String[] args) {

    }

    /**
     * 根据ID获取内容
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getById")
    @ErrorCatching(writeErrorToResponse = true)
    public void getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = RequestUtil.getString(request, "id");
        SerialNoEntity SerialNo = serialNoManager.get(id);
        writeSuccessData(response, SerialNo);
    }

    /**
     * 保存流水号生成信息
     *
     * @param request
     * @param response
     * @param SerialNo
     * @throws Exception void
     */
    @RequestMapping("save")
    @ErrorCatching
    public void save(HttpServletRequest request, HttpServletResponse response, @RequestBody SerialNoEntity SerialNo) throws Exception {
        String ResultMessage = null;

        boolean rtn = serialNoManager.isAliasExisted(SerialNo.getId(), SerialNo.getAlias());
        if (rtn) {
            throw new BusinessException("别名已经存在!", SysStatusCode.SERIALNO_EXSIT);
        }

        if (StringUtils.isEmpty(SerialNo.getId())) {
            serialNoManager.create(SerialNo);
            ResultMessage = "添加流水号生成成功";
        } else {
            serialNoManager.update(SerialNo);
            ResultMessage = "更新流水号生成成功";
        }

        writeSuccessResult(response, ResultMessage);
    }


    /**
     * 批量删除流水号生成记录
     *
     * @param request
     * @param response
     * @throws Exception void
     */
    @RequestMapping("remove")
    @ErrorCatching("删除流水号失败")
    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] aryIds = RequestUtil.getStringAryByStr(request, "id");

        serialNoManager.removeByIds(aryIds);
        writeSuccessResult(response, "删除流水号成功");
    }

    /**
     * 取得流水号生成分页列表
     *
     * @param request
     * @param response
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("showlist")
    public 
    PageJson showlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<SerialNoEntity> SerialNoList = (Page<SerialNoEntity>) serialNoManager.query(queryFilter);
        return new PageJson(SerialNoList);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("preview")
    public List<SerialNoEntity> preview(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String alias = RequestUtil.getString(request, "alias");
        List<SerialNoEntity> identities = serialNoManager.getPreviewIden(alias);

        return identities;
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("getNextIdByAlias")
    public ResultMessage<String> getNextIdByAlias(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String alias = RequestUtil.getString(request, "alias");
        if (serialNoManager.isAliasExisted(null, alias)) {
            String nextId = serialNoService.genNextNo(alias);
            return getSuccessResult(nextId,"请求成功");
        }
        return getSuccessResult("","请求成功");
    }
}
