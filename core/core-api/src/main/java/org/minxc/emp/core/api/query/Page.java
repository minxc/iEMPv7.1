package org.minxc.emp.core.api.query;

/**
 * 项目名称：base-intf
 * 类名称：Page
 * 类   分页对象接口
 * 创建人：Xianchang.min
 * 创建时间：2018年8月21日 下午10:40:15
 * 修改人：Xianchang.min
 * 修改时间：2018年8月21日 下午10:40:15
 * 修改备注：
 * @version  1.0
 *
 */
public interface Page {

    /**
     * 默认每页显示的记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 返回每页大小
     *
     * @return
     */
    public Integer getPageSize();

    /**
     * 返回总页数
     *
     * @return
     */
    public Integer getTotal();

    /**
     * 返回总页码
     *
     * @return
     */
    public Integer getPageNo();

    /**
     * 是否显示总记录数
     *
     * @return
     */
    public boolean isShowTotal();


    /**
     * 获取当前页的偏移量
     *
     * @return
     */
    public Integer getCurrentIndex();
}
