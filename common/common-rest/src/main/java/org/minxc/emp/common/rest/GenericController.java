package org.minxc.emp.common.rest;

import com.minxc.emp.core.util.JacksonUtil;
import com.minxc.emp.core.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.minxc.emp.common.db.model.query.DefaultFieldSort;
import org.minxc.emp.common.db.model.query.DefaultPage;
import org.minxc.emp.common.db.model.query.DefaultQueryFilter;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.query.FieldSort;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.query.SortDirection;
import org.minxc.emp.core.api.response.impl.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Title: GenericController
 * @Package: org.minxc.emp.common.rest
 * @Description: TODO:(用一句话描述该文件做什么)
 * @author: Xianchang.min
 * @date 2018/8/25 18:48
 */

public class GenericController {

    protected <T> ResultMessage<T> getSuccessResult(T data, String msg) throws IOException {
        ResultMessage<T> ResultMessage = new ResultMessage<T>();
        ResultMessage.setSuccess(true);
        ResultMessage.setMessage(msg);
        ResultMessage.setData(data);
        return ResultMessage;
    }

    protected <T> ResultMessage<T> getSuccessResult(T data) throws IOException {
        ResultMessage<T> ResultMessage = new ResultMessage<T>();
        ResultMessage.setSuccess(true);
        ResultMessage.setMessage("请求成功");
        ResultMessage.setData(data);
        return ResultMessage;
    }


    protected ResultMessage<String> getSuccessResult(String msg) throws IOException {
        ResultMessage<String> ResultMessage = new ResultMessage<String>();
        ResultMessage.setSuccess(true);
        ResultMessage.setMessage(msg);
        return ResultMessage;
    }


    /**
     * 返回构建的QueryFilter
     *
     * @param request
     * @return QueryFilter
     * @throws
     * @since 1.0.0
     */
    protected QueryFilter getQueryFilter(HttpServletRequest request) {
        DefaultQueryFilter queryFilter = new DefaultQueryFilter();
        try {
            RequestUtil.handleRequestParam(request, queryFilter);

            String offset = request.getParameter("offset");
            String limit = request.getParameter("limit");
            if (StringUtil.isNotEmpty(offset) && StringUtil.isNotEmpty(limit)) {
                RowBounds rowBounds = new RowBounds(Integer.parseInt(offset), Integer.parseInt(limit));
                DefaultPage page = new DefaultPage(rowBounds);
                queryFilter.setPage(page);
            }

            // 设置排序字段
            String sort = request.getParameter("sort");
            String order = request.getParameter("order");
            if (StringUtil.isNotEmpty(sort) && StringUtil.isNotEmpty(order)) {
                List<FieldSort> fieldSorts = new ArrayList<FieldSort>();
                fieldSorts.add(new DefaultFieldSort(sort, SortDirection.fromString(order)));
                queryFilter.setFieldSortList(fieldSorts);
            }
        } catch (Exception e) {
        }
        return queryFilter;
    }


    // ====================下面方法不在推荐使用=============================

    /**
     *  向 response 中写入JSON数据
     *
     * @param response
     * @param data
     * @throws IOException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Deprecated
    protected void writeSuccessData(HttpServletResponse response, Object data) throws IOException {
        ResultMessage ResultMessage = new ResultMessage();
        ResultMessage.setSuccess(true);
        ResultMessage.setData(data);
//        response.getWriter().print(JSON.toJSONString(ResultMessage));
        response.getWriter().print(JacksonUtil.pojo2Json(ResultMessage));
    }

    @Deprecated
    protected void writeSuccessData(HttpServletResponse response, Object data, String msg) throws IOException {
        ResultMessage ResultMessage = new ResultMessage();
        ResultMessage.setSuccess(true);
        ResultMessage.setMessage(msg);
        ResultMessage.setData(data);
//        response.getWriter().print(JSON.toJSONString(ResultMessage));
        response.getWriter().print(JacksonUtil.pojo2Json(ResultMessage));
    }

    @Deprecated
    protected void writeSuccessResult(HttpServletResponse response, String msg) throws IOException {
        ResultMessage ResultMessage = new ResultMessage();
        ResultMessage.setSuccess(true);
        ResultMessage.setMessage(msg);
//        response.getWriter().print(JSON.toJSONString(ResultMessage));
        response.getWriter().print(JacksonUtil.pojo2Json(ResultMessage));
    }

    /**
     * 返回出错或成功的信息。
     *
     * @param writer
     * @param ResultMessage
     */
    @Deprecated
    protected void writeResultMessage(PrintWriter writer, ResultMessage ResultMessage) {
//        writer.print(JSON.toJSONString(ResultMessage));
        writer.print(JacksonUtil.pojo2Json(ResultMessage));
    }

}
