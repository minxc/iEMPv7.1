package org.minxc.emp.common.db.id;

import org.minxc.emp.common.db.api.IdGenerator;

/**
 * 唯一ID获取类。
 * 描述：用于产生唯一ID，需要配置spring文件，具体参考
 */
public class UniqueIdUtil {

    private static IdGenerator idGenerator;

    public void setIdGenerator(IdGenerator idGenerator_) {
        idGenerator = idGenerator_;
    }

    /**
     * 获取long型的ID.
     *
     * @return Long
     * @throws
     * @since 1.0.0
     */
    public static Long getUId() {
        return idGenerator.getUId();
    }

    /**
     * 获取字符型的ID
     *
     * @return
     */
    public static String getSuid() {
        return idGenerator.getSuid();
    }
}
