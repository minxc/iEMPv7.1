package org.minxc.emp.core.api.executor;
/*
 *
 * 项目名称：base-intf
 * 类名称：ExecutorType
 * 类描述：   执行器类型
 * 创建人：Xianchang.min
 * 创建时间：2018年8月21日 下午10:50:21
 * 修改人：Xianchang.min
 * 修改时间：2018年8月21日 下午10:50:21
 * 修改备注：
 * @version  1.0
 *
 */
public enum ExecutorType {

    /**
     * 必要性执行器，没有这个执行器，执行器服务功能无法正常运行
     */
    MUST("MUST", "必要性执行器"),
    /**
     * 非必要性执行器，没有这个执行器服务，服务能跑，但可能有些东西不完整
     */
    MAY("MAY", "非必要性执行器");

    private String key;
    private String description;

    private ExecutorType(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return description;
    }

    /**
     * 根据key来判断是否跟当前一致
     */
    public boolean equalsWithKey(String key) {
        return this.key.equals(key);
    }
}
