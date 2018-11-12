package org.minxc.emp.system.impl.upload;

import java.io.InputStream;

/**
 * 系统的上传流接口
 * ps：跟任何业务都无关，可作为工具类提供出来
 */
public interface IUploader {
	/**
	 * 
	 * 类型
	 * 
	 * 
	 * @return
	 */
	String type();

	/**
	 * 
	 * 上传流
	 * 
	 * 
	 * @param is
	 *            流
	 * @param name
	 *            流名
	 * @return 流路径，take的时候以这个路径能获取到对应的流
	 */
	String upload(InputStream is, String name);

	/**
	 * 
	 * 根据流路径 获取流
	 * 
	 * 
	 * @param path
	 * @return
	 */
	InputStream take(String path);
	
	/**
	 * 
	 * 删除流
	 * 
	 * 
	 * @param path
	 */
	void remove(String path);
}
