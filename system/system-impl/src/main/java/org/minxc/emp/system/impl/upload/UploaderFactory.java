package org.minxc.emp.system.impl.upload;

import java.util.Map;
import java.util.Map.Entry;

import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.PropertiesUtil;

/**
 * 描述：上传器工厂
 */
public class UploaderFactory {
	private UploaderFactory() {

	}

	/**
	 * <pre>
	 * 获取上传器
	 * </pre>
	 * 
	 * @param type
	 * @return
	 */
	public static IUploader getUploader(String type) {
		Map<String, IUploader> map = AppContextUtil.getImplInstance(IUploader.class);
		for (Entry<String, IUploader> entry : map.entrySet()) {
			if (entry.getValue().type().equals(type)) {
				return entry.getValue();
			}
		}
		throw new BusinessException(String.format("找不到类型[%s]的上传器的实现类", type));
	}

	/**
	 * <pre>
	 * 返回默认的上传器
	 * </pre>
	 * 
	 * @return
	 */
	public static IUploader getDefault() {
		return getUploader(PropertiesUtil.getProperty("uploader.default"));
	}
}
