package org.minxc.emp.system.impl.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.minxc.emp.core.api.exception.BusinessException;
import org.springframework.stereotype.Service;

import com.minxc.emp.core.util.FileUtil;
import com.minxc.emp.core.util.PropertiesUtil;
import com.minxc.emp.core.util.time.DateUtil;


/**
 * 描述：普通的上传器
 * 上传到服务器的某个文件夹中
 * 每次上传时会自动放在当前日期yyyyMMdd的目录下
 */
@Service
public class OrdinaryUploader extends AbstractUploader {

	@Override
	public String type() {
		return "ordinary";
	}

	@Override
	public String upload(InputStream is, String name) {
		FileUtil.createFolderFile(getPath(name));
		FileUtil.writeFile(getPath(name), is);
		return getPath(name);
	}

	@Override
	public InputStream take(String path) {
		try {
			return new FileInputStream(new File(path));
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void remove(String path) {
		FileUtil.deleteFile(path);
	}

	private String getPath(String name) {
		return PropertiesUtil.getProperty("uploader.ordinary.path") + DateUtil.getCurrentTime("yyyyMMdd") + File.separator + name;
	}

}
