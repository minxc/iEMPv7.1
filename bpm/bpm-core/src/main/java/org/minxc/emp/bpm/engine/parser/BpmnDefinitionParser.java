package org.minxc.emp.bpm.engine.parser;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmDef;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

interface BpmnDefinitionParser<D extends BpmDef> {
	public Object parseDef(D var1, String var2);

	public void parse(D var1, JSONObject var2);

	public String getKey();

	public Class getClazz();

	public boolean isArray();

	public String validate(Object var1);

	public void setDefParam(D var1, Object var2);

	public void JSONAmend(D var1, Object var2, JSON var3);
}