package org.minxc.emp.bpm.api.model.def;

import org.minxc.emp.core.api.model.CommonModel;


/**
 * 流程定义实体接口
 */
public interface BpmnDefinition extends CommonModel{
    public final static class STATUS {
        /**
         * 草稿
         */
        public final static String DRAFT = "draft";
        /**
         * 已发布
         */
        public final static String DEPLOY = "deploy";
        /**
         * 禁用
         */
        public final static String FORBIDDEN = "forbidden";

    }

    /**
     * 返回 流程定义ID
     *
     * @return
     */
    public String getId();


    /**
     * 返回 流程名称
     *
     * @return
     */
    public String getName();


    /**
     * 返回 流程业务主键
     *
     * @return
     */
    public String getKey();


    /**
     * 返回 流程描述
     *
     * @return
     */
    public String getDesc();


    /**
     * 返回 所属分类ID
     *
     * @return
     */
    public String getTypeId();


    /**
     * 返回 流程状态。草稿、发布、禁用
     *
     * @return
     */
    public String getStatus();

    public void setActDefId(String actDefId);

    /**
     * 返回 BPMN - 流程定义ID
     *
     * @return
     */
    public String getActDefId();

    public void setActModelId(String actModelId);

    /**
     * 返回 act_model_id_
     *
     * @return
     */
    public String getActModelId();

    public void setActDeployId(String actDeployId);

    /**
     * 返回 BPMN - 流程发布ID
     *
     * @return
     */
    public String getActDeployId();

    public void setVersion(Integer version);

    /**
     * 返回 版本 - 当前版本号
     *
     * @return
     */
    public Integer getVersion();

    public void setMainDefId(String mainDefId);

    /**
     * 返回 版本 - 主版本流程ID
     *
     * @return
     */
    public String getMainDefId();

    public void setIsMain(String isMain);

    /**
     * 返回 版本 - 是否主版本
     *
     * @return
     */
    public String getIsMain();

    public void setCreateBy(String createBy);

    /**
     * 返回 创建人ID
     *
     * @return
     */
    public String getCreateBy();

    public void setCreateTime(java.util.Date createTime);

    /**
     * 返回 创建时间
     *
     * @return
     */
    public java.util.Date getCreateTime();

    public void setCreateOrgId(String createOrgId);

    /**
     * 返回 创建者所属组织ID
     *
     * @return
     */
    public String getCreateOrgId();

    public void setUpdateBy(String updateBy);

    /**
     * 返回 更新人ID
     *
     * @return
     */
    public String getUpdateBy();

    public void setUpdateTime(java.util.Date updateTime);

    /**
     * 返回 更新时间
     *
     * @return
     */
    public java.util.Date getUpdateTime();


    /**
     * 返回 support_mobile_
     *
     * @return
     */
    public Integer getSupportMobile();


    /**
     * 返回 def_setting_
     *
     * @return
     */
    public String getDefSetting();


    /**
     * 返回 rev_
     *
     * @return
     */
    public Integer getRev();


}
