
/**  

* @Title: StatusCode.java 

* @Package org.minxc.emp.core.api 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月24日 上午12:35:32 

* @version V1.0  

*/ 

package org.minxc.emp.core.api.status;


/**      
* 项目名称：core-api   
* 类名称：StatusCode   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年8月24日 上午12:35:32   
* 修改人：Xianchang.min   
* 修改时间：2018年8月24日 上午12:35:32   
* 修改备注：   
* @version  1.0  
**/

public interface StatusCode {
	
	/**
     * 状态码
     *
     * @return
     */
    public String getCode();

    /**
     * 异常信息
     *
     * @return
     */
    public String getDescription();

    /**
     * 应用编码
     *
     * @return
     */
    public String getApplication();
    
    
    /**
     * 
    
    * @Title: getTenant 
    
    * @Description: 租户信息
    
    * @param @return    设定文件 
    
    * @return String    返回类型 
    
    * @throws
     */
    public String getTenant();
}
