package org.minxc.emp.common.rest.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.minxc.emp.base.core.util.FileUtil;
//import org.minxc.emp.base.core.util.MyX509TrustManager;
//import org.minxc.emp.base.core.util.StringUtil;
//import org.minxc.emp.base.core.util.ZipUtil;
import org.minxc.emp.core.util.FileUtil;
import org.minxc.emp.core.util.MinxcX509TrustManager;
import org.minxc.emp.core.util.ZipUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;


public class HttpUtil {

    /**
     * 下载文件。
     *
     * @param request
     * @param response
     * @param fullPath 下载文件路径
     * @param fileName 下载文件名
     * @throws IOException void
     */
    private static void downLoadFile(HttpServletRequest request, HttpServletResponse response, String fullPath, String fileName) throws IOException {
        OutputStream outp = response.getOutputStream();
        FileInputStream in = null;
        File file = new File(fullPath);
        if (file.exists()) {
            response.setContentType("application/x-download");
            if (System.getProperty("file.encoding").equals("GBK")) {
                response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes(), "ISO-8859-1") + "\"");
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\"");
            }
            try {
                in = new FileInputStream(fullPath);
                IOUtils.copy(in, outp);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (outp != null) {
                    outp.close();
                    outp = null;
                    response.flushBuffer();
                }
            }
        } else {
            outp.write("文件不存在!".getBytes("utf-8"));
        }
    }

    /**
     * <pre>
     * 压缩多个文件到一个zip下然后提供到页面下载
     * 目前常用于导出xml
     * 里面进行了1：写一个临时文件；2：打包；3：导出打包好的文件；4：删除临时文件
     * </pre>
     *
     * @param request
     * @param response
     * @param fileContentMap ：{a:a的内容,b:b的内容,...}
     * @param zipName        :压缩包的名字
     * @throws Exception void
     * @throws
     * @since 1.0.0
     */
    public static void downLoadFile(HttpServletRequest request, HttpServletResponse response, Map<String, String> fileContentMap, String zipName) throws Exception {
        String zipPath = (FileUtil.getWebRootPath() + "/attachFiles/tempZip/" + zipName).replace("/", File.separator);
        for (Map.Entry<String, String> ent : fileContentMap.entrySet()) {
            String fileName = ent.getKey();
            String content = ent.getValue();

            String filePath = zipPath + File.separator + fileName;
            FileUtil.writeFile(filePath, content);
        }
        // 打包
//        ZipUtil.zip(zipPath, true);   TODO:更新的内容，需要注意可能会有bug
        ZipUtil.zip(zipPath);
        // 导出
        HttpUtil.downLoadFile(request, response, zipPath + ".zip", zipName + ".zip");
        // 删除导出的文件
        FileUtil.deleteFile(zipPath + ".zip");
    }

    /**
     * <pre>
     * 压缩一个文件到压缩包下然后提供到页面下载
     * 目前常用于导出xml
     * 里面进行了1：写一个临时文件；2：打包；3：导出打包好的文件；4：删除临时文件
     * </pre>
     *
     * @param request
     * @param response
     * @param content  :要导出的文本
     * @param fileName ：文件名称
     * @param zipName  ：压缩包名称
     * @throws Exception void
     */
    public static void downLoadFile(HttpServletRequest request, HttpServletResponse response, String content, String fileName, String zipName) throws Exception {
        Map<String, String> fileContentMap = new HashMap<String, String>();
        fileContentMap.put(fileName, content);
        downLoadFile(request, response, fileContentMap, zipName);
    }


    /**
     * 发送请求。
     *
     * @param url           URL地址
     * @param params        发送参数
     * @param requestMethod GET,POST
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public static String sendHttpsRequest(String url, String params,
                                          String requestMethod) {
        HttpsURLConnection conn;
        String str = null;
        try {
            conn = getHttpsConnection(url);
            conn.setRequestMethod(requestMethod);

            conn.setDoInput(true);
            conn.setDoOutput(true);

            if (StringUtils.isNotEmpty(params)) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(params.getBytes("utf-8"));
                outputStream.close();
            }
            str = getOutPut(conn);
        } catch (KeyManagementException e) {
            throw new RuntimeException("远程服务器请求失败！" + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("远程服务器请求失败！" + e.getMessage(), e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException("远程服务器请求失败！" + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("远程服务器请求失败！" + e.getMessage(), e);
        }

        return str;
    }

    /**
     * 获取https连接。
     *
     * @param accessUrl
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public static HttpsURLConnection getHttpsConnection(String accessUrl)
            throws KeyManagementException, NoSuchAlgorithmException,
            NoSuchProviderException, IOException {
        URL url = new URL(accessUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url
                .openConnection();

        TrustManager[] tm = {new MinxcX509TrustManager()};

        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        connection.setSSLSocketFactory(ssf);
        return connection;
    }


    /**
     * 读取返回数据。
     *
     * @param conn
     * @return
     * @throws IOException
     */
    public static String getOutPut(HttpsURLConnection conn) throws IOException {
        InputStream inputStream = conn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer buffer = new StringBuffer();
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        conn.disconnect();
        return buffer.toString();
    }


    /**
     * 根据url取得数据，支持gzip类网站
     *
     * @param url
     * @param charset
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String getContentByUrl(String url, String charset) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpget);


        if (StringUtils.isEmpty(charset)) {
            String defaultCharset = "iso-8859-1";
            Header contentTypeHeader = response.getFirstHeader("Content-Type");
            String contentType = contentTypeHeader.getValue().toLowerCase();
            if (contentType.indexOf("gbk") > -1 || contentType.indexOf("gb2312") > -1 || contentType.indexOf("gb18030") > -1) {
                defaultCharset = "gb18030";
            } else if (contentType.indexOf("utf-8") > -1) {
                defaultCharset = "utf-8";
            } else if (contentType.indexOf("big5") > -1) {
                defaultCharset = "big5";
            }
            charset = defaultCharset;
        }
        Header contentEncoding = response.getFirstHeader("Content-Encoding");
        StatusLine line = response.getStatusLine();
        if (line.getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            InputStream is = null;
            if (contentEncoding != null && contentEncoding.getValue().toLowerCase().equals("gzip")) {
                is = new GZIPInputStream(entity.getContent());
            } else {
                is = entity.getContent();
            }
            String str = FileUtil.inputStream2String(is, charset);
            is.close();
            return str;

        }
        return "";
    }

    public static String sendData(String url, String data) {
        return sendData(url, data, "utf-8");
    }

    /**
     * 发送数据到指定的URL并读取返回结果。
     *
     * @param url
     * @param data
     * @return
     */
    public static String sendData(String url, String data, String charset) {
        URL uRL;
        URLConnection conn;
        BufferedReader bufferedReader = null;
        try {
            uRL = new URL(url);
            conn = uRL.openConnection();
            conn.setDoOutput(true);
            if (StringUtils.isNotEmpty(data)) {
                OutputStream stream = conn.getOutputStream();
                stream.write(data.getBytes(charset));
                stream.flush();
                stream.close();
            }


            // Get the response
            bufferedReader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }

            bufferedReader.close();

            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}
