package org.minxc.emp.basis.api.constant;

/**
 * @author Xianchang.min
 */
public enum RightsObjectConstants {
	
	FLOW("FLOW","流程定义"),
	WORKBENCH("WORKBENCH","工作台");
	
	private String key;
	private String label;
	
	RightsObjectConstants(String key,String label){
		this.key = key;
		this.label = label;
	}
	public String key(){
		return key;		
	}
	public String label(){
		return label;		
	}	
	
	public static RightsObjectConstants getByKey(String key){
		for (RightsObjectConstants rights : RightsObjectConstants.values()) {
			if(rights.key.equals(key)){
				return rights;
			}
		}
		throw new RuntimeException(String.format(" key [%s]所对应的RightsObjectConstants不存在常量定义，请检查！",key));
	}
}
