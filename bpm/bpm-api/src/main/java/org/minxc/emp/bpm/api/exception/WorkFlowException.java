package org.minxc.emp.bpm.api.exception;

import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.api.status.StatusCode;

/*
 * 
* 项目名称：bpm-api   
* 类名称：WorkFlowException   
* 类   
* 创建人：Xianchang.min   
* 创建时间：2018年8月29日 下午10:22:33   
* 修改人：Xianchang.min   
* 修改时间：2018年8月29日 下午10:22:33   
* 修改备注：   
* @version  1.0  
*
 */
public class WorkFlowException extends BusinessException {

	private static final long serialVersionUID = 5427635814041427869L;

	public WorkFlowException(String msg, StatusCode errorCode) {
        super(msg, errorCode);
    }

    public WorkFlowException(StatusCode errorCode) {
        super(errorCode);
    }

    public WorkFlowException(StatusCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }


}
