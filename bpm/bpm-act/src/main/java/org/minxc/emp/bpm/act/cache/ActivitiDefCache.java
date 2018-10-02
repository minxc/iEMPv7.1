package org.minxc.emp.bpm.act.cache;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.impl.persistence.deploy.DeploymentCache;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.minxc.emp.core.cache.Cache;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.FileUtil;
import org.springframework.stereotype.Component;

@Component
public class ActivitiDefCache implements DeploymentCache<ProcessDefinitionEntity> {
	
	public static ActivitiDefCache a = null;
	private ThreadLocal<Map<String, ProcessDefinitionEntity>> b = new ThreadLocal<>();
	@Resource
	Cache c;

	public static void clearLocal() {
		ActivitiDefCache cache = (ActivitiDefCache) AppContextUtil.getBean(ActivitiDefCache.class);
		cache.clearProcessCache();
	}

	public static void clearByDefId(String actDefId) {
		if (a == null) {
			a = (ActivitiDefCache) AppContextUtil.getBean(ActivitiDefCache.class);
		}
		a.clearProcessDefinitionEntity(actDefId);
		a.clearProcessCache();
	}

	private void clearProcessDefinitionEntity(String defId) {
		this.remove(defId);
		this.b.remove();
	}

	private void clearProcessCache() {
		this.b.remove();
	}

	private void setThreadLocalDef(ProcessDefinitionEntity processEnt) {
		if (this.b.get() == null) {
			HashMap<String, ProcessDefinitionEntity> map = new HashMap<String, ProcessDefinitionEntity>();
			map.put(processEnt.getId(), processEnt);
			this.b.set(map);
		} else {
			Map<String, ProcessDefinitionEntity> map = this.b.get();
			map.put(processEnt.getId(), processEnt);
		}
	}

	private ProcessDefinitionEntity getThreadLocalDef(String processDefinitionId) {
		if (this.b.get() == null) {
			return null;
		}
		Map<String, ProcessDefinitionEntity> map = this.b.get();
		if (!map.containsKey(processDefinitionId)) {
			return null;
		}
		return map.get(processDefinitionId);
	}

	public ProcessDefinitionEntity get(String id) {
		ProcessDefinitionEntity ent = (ProcessDefinitionEntity) this.c.getByKey(id);
		if (ent == null) {
			return null;
		}
		ProcessDefinitionEntity cloneEnt = null;
		try {
			cloneEnt = (ProcessDefinitionEntity) FileUtil.cloneObject((Object) ent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProcessDefinitionEntity p = this.getThreadLocalDef(id);
		if (p == null) {
			this.setThreadLocalDef(cloneEnt);
		}
		p = this.getThreadLocalDef(id);
		return p;
	}

	public void add(String id, ProcessDefinitionEntity object) {
		this.c.add(id, (Object) object);
	}

	public void remove(String id) {
		this.c.delByKey(id);
	}

	public void clear() {
		this.c.clearAll();
	}
}