package org.minxc.emp.system.impl.model;

import org.minxc.emp.core.impl.model.AbstractCommonModel;

/**
 * 系统附件信息
 */
public class UploadedFileEntity extends AbstractCommonModel {
	
	private static final long serialVersionUID = 1094360923381261339L;
	/**
	 * 附件名
	 */
	private String name;
	/**
	 * 
	 * 这附件用的是上传器
	 * 具体类型可以看 IUploader 的实现类
	 * 
	 */
	private String uploader;
	/**
	 * 
	 * 路径，这个路径能从上传器中获取到对应的附件内容
	 * 所以也不一定是路径，根据不同上传器会有不同值
	 * 
	 */
	private String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
