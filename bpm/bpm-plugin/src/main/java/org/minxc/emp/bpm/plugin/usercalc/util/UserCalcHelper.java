package org.minxc.emp.bpm.plugin.usercalc.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.minxc.emp.bpm.plugin.usercalc.user.def.ExecutorVar;

public class UserCalcHelper {
	
//	public static List<String> a(ExecutorVar executorVar, BpmUserCalcPluginSession pluginSession,
//			boolean turnKeys2Ids) {
//		Map<String, Object> vars = null;
//		List<String> ids = new ArrayList();
//		String PK = "";
//		String[] PKs;
//		String[] boMap;
//		if ("BO".equals(executorVar.getSource())) {
//			PKs = executorVar.getName().split("\\.");
//			boMap = null;
//			BusinessData boData = (BusinessData) boMap.get(PKs[0]);
//			PK = boData.getString(PKs[1]);
//		} else if ("flowVar".equals(executorVar.getSource())) {
//			PK = (String) ((Map) vars).get(executorVar.getName());
//		}
//
//		PKs = PK.split(",");
//		if (BeanUtils.isEmpty(PK)) {
//			return Collections.emptyList();
//		} else if (executorVar.getExecutorType().equals("fixed")) {
//			ids.addAll(Arrays.asList(PKs));
//			return ids;
//		} else {
//			int var9;
//			String var10000;
//			if ("user".equals(executorVar.getExecutorType())) {
//				if (!"userId".equals(executorVar.getUserValType()) && turnKeys2Ids) {
//					boMap = PKs;
//					int var14 = PKs.length;
//
//					for (var9 = 0; var9 < var14; ++var9) {
//						var10000 = boMap[var9];
//						IUser u = null;
//						if (u != null) {
//							ids.add(((IUser) u).getUserId());
//						}
//					}
//				} else {
//					ids.addAll(Arrays.asList(PKs));
//				}
//			} else if (!"groupId".equals(executorVar.getGroupValType()) && turnKeys2Ids) {
//				String dimension = executorVar.getDimension();
//				String[] var15 = PKs;
//				var9 = PKs.length;
//
//				for (int var10 = 0; var10 < var9; ++var10) {
//					var10000 = var15[var10];
//					IGroup group = null;
//					if (group != null) {
//						ids.add(((IGroup) group).getGroupId());
//					}
//				}
//			} else {
//				ids.addAll(Arrays.asList(PKs));
//			}
//
//			return ids;
//		}
//	}
	
}