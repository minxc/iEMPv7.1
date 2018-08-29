package org.minxc.emp.biz.core.executor.parseval;


import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.biz.api.model.IBusinessColumn;
import org.minxc.emp.biz.core.executor.parseval.ParseValExecuteChain;
import org.minxc.emp.biz.core.executor.parseval.ParseValParam;
import org.springframework.stereotype.Service;

@Service
public class ParseValTypeExecutor extends ParseValExecuteChain {
	
	@Override
	protected void run(ParseValParam param) {
		String key = param.getKey();
		Object value = param.getValue();
		if (value == null || StringUtils.isEmpty((String) value.toString())) {
			return;
		}
		IBusinessColumn column = param.getBusTableRel().getTable().getColumnByKey(key);
		if (column == null) {
			param.getData().put(key, value);
			return;
		}
		param.getData().put(column.getKey(), column.value(value.toString()));
	}
	
	@Override
	public int getSequanceNum() {
		return 0;
	}

}