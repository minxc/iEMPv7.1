package org.minxc.emp.bpm.engine.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.ParameterizedType;

import org.minxc.emp.bpm.api.engine.plugin.def.BpmDef;
import org.minxc.emp.core.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseBpmnDefParser<T, D extends BpmDef> implements BpmnDefinitionParser<D> {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	public void parse(D def, JSONObject jsonConf) {
		Object args = null;
		if (jsonConf.containsKey((Object) this.getKey())) {
			String jsonStr = jsonConf.getString(this.getKey());
			args = this.parseDef(def, jsonStr);
			if (args == null) {
				args = this.isArray()
						? JSONArray.parseArray((String) jsonStr, this.getClazz())
						: JSON.parseObject((String) jsonStr, this.getClazz());
			}
			this.setDefParam(def, args);
		}
		this.validate(args);
		this.JSONAmend(def, args, (JSON) jsonConf);
	}

	public Object parseDef(D def, String conf) {
		return null;
	}

	public Class<T> getClazz() {
		return (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public boolean isArray() {
		return false;
	}

	public String validate(Object o) {
		if (o != null) {
			ValidateUtil.validate((Object) o);
		}
		return null;
	}

	public void JSONAmend(D bpmdef, Object args, JSON configJson) {
	}

	public String getN() {
		return "asdf2dds";
	}
}