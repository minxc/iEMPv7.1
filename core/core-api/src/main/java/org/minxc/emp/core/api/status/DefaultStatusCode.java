package org.minxc.emp.core.api.status;

import lombok.*;

/**
 * @version V1.0
 * @Title: DefaultStatusCode
 * @Package: org.minxc.emp.core.api.status
 * @Description: TODO:(用一句话描述该文件做什么)
 * @author: Xianchang.min
 * @date 2018/8/24 22:13
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DefaultStatusCode implements StatusCode{

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 应用
     */
    private String application;

    /**
     * 租户
     */
    private String tenant;
}
