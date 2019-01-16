package org.minxc.emp.idm.api.model;

import java.io.Serializable;

/**
 * 系统的业务权限信息
 */
public interface Privilege extends Serializable {

    String getId();

    String getName();

}