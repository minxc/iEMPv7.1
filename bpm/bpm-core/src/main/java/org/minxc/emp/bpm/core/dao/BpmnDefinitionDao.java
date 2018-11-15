package org.minxc.emp.bpm.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.minxc.emp.bpm.core.model.BpmnDefinitionImpl;
import org.minxc.emp.common.db.dao.CommonDao;
import org.minxc.emp.core.api.query.QueryFilter;

@Mapper
public interface BpmnDefinitionDao extends CommonDao<String, BpmnDefinitionImpl> {
	public BpmnDefinitionImpl getMainByDefKey(String var1);

	public void updateActResourceEntity(@Param(value = "deploymentId") String var1,
			@Param(value = "resName") String var2, @Param(value = "bpmnBytes") byte[] var3);

	public List<BpmnDefinitionImpl> getByKey(String var1);

	public BpmnDefinitionImpl getMainDefByActModelId(String var1);

	public BpmnDefinitionImpl getByActDefId(String var1);

	public void updateToMain(String var1);

	public List<BpmnDefinitionImpl> getMyDefinitionList(QueryFilter var1);
}