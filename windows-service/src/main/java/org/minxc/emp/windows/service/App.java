/**
 * 
 */
package org.minxc.emp.windows.service;

import java.util.Date;

/**      
* 项目名称：windows-service   
* 类名称：App   
* 类  Windows服务入口类
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 上午11:59:40   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 上午11:59:40   
* 修改备注：   
* @version  1.0  
**/

public class App {

	private static Thread thread = null;
	private static JavaWindowsService service = null;
	
	public static void stopService(String[] args){
		service.setServiceStatu(false);
	}
	
	public static void startService(String[] args){
		service = new JavaWindowsService();
		thread = new Thread(service);
		try{
			// 将服务线程设定为用户线程
			thread.setDaemon(false);
			if(!thread.isDaemon()){
				System.out.println("[" + new Date() + "]成功设定为用户线程！");
			}
			thread.start();
		}catch(SecurityException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		startService(args);
	}


}
