package org.minxc.emp.biz.core.executor.assemblyval;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.constant.ColumnType;
import com.dstz.base.core.util.time.DateFormatUtil;

import java.util.Date;
import java.util.Map;

import org.minxc.emp.biz.core.api.model.IBusinessColumn;
import org.minxc.emp.biz.core.executor.assemblyval.AssemblyValExecuteChain;
import org.minxc.emp.biz.core.executor.assemblyval.AssemblyValParam;
import org.minxc.emp.biz.core.model.BusinessData;
import org.springframework.stereotype.Service;

@Service
public class AssemblyValTypeExecutor extends AssemblyValExecuteChain {

	public int getSn() {
		return 0;
	}

	@Override
	protected void run(AssemblyValParam param) {
		BusinessData businessData = param.getBusinessData();
		JSONObject data = param.getData();
		for (Map.Entry<String, Object> entry : businessData.getData().entrySet()) {
			IBusinessColumn column = businessData.getBusTableRel().getTable().getColumnByKey(entry.getKey());
			if (ColumnType.DATE.equalsWithKey(column.getType()) && entry.getValue() != null) {
				JSONObject config = JSON.parseObject(column.getCtrl().getConfig());
				data.put(column.getKey(), DateFormatUtil.format((Date) entry.getValue(), config.getString("format")));
			} else {
				data.put(entry.getKey(), entry.getValue());
			}
		}
	}
}