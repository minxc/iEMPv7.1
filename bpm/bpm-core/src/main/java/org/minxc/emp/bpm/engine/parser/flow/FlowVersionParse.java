package org.minxc.emp.bpm.engine.parser.flow;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

import org.minxc.emp.bpm.engine.model.DefaultBpmProcessDef;
import org.minxc.emp.bpm.engine.model.DefaultBpmVariableDef;
import org.minxc.emp.bpm.engine.parser.flow.AbsFlowParse;
import org.minxc.emp.core.util.EncryptUtil;
import org.minxc.emp.core.util.PropertiesUtil;
import org.minxc.emp.core.util.StringUtil;
import org.minxc.emp.core.util.time.DateFormatUtil;
import org.springframework.stereotype.Component;

@Component
public class FlowVersionParse extends AbsFlowParse<DefaultBpmVariableDef> {
	private static boolean bD = false;
	private static String bE = "b";

	public void parse(DefaultBpmProcessDef def, JSONObject flowConf) {
		if (bD) {
			flowConf.put("v", bE);
			if (bE.equals("b")) {
				this.a(flowConf);
			}
			return;
		}
		String key = PropertiesUtil.getProperty(String.format("%s.%s", "s", "k"));
		bE = this.f(key);
		this.g(bE);
		if (bE.equals("b")) {
			this.a(flowConf);
		}
		bD = true;
		flowConf.put("v", (Object) bE);
	}

	private String f(String key) {
		try {
			String str = EncryptUtil.decrypt((String) key);
			if (StringUtil.isEmpty((String) str)) {
				return bE;
			}
			String[] msg = str.split("_");
			if (msg.length != 3) {
				return bE;
			}
			Date date = DateFormatUtil.parse((String) msg[2]);
			if (date.before(new Date())) {
				return bE;
			}
			return msg[0];
		} catch (Exception e) {
			return bE;
		}
	}

//	public static void main(String[] args) throws Exception {
//		String code = "senior_agileBpm敏捷工作流_2019-5-13";
//		System.out.println(EncryptUtil.encrypt((String) code));
//		System.out.println(EncryptUtil.decrypt(
//				(String) "5392537a203272132719aad01638fc99d0258d67e6cb7c29b76672a4cb47c18f0a16f85c569009d02da55598643b4169"));
//	}

	private void a(JSONObject flowConf) {
	}

	private void g(String string) {
		new Thread(new Runnable() {

			@Override
			public void run() {
			}
		}).run();
	}

	public String getKey() {
		return null;
	}

	public void setDefParam(DefaultBpmProcessDef bpmdef, Object object) {
	}

}