package org.minxc.emp.core.api.response.impl;


import com.google.common.collect.Maps;
import org.minxc.emp.core.api.status.CommonStatusCode;
import org.minxc.emp.core.api.status.StatusCode;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：base-intf
 * 类名称：ResultMsg
 * 类描述：回结果
 * 创建人：Xianchang.min
 * 创建时间：2018年8月21日 下午11:02:33
 * 修改人：Xianchang.min
 * 修改时间：2018年8月21日 下午11:02:33
 * 修改备注：
 *
 * @version 1.0
 */
public class ResultMessage<T> extends BaseResult {


    private static final long serialVersionUID = 2299392167452381897L;

    private T data = null; // 返回数据

    public ResultMessage() {

    }

    /**
     * 成功，有结果数据
     */
    public ResultMessage(T result) {
        this.setSuccess(true);
        this.setData(result);
    }

    public ResultMessage(StatusCode code, String msg) {
        if (CommonStatusCode.SUCCESS.getCode().equals(code.getCode())) {
            this.setSuccess(true);
        } else {
            this.setSuccess(false);
        }
        this.setStatusCode(code);
        this.setMessage(msg);
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void addMapParam(String key, Object val) {
        if (data == null) {
            Map<String, Object> map = Maps.newHashMap();
            this.data = (T) map;
        }
        if (!(this.data instanceof Map)) {
            throw new RuntimeException("设置参数异常！当前返回结果非map对象，无法使用 addMapParam方法获取数据");
        }

        Map<String, Object> map = (Map) data;
        map.put(key, val);
    }

    public Object getMapParam(String key) {
        if (!(this.data instanceof Map)) {
            throw new RuntimeException("获取参数异常！当前返回结果非map对象，无法使用 addMapParam方法获取数据");
        }

        Map<String, Object> map = (Map) data;
        return map.get(key);
    }
}

