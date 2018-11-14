package org.minxc.emp.biz.core.executor.assemblyval;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.Map;

import org.minxc.emp.biz.api.model.BusinessColumn;
import org.minxc.emp.biz.core.model.BusinessDataImpl;
import org.minxc.emp.core.api.constant.ColumnType;
import org.minxc.emp.core.util.time.DateFormatUtil;
import org.springframework.stereotype.Service;

@Service
public class AssemblyValTypeExecutor extends AssemblyValExecuteChain {


	@Override
	protected void run(AssemblyValParam param) {
		BusinessDataImpl businessData = param.getBusinessData();
		JSONObject data = param.getData();
		for (Map.Entry<String, Object> entry : businessData.getData().entrySet()) {
			BusinessColumn column = businessData.getBusTableRel().getTable().getColumnByKey(entry.getKey());
			if (ColumnType.DATE.equalsWithKey(column.getType()) && entry.getValue() != null) {
				JSONObject config = JSON.parseObject(column.getCtrl().getConfig());
				data.put(column.getKey(), DateFormatUtil.format((Date) entry.getValue(), config.getString("format")));
			} else {
				data.put(entry.getKey(), entry.getValue());
			}
		}
	}

	@Override
	public int getSequanceNum() {
		return 0;
	}
}