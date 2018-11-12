
/**  

* @Title: ZipUtil.java 

* @Package com.minxc.emp.core.util 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月24日 下午12:59:35 

* @version V1.0  

*/ 

package org.minxc.emp.core.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.compress.archivers.zip.Zip64Mode;
//import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
//import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
//import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;


/**      
* 项目名称：core-impl   
* 类名称：ZipUtil   
* 类 压缩工具类 
* 创建人：Xianchang.min   
* 创建时间：2018年8月24日 下午12:59:35   
* 修改人：Xianchang.min   
* 修改时间：2018年8月24日 下午12:59:35   
* 修改备注：   
* @version  1.0  
**/

@Slf4j
public class ZipUtil {

//    private static final String DEFAULT_CHARSET = "UTF-8";
//
//    public static final int BUFFER_SIZE = 1024;
//
//    /**
//     * 解压 zip 文件
//     *
//     * @param zipFile zip 压缩文件
//     * @param destDir zip 压缩文件解压后保存的目录
//     * @return 返回 zip 压缩文件里的文件名的 list
//     * @throws Exception
//     */
//    public static List<String> unZip(File zipFile, String destDir) throws Exception {
//
//        // 如果 destDir 为 null, 空字符串, 或者全是空格, 则解压到压缩文件所在目录
//        if (StringUtils.isBlank(destDir)) {
//            destDir = zipFile.getParent();
//        }
//
//        destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;
//        ZipArchiveInputStream is = null;
//        List<String> fileNames = new ArrayList<String>();
//
//        try {
//            is = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipFile), BUFFER_SIZE));
//            ZipArchiveEntry entry = null;
//
//            while ((entry = is.getNextZipEntry()) != null) {
//                fileNames.add(entry.getName());
//
//                if (entry.isDirectory()) {
//                    File directory = new File(destDir, entry.getName());
//                    directory.mkdirs();
//                } else {
//                    OutputStream os = null;
//                    try {
//                        os = new BufferedOutputStream(new FileOutputStream(new File(destDir, entry.getName())), BUFFER_SIZE);
//                        IOUtils.copy(is, os);
//                    } finally {
//                        IOUtils.closeQuietly(os);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        } finally {
//            IOUtils.closeQuietly(is);
//        }
//
//        return fileNames;
//    }
//
//    /**
//     * 解压 zip 文件
//     *
//     * @param zipfile zip 压缩文件的路径
//     * @param destDir zip 压缩文件解压后保存的目录
//     * @return 返回 zip 压缩文件里的文件名的 list
//     * @throws Exception
//     */
//    public static List<String> unZip(String zipfile, String destDir) throws Exception {
//        File zipFile = new File(zipfile);
//        return unZip(zipFile, destDir);
//    }
//
//
//    /**
//     * 判断文件名是否以.zip为后缀
//     *
//     * @param fileName 需要判断的文件名
//     * @return 是zip文件返回true, 否则返回false
//     */
//    public static boolean isEndWithZip(String fileName) {
//        boolean flag = false;
//        if (fileName != null && !"".equals(fileName.trim())) {
//            if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
//                flag = true;
//            }
//        }
//        return flag;
//    }
//
//
//    /**
//     * 把文件压缩成zip格式
//     *
//     * @param files       需要压缩的文件
//     * @param zipFilePath 压缩后的zip文件路径   ,如"D:/test/aa.zip";
//     */
//    public static void zip(File[] files, String zipFilePath) {
//        if (files != null && files.length > 0) {
//            if (isEndWithZip(zipFilePath)) {
//                ZipArchiveOutputStream zaos = null;
//                try {
//                    File zipFile = new File(zipFilePath);
//                    zaos = new ZipArchiveOutputStream(zipFile);
//                    //Use Zip64 extensions for all entries where they are required
//                    zaos.setUseZip64(Zip64Mode.AsNeeded);
//
//                    //将每个文件用ZipArchiveEntry封装
//                    //再用ZipArchiveOutputStream写到压缩文件中
//                    for (File file : files) {
//                        if (file != null) {
//                            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
//                            zaos.putArchiveEntry(zipArchiveEntry);
//                            InputStream is = null;
//                            try {
//                                is = new BufferedInputStream(new FileInputStream(file));
//                                byte[] buffer = new byte[1024 * 5];
//                                int len = -1;
//                                while ((len = is.read(buffer)) != -1) {
//                                    //把缓冲区的字节写入到ZipArchiveEntry
//                                    zaos.write(buffer, 0, len);
//                                }
//                                //Writes all necessary data for this entry.
//                                zaos.closeArchiveEntry();
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            } finally {
//                                if (is != null)
//                                    is.close();
//                            }
//
//                        }
//                    }
//                    zaos.finish();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    try {
//                        if (zaos != null) {
//                            zaos.close();
//                        }
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 压缩文件
//     *
//     * @param path
//     *            压缩文件\文件夹路径
//     * @param
//     *            压缩后是否删除原文件\文件夹
//     */
////    public static void zip(String path, Boolean isDelete) {
////
////       if(StringUtils.isNotEmpty(path)){
////           File[] files = FileUtil.getFiles(path);
////
////           zip(files)
////
////       }
////    }
//
//
//    public static void zip(String path) {
//        if(StringUtils.isNotEmpty(path)) {
//            File[] files = FileUtil.getFiles(path);
//            String zipFileName = path.substring(path.lastIndexOf(File.separator));
//            String  zipFilePath = path + File.separator + zipFileName + ".zip";
//            zip(files, zipFilePath);
//        }
//
//    }
//    
//    
	
	
	/**
	 * 压缩文件 （压缩后 删除原有的文件）
	 *
	 * @param path
	 *            压缩文件\文件夹路径
	 */
	public static void zip(String path) {
		zip(path, true);
	}

	public static void main(String[] args) throws Exception {
		String path = "D:\\IT\\file";
		/*
		 * zip(path, false); //unZipFile("d:/test.zip", "d:");
		 * System.out.println("main");
		 */
		ZipFile zipFile = new ZipFile(new File("D:\\IT\\file.zip"));
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		ArrayList sourceFileList = new ArrayList<>();
		sourceFileList.add(new File("D:\\IT\\file\\Koala.jpg"));
		sourceFileList.add(new File("D:\\IT\\file\\Penguins.jpg"));
		zipFile.createZipFile(sourceFileList, parameters);
		System.out.println("main");
	}

	/**
	 * 
	 * 把文件打包到zip文件中
	 * 
	 * 
	 * @param fileNames
	 *            要压缩的文件列表
	 * @param zipName
	 *            打包的文件
	 * @return 返回压缩zip文件
	 */
	public static File zip(List<String> fileNames, String zipName) {
		try {
			ArrayList<File> sourceFileList = new ArrayList<>();
			for (String fileName : fileNames) {
				sourceFileList.add(new File(fileName));
			}
			return zip(sourceFileList,new ZipFile(new File(zipName)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * 把文件打包到zip文件中
	 * 	
	 * @param sourceFileList
	 * @param zipName
	 * @return
	 */
	public static File zip(ArrayList<File> sourceFileList, ZipFile zipFile) {
		try {
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			zipFile.createZipFile(sourceFileList, parameters);
			return zipFile.getFile();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 压缩文件
	 *
	 * @param path
	 *            压缩文件\文件夹路径
	 * @param isDelete
	 *            压缩后是否删除原文件\文件夹
	 */
	public static void zip(String path, Boolean isDelete) {
		ZipFile zipFile = null;
		try {
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			File file = new File(path);
			if (file.isDirectory()) {
				zipFile = new ZipFile(new File(path + ".zip"));
				zipFile.setFileNameCharset("utf-8");
				zipFile.addFolder(path, parameters);
			} else {
				zipFile = new ZipFile(new File(path.split(".")[0] + ".zip"));
				zipFile.setFileNameCharset("utf-8");
				zipFile.addFile(file, parameters);
			}

			if (isDelete)
				FileUtil.deleteDir(file);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 压缩文件夹(加密)
	 *
	 * @param path
	 *            压缩文件\文件夹路径
	 * @param isDelete
	 *            压缩后是否删除原文件\文件夹
	 * @param password
	 *            加密密码
	 */
	public static void zipSetPass(String path, Boolean isDelete, String password) {
		ZipFile zipFile = null;
		try {
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			// 设置密码
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
			parameters.setPassword(password);
			File file = new File(path);
			if (file.isDirectory()) {
				zipFile = new ZipFile(new File(path + ".zip"));
				zipFile.setFileNameCharset("utf-8");
				zipFile.addFolder(path, parameters);
			} else {
				zipFile = new ZipFile(new File(path.split(".")[0] + ".zip"));
				zipFile.setFileNameCharset("utf-8");
				zipFile.addFile(file, parameters);
			}
			if (isDelete) {
				FileUtil.deleteDir(new File(path));
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 解压上传zip文件。
	 *
	 * @param multipartFile
	 * @param toPath
	 * @throws Exception
	 *             void
	 */
	public static void unZipFile(MultipartFile multipartFile, String toPath) throws Exception {
		// 转存文件
		String originalFilename = multipartFile.getOriginalFilename();
		String destPath = toPath + originalFilename;

		createFilePath(destPath, originalFilename);
		File file = new File(destPath);
		if (file.exists())
			file.delete();
		multipartFile.transferTo(file);
		ZipFile zipFile = new ZipFile(file);
		zipFile.setFileNameCharset("GBK"); // 设置文件名编码，在GBK系统中需要设置
		if (zipFile.isEncrypted())
			zipFile.setPassword("");
		if (!zipFile.isValidZipFile())
			throw new ZipException("压缩文件不合法,可能被损坏.");
		// 将文件抽出到解压目录(解压)
		zipFile.extractAll(toPath);
		// 删除解压文件
		FileUtil.deleteDir(file);
	}

	/**
	 * 解压压缩文件
	 *
	 * @param filePath
	 *            压缩文件路径
	 * @param toPath
	 *            解压到的文件夹路径
	 * @param password
	 *            密码 没有密码设置为""
	 */
	public static void unZip(String filePath, String toPath, String password) {
		try {
			unZipFile(new ZipFile(filePath), toPath, password);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解压压缩文件
	 *
	 * @param zipFile
	 * @param toPath
	 * @param password
	 */
	public static void unZipFile(ZipFile zipFile, String toPath, String password) {
		try {
			if (zipFile.isEncrypted())
				zipFile.setPassword(password);
			List<?> fileHeaderList = zipFile.getFileHeaders();
			for (Object o : fileHeaderList) {
				FileHeader fileHeader = (FileHeader) o;
				zipFile.extractFile(fileHeader, toPath);
			}
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解压文件。
	 *
	 * @param filePath
	 *            zip文件路径
	 * @param toPath
	 *            目录 void
	 */
	public static void unZipFile(String filePath, String toPath) {
		unZip(filePath, toPath, "");
	}

	/**
	 * 创建文件目录
	 *
	 * @param tempPath
	 * @param fileName
	 *            文件名称
	 * @return 文件的完整目录
	 */
	public static String createFilePath(String tempPath, String fileName) {
		File file = new File(tempPath);
		// 文件夹不存在创建
		if (!file.exists())
			file.mkdirs();
		return file.getPath() + File.separator + fileName;
	}

}