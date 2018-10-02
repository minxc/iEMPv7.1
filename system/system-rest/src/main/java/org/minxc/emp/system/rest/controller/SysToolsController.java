package org.minxc.emp.system.rest.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.minxc.emp.common.rest.GenericController;
import org.minxc.emp.common.rest.util.RequestUtil;
import org.minxc.emp.core.api.aop.annotation.ErrorCatching;
import org.minxc.emp.core.util.AppContextUtil;
import org.minxc.emp.core.util.ConstantUtil;
import org.minxc.emp.core.util.EnumUtil;
import org.minxc.emp.core.util.PinyinUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统工具类
 *
 * @author aschs
 */
@RestController
@RequestMapping("/sys/tools/")
public class SysToolsController extends GenericController {
	/**
	 * <pre>
	 * 根据一个枚举类的路径获取这个枚举的json形式，供前端使用
	 * 注意！！如果枚举在类中间，那么路径如下：com.dstz.base.db.model.Column$TYPE
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getEnum")
	public Object getEnum(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String enumClassPath = RequestUtil.getString(request, "path");
		boolean listMode = RequestUtil.getBoolean(request, "listMode");// 列表模式
		if (listMode) {
			return EnumUtil.toJSONArray(enumClassPath);
		}
		return EnumUtil.toJSON(enumClassPath);
	}

	/**
	 * <pre>
	 * 根据path(类路径)获取key（字段名）的常量
	 * ps:如果key为空，会把path类的全部static final的静态变量获取出来
	 * </pre>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getConstant")
	public Object getConstant(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String classPath = RequestUtil.getString(request, "path");
		String key = RequestUtil.getString(request, "key");
		if (StringUtils.isEmpty(key)) {
			return ConstantUtil.get(classPath);
		}
		return ConstantUtil.get(classPath, key);
	}

	@RequestMapping("getInterFaceImpls")
	@ErrorCatching
	public Object getInterFaceImpls(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String classPath = RequestUtil.getString(request, "classPath");
		Class<?> clazz = Class.forName(classPath);
		Map<String, ?> map = AppContextUtil.getImplInstance(clazz);
		return map.values();
	}

	@RequestMapping("pinyin")
	public void pinyin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String chinese = RequestUtil.getString(request, "chinese");
		int type = RequestUtil.getInt(request, "type");// 1：全拼 0：首字母拼音
		String result = "";
		if (type == 1) {
			result = PinyinUtil.getPinyin(chinese);
		} else {
			result = PinyinUtil.getPinYinHeadChar(chinese);
		}
		response.getWriter().write(result);
	}
}
