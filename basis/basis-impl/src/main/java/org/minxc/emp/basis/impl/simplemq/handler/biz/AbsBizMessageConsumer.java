package org.minxc.emp.basis.impl.simplemq.handler.biz;

import java.io.Serializable;

import org.minxc.emp.basis.api.jms.consumer.JmsConsumer;

/**
 * 做公共逻辑,如日志等
 *
 */
public abstract class AbsBizMessageConsumer<T extends Serializable> implements JmsConsumer<T>{

}
