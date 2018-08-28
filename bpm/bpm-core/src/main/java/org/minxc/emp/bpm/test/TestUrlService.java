package org.minxc.emp.bpm.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.PrintStream;

import org.minxc.emp.bpm.api.engine.action.cmd.ActionCmd;
import org.springframework.stereotype.Component;

@Component
public class TestUrlService {
	public void a(ActionCmd cmd) {
		String data = cmd.getBusData();
		JSONObject json = JSON.parseObject((String) data);
		System.err.println((Object) json);
		cmd.setBusinessKey("123");
	}
}