package org.minxc.emp.bpm.act.service;

import java.util.Collection;
import java.util.Map;

public interface ActInstanceService {
	public String startProcessInstance(String var1, String var2, Map<String, Object> var3);

	public String startProcessInstance(String var1, String var2, Map<String, Object> var3, String... var4);

	public Map<String, Object> getVariables(String var1);

	public void setVariable(String var1, String var2, Object var3);

	public void setVariableLocal(String var1, String var2, Object var3);

	public void setVariables(String var1, Map<String, ? extends Object> var2);

	public void setVariablesLocal(String var1, Map<String, ? extends Object> var2);

	public void removeVariable(String var1, String var2);

	public void removeVariableLocal(String var1, String var2);

	public void removeVariables(String var1, Collection<String> var2);

	public void removeVariablesLocal(String var1, Collection<String> var2);

	public boolean hasVariableLocal(String var1, String var2);

	public Object getVariableLocal(String var1, String var2);

	public boolean hasVariable(String var1, String var2);

	public Object getVariable(String var1, String var2);

	public Map<String, Object> getVariablesLocal(String var1, Collection<String> var2);

	public Map<String, Object> getVariablesLocal(String var1);

	public Map<String, Object> getVariables(String var1, Collection<String> var2);

	public void endProcessInstance(String var1);

	public void activateProcessInstanceById(String var1);

	public void suspendProcessInstanceById(String var1);

	public void deleteProcessInstance(String var1, String var2);

	public Object getSuperVariable(String var1, String var2);
}