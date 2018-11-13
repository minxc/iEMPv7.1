package org.minxc.emp.core.api.model;

import java.util.Date;

/**
 * 项目名称：base-intf
 * 类名称：CreateAndUpdateInfoModel
 * 类   创建更新信息接口--若实现了该接口、则保存更新时会自动赋值
 * 创建人：Xianchang.min
 * 创建时间：2018年8月22日 下午11:41:49
 * 修改人：Xianchang.min
 * 修改时间：2018年8月22日 下午11:41:49
 * 修改备注：
 * @version  1.0
 **/

public interface NewOrUpdateModel {

    public Date getCreateTime();

    /**
     * 
     * 设置创建时间
     * 
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime);

    /**
     * 
     * 创建人ID
     * 
     *
     * @return
     */
    public String getCreateBy();

    /**
     * 
     * 设置创建人ID
     * 
     *
     * @param createBy
     */
    public void setCreateBy(String createBy);

    /**
     * 
     * 更新时间
     * 
     *
     * @return
     */
    public Date getUpdateTime();

    /**
     * 
     * 设置 更新时间
     * 
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime);

    /**
     * 
     * 更新人ID
     * 
     *
     * @return
     */
    public String getUpdateBy();

    /**
     * 
     * 设置更新人ID
     * 
     *
     * @param updateBy
     */
    public void setUpdateBy(String updateBy);
}
