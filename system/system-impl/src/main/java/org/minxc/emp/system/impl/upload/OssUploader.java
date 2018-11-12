package org.minxc.emp.system.impl.upload;

/**
 * oss上传器
 */
public abstract class OssUploader extends AbstractUploader {

	@Override
	public String type() {
		return "oss";
	}

}
