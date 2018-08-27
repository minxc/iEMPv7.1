package org.minxc.emp.system.impl.upload;

/**
 * 描述：oss上传器
 */
public abstract class OssUploader extends AbstractUploader {

	@Override
	public String type() {
		return "oss";
	}

}
