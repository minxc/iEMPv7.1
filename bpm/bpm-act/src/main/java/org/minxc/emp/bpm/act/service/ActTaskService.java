package org.minxc.emp.bpm.act.service;

import java.util.Collection;
import java.util.Map;
import org.activiti.engine.delegate.DelegateTask;

public interface ActTaskService {
	public DelegateTask getByTaskId(String var1);

	public void save(DelegateTask var1);

	public void completeTask(String var1);

	public void completeTask(String var1, String... var2);

	public void completeTaskOnly(String var1);

	public Object getVariable(String var1, String var2);

	public Object getVariableLocal(String var1, String var2);

	public Map<String, Object> getVariables(String var1);

	public Map<String, Object> getVariables(String var1, Collection<String> var2);

	public Map<String, Object> getVariablesLocal(String var1);

	public Map<String, Object> getVariablesLocal(String var1, Collection<String> var2);

	public void setVariable(String var1, String var2, Object var3);

	public void setVariableLocal(String var1, String var2, Object var3);

	public void setVariables(String var1, Map<String, ? extends Object> var2);

	public void setVariablesLocal(String var1, Map<String, ? extends Object> var2);
}