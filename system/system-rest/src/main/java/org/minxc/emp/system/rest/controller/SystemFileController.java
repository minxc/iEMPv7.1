package org.minxc.emp.system.rest.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.minxc.emp.common.db.model.page.PageJson;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.api.query.QueryFilter;
import org.minxc.emp.core.api.response.impl.ResultMessage;
import org.minxc.emp.core.util.FileUtil;
import org.minxc.emp.core.util.ZipUtil;
import org.minxc.emp.core.util.time.DateUtil;
import org.minxc.emp.system.impl.manager.FileManager;
import org.minxc.emp.system.impl.model.UploadedFileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;

import net.lingala.zip4j.core.ZipFile;

//import net.lingala.zip4j.core.ZipFile;

/**
 * 上传附件的controller
 */
@RestController
@RequestMapping("/sys/sysFile/")
public class SystemFileController extends GenericController {
	
	@Autowired
	FileManager sysFileManager;

	/**
	 * 
	 * 
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ErrorCatching(value = "上传失败")
	public ResultMessage<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
		UploadedFileEntity sysFile = sysFileManager.upload(file.getInputStream(), file.getOriginalFilename());
		return getSuccessResult(sysFile.getId(), "上传成功");
	}

	/**
	 * 
	 * 
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	@ErrorCatching(value = "下载失败")
	public ResponseEntity<byte[]> download(@RequestParam("fileId") String fileId) throws Exception {
		UploadedFileEntity sysFile = sysFileManager.get(fileId);

		HttpHeaders headers = new HttpHeaders();
		String downloadFileName = new String(sysFile.getName().getBytes("UTF-8"), "iso-8859-1");
		headers.setContentDispositionFormData("attachment", downloadFileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<>(IOUtils.toByteArray(sysFileManager.download(fileId)), headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "zip", method = RequestMethod.GET)
	@ErrorCatching(value = "打包失败")
	public ResponseEntity<byte[]> zip(@RequestParam("fileIds") String fileIds) throws Exception {
		ArrayList<File> sourceFileList = new ArrayList<>();
		for (String id : fileIds.split(",")) {
			UploadedFileEntity sysFile = sysFileManager.get(id);
			sourceFileList.add(FileUtil.inputstream2file(sysFileManager.download(id),new File(sysFile.getName())));
		}

		String zipName = DateUtil.getCurrentTime("yyyyMMddHHmmss") + ".zip";
		File file = ZipUtil.zip(sourceFileList, new ZipFile(new File(zipName)));
		HttpHeaders headers = new HttpHeaders();
		String downloadFileName = new String(zipName.getBytes("UTF-8"), "iso-8859-1");
		headers.setContentDispositionFormData("attachment", downloadFileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		byte[] bs = FileUtils.readFileToByteArray(file);
		file.delete();// 删除临时zip文件
		return new ResponseEntity<>(bs, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "del")
	@ErrorCatching(value = "删除失败")
	public ResultMessage<String> del(@RequestParam("fileId") String fileId) throws Exception {
		sysFileManager.delete(fileId);
		return getSuccessResult("删除成功");
	}
	
    @RequestMapping("listJson")
    public PageJson listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QueryFilter queryFilter = getQueryFilter(request);
        Page<UploadedFileEntity> pageList = (Page<UploadedFileEntity>) sysFileManager.query(queryFilter);
        return new PageJson(pageList);
    }
}
