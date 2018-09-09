package org.minxc.emp.system.impl.manager.impl;

import java.io.InputStream;

import javax.annotation.Resource;

import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.system.impl.dao.SysFileDao;
import org.minxc.emp.system.impl.manager.SysFileManager;
import org.minxc.emp.system.impl.model.UploadedFileEntity;
import org.minxc.emp.system.impl.upload.IUploader;
import org.minxc.emp.system.impl.upload.UploaderFactory;
import org.springframework.stereotype.Service;


/**
 * 系统附件 Manager处理实现类
 */
@Service("sysFileManager")
public class SysFileManagerImpl extends CommonManager<String, UploadedFileEntity> implements SysFileManager {
	@Resource
	SysFileDao sysFileDao;

	@Override
	public UploadedFileEntity upload(InputStream is, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf('.'));
		String id = UniqueIdUtil.getSuid();

		// 1 先上传文件
		IUploader uploader = UploaderFactory.getDefault();
		String path = uploader.upload(is, id + ext);//以id为文件名保证不重复

		// 2 建立SysFile数据
		UploadedFileEntity sysFile = new UploadedFileEntity();
		sysFile.setId(id);
		sysFile.setName(fileName);
		sysFile.setUploader(uploader.type());
		sysFile.setPath(path);
		create(sysFile);

		return sysFile;
	}

	@Override
	public InputStream download(String fileId) {
		UploadedFileEntity sysFile = get(fileId);
		IUploader uploader = UploaderFactory.getUploader(sysFile.getUploader());
		return uploader.take(sysFile.getPath());
	}
	
	@Override
	public void delete(String fileId) {
		UploadedFileEntity sysFile = get(fileId);
//		IUploader uploader = UploaderFactory.getUploader(sysFile.getUploader());
//		uploader.remove(sysFile.getPath());
//		remove(fileId);
		//做逻辑删除
		sysFile.setDelete(true);
		update(sysFile);
	}
}
