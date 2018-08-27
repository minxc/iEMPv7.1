package org.minxc.emp.core.api.request;

/*
 * 
* 项目名称：base-intf   
* 类名称：RequestDto   
* 类描述：假如参数为某个bean，则使用该对象，比如传递一个订单对象。 说明:用于系统交互请求参数使用   
* 创建人：Xianchang.min   
* 创建时间：2018年8月21日 下午10:59:08   
* 修改人：Xianchang.min   
* 修改时间：2018年8月21日 下午10:59:08   
* 修改备注：   
* @version  1.0  
*
 */
public class RequestDto<E> {

    private RequestHead head;
    /**
     * 参数为某个bean时使用
     */
    private E data;


    public RequestHead getHead() {
        return head;
    }

    public void setHead(RequestHead head) {
        this.head = head;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
