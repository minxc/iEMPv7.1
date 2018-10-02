package org.minxc.emp.common.db.dao.baseinterceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.minxc.emp.common.db.id.UniqueIdUtil;
import org.minxc.emp.core.api.model.CommonModel;
import org.minxc.emp.core.api.model.IdModel;
import org.minxc.emp.core.api.model.NewOrUpdateModel;
import org.minxc.emp.core.impl.model.AbstractCommonModel;
import org.minxc.emp.core.util.BeanUtils;

import java.util.Date;
import java.util.Properties;

/**
 * 更新设置更新人
 */
@Intercepts({
	        @Signature(type= Executor.class,method = "update",args = {MappedStatement.class,Object.class})
})
public class SaveInterceptor  implements Interceptor{

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		if(BeanUtils.isEmpty(args) || args.length < 2) {
			 return invocation.proceed();
		}
		
		Object param = args[1];
		MappedStatement statement = (MappedStatement) args[0];
		
		// 更新逻辑
		if(statement.getId().endsWith(".update")) {
			
			 if (param instanceof NewOrUpdateModel) {
				 NewOrUpdateModel model = (NewOrUpdateModel) param;
	            if (model.getUpdateTime() == null) {
	                model.setUpdateTime(new Date());
	            }
	            if(param instanceof CommonModel) {
					AbstractCommonModel baseModel = (AbstractCommonModel) param;
	            	baseModel.setVersion(baseModel.getVersion() + 1);
	            }
	        }
		}

		//新增逻辑
		else if(StringUtils.endsWithAny(statement.getId(), ".create", ".insertSelective")) {
			//为ID赋值
			if (param instanceof IdModel) {
				IdModel model = (IdModel) param;
	            if (model.getId() == null) {
	                model.setId(UniqueIdUtil.getSuid());
	            }
	        }
			//创建信息赋值
			if(param instanceof NewOrUpdateModel) {
				NewOrUpdateModel model = (NewOrUpdateModel) param;
	            if (model.getCreateTime() == null) {
	                model.setCreateTime(new Date());
	            }
	            if(model.getUpdateTime() == null){
	            	model.setUpdateTime(new Date());
				}
			}
		}
		
		// 批量新增
		
		 return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		
	}

}
