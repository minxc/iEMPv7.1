package org.minxc.emp.system.impl.manager;

import java.io.InputStream;

import org.minxc.emp.common.manager.Manager;
import org.minxc.emp.system.impl.model.UploadedFileEntity;

/**
 * 系统附件 Manager处理接口
 */
public interface FileManager extends Manager<String, UploadedFileEntity>{
	
	/**
	 * 
	 * 上传附件
	 * 	
	 * @param is
	 * @param fileName
	 * @return
	 */
	UploadedFileEntity upload(InputStream is, String fileName);
	
	/**
	 * 
	 * 下载附件
	 * 返回流
	 * 	
	 * @param fileId
	 * @return
	 */
	InputStream download(String fileId);
	
	/**
	 * 
	 * 删除附件
	 * 包括流信息
	 * 	
	 * @param fileId
	 */
	void delete(String fileId);
	
}
