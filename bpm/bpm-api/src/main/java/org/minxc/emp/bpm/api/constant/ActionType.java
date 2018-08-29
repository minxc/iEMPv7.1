package org.minxc.emp.bpm.api.constant;

import org.minxc.emp.bpm.api.exception.BpmStatusCode;
import org.minxc.emp.core.api.exception.BusinessException;


/**
 * 审批动作类型。
 */
public enum ActionType {
    DRAFT("draft", "保存草稿", "instanceSaveActionHandler"),
    START("start", "启动", "instanceStartActionHandler"),
    AGREE("agree", "同意", "taskAgreeActionHandler"),
    SAVE("save", "保存", "taskSaveActionHandler"),
    OPPOSE("oppose", "反对", "taskOpposeActionHandler"),
    REJECT("reject", "驳回", "taskRejectActionHandler"),
    REJECT2START("reject2Start", "驳回发起人", "taskReject2StartActionHandler"),
    RECOVER("recover", "撤销", "null"),
    DISPENDSE("dispense", "分发", "null"),
    TASKOPINION("taskOpinion", "审批历史", "instanceTaskOpinionActionHandler"),
    FLOWIMAGE("flowImage", "流程图", "instanceImageActionHandler"),
    PRINT("print", "打印", "instancePrintActionHandler"),
    MANUALEND("manualEnd", "人工终止", "instanceManualEndActionHandler"),

    LOCK("lock", "锁定", "taskLockActionHandler"),
    UNLOCK("unlock", "解锁", "taskUnlockActionHandler"),

    CREATE("create", "创建时", "null");

    // 键
    private String key = "";
    // 值
    private String name = "";

    private String beanId = "";

    // 构造方法
    private ActionType(String key, String name, String beanId) {
        this.key = key;
        this.name = name;
        this.beanId = beanId;
    }

    // =====getting and setting=====
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setName(String value) {
        this.name = value;
    }

    @Override
    public String toString() {
        return key;
    }

    /**
     * 通过key获取对象
     *
     * @param key
     * @return
     */
    public static ActionType fromKey(String key) {
        for (ActionType c : ActionType.values()) {
            if (c.getKey().equalsIgnoreCase(key))
                return c;
        }
        throw new BusinessException(BpmStatusCode.NO_TASK_ACTION);
    }

}
