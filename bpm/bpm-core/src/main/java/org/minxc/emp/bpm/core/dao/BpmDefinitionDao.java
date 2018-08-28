package org.minxc.emp.bpm.core.dao;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.dao.BaseDao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.bpm.core.model.BpmDefinition;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmDefinitionDao extends BaseDao<String, BpmDefinition> {
	public BpmDefinition getMainByDefKey(String var1);

	public void updateActResourceEntity(@Param(value = "deploymentId") String var1,
			@Param(value = "resName") String var2, @Param(value = "bpmnBytes") byte[] var3);

	public List<BpmDefinition> getByKey(String var1);

	public BpmDefinition getMainDefByActModelId(String var1);

	public BpmDefinition getByActDefId(String var1);

	public void updateToMain(String var1);

	public List<BpmDefinition> getMyDefinitionList(QueryFilter var1);
}