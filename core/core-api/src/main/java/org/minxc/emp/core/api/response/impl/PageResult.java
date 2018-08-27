package org.minxc.emp.core.api.response.impl;

import org.minxc.emp.core.api.model.PageList;


/**
 * 
* 项目名称：base-intf   
* 类名称：PageResult   
* 类描述： 分页请求结果
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午11:02:11   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午11:02:11   
* 修改备注：   
* @version  1.0  
*
 */
public class PageResult<E> extends BaseResult {

    public PageResult(PageList<E> data) {
        super();
        this.data = data;
    }

    public PageResult(String errorMsg) {
        super(errorMsg);
    }

    public PageResult() {
        super();
    }

    private PageList<E> data;

    public PageList<E> getData() {
        return data;
    }

    public void setData(PageList<E> data) {
        this.data = data;
    }
}
