/**
 * 
 */
package org.minxc.emp.windows.service;

import java.util.Date;

/**      
* 项目名称：windows-service   
* 类名称：JavaWindowsService   
* 类   
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 上午11:58:36   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 上午11:58:36   
* 修改备注：   
* @version  1.0  
**/

public class JavaWindowsService implements Runnable{

private boolean serviceStatu = true;
	
	public synchronized void setServiceStatu(boolean serviceStatu){
		this.serviceStatu = serviceStatu;
	}
	
	private synchronized boolean getServiceStatu(){
		return serviceStatu;
	}
	
	@Override
	public void run() {
		try {
			while(getServiceStatu()){
				System.out.println("[" + new Date() + "]服务运行中...");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
 
	}


}
