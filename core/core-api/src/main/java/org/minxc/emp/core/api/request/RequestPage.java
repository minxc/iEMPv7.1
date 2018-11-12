package org.minxc.emp.core.api.request;

import org.minxc.emp.core.api.query.FieldSort;

import java.util.ArrayList;
import java.util.List;


/*
 * 
* 项目名称：base-intf   
* 类名称：RequestPage   
* 类分页请求参数   
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午11:00:27   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午11:00:27   
* 修改备注：   
* @version  1.0  
*
 */
public class RequestPage extends RequestParam {

    //当前页
    private int pageNo = 1;
    private int pageSize = 20;
    private List<FieldSort> orders = new ArrayList<FieldSort>();

    public RequestPage() {

    }

    public RequestPage(int pageNo, int pageSize) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<FieldSort> getOrders() {
        return orders;
    }

    public void setOrders(List<FieldSort> orders) {
        this.orders = orders;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}

