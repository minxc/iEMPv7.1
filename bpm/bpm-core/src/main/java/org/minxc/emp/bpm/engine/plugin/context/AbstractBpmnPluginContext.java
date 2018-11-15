package org.minxc.emp.bpm.engine.plugin.context;

import com.alibaba.fastjson.JSON;

import org.minxc.emp.bpm.api.engine.plugin.context.BpmnPluginContext;
import org.minxc.emp.bpm.api.engine.plugin.def.BpmnPluginDef;
import org.minxc.emp.core.util.BeanUtils;
import org.minxc.emp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBpmnPluginContext<T extends BpmnPluginDef> implements BpmnPluginContext<T> {
	private static final long serialVersionUID = 1L;
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	private T bG;
	protected int bH = 100;

	public T getBpmPluginDef() {
		return this.bG;
	}

	public void setBpmPluginDef(T bpmPluginDef) {
		this.bG = bpmPluginDef;
	}

	protected abstract T parseFromJson(JSON var1);

	public JSON getJson() {
		return (JSON) JSON.toJSON(this.bG);
	}

	public T parse(JSON json) {
		T def = this.parseFromJson(json);
		this.setBpmPluginDef(def);
		return this.bG;
	}

	public T parse(String json) {
		if (StringUtil.isEmpty((String) json)) {
			return null;
		}
		JSON j = (JSON) JSON.parse((String) json);
		return this.parse(j);
	}

	public String getType() {
		return StringUtil.lowerFirst((String) this.getClass().getSimpleName().replaceAll("PluginContext", ""));
	}

	public String persistnce(String defId) {
		String msg = null;
		if (BeanUtils.isEmpty((Object) this.getJson())) {
			msg = "清空改插件";
		}
		return msg;
	}

	public Integer getSn() {
		return this.bH;
	}

	public int compareTo(BpmnPluginContext pluginContext) {
		if (this.bH == pluginContext.getSn()) {
			return 0;
		}
		if (this.bH > pluginContext.getSn()) {
			return 1;
		}
		return 0;
	}
}