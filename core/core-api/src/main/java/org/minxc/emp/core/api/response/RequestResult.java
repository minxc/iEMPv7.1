package org.minxc.emp.core.api.response;

import org.minxc.emp.core.api.status.StatusCode;

/**
 * @version V1.0
 * @Title: ReuqestResult
 * @Package: org.minxc.emp.core.api.response
 * @Description: TODO:(用一句话描述该文件做什么)
 * @author: Xianchang.min
 * @date 2018/8/24 23:26
 */

public interface RequestResult {

    public boolean isSuccess();

    public StatusCode getStatusCode();

    public String getCode();

    public String getMessage();

    public String getCause();

}
