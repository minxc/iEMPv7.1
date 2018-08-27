package org.minxc.emp.system.impl.manager.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.minxc.emp.common.manager.impl.CommonManager;
import org.minxc.emp.core.api.exception.BusinessException;
import org.minxc.emp.system.impl.dao.SysDataSourceDefDao;
import org.minxc.emp.system.impl.manager.SysDataSourceDefManager;
import org.minxc.emp.system.impl.model.SysDataSourceDef;
import org.minxc.emp.system.impl.model.def.SysDataSourceDefAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.alibaba.fastjson.JSON;
import com.minxc.emp.core.util.JacksonUtil;
import com.minxc.emp.core.util.StringUtil;

/**
 * @description 数据源模板 Manager处理实现类
 */
@Service
public class SysDataSourceDefManagerImpl extends CommonManager<String, SysDataSourceDef> implements SysDataSourceDefManager {
    @Autowired
    SysDataSourceDefDao sysDataSourceDefDao;

    @Override
    public List<SysDataSourceDefAttribute> initAttributes(String classPath) {
        try {
            List<SysDataSourceDefAttribute> attributes = new ArrayList<>();
            Class<?> cls = Class.forName(classPath);
            if (!DataSource.class.isAssignableFrom(cls)) {// 不是DataSource的子类就报错
                throw new Exception("classPath[" + classPath + "]不是javax.sql.DataSource的子类");
            }
            for (Method method : cls.getMethods()) {
                if (!method.getName().startsWith("set") || method.getParameters().length != 1) {
                    continue;
                }
                Parameter param = method.getParameters()[0];
                SysDataSourceDefAttribute attribute = new SysDataSourceDefAttribute();
                String fieldName = StringUtil.lowerFirst(method.getName().replace("set", ""));
                attribute.setComment(fieldName);
                attribute.setName(fieldName);
                attribute.setRequired(false);
                attribute.setType(param.getType().getName());
                attributes.add(attribute);
            }
            return attributes;
        } catch (Exception e) {
            throw new BusinessException("根据classPath[" + classPath + "]获取参数", e);
        }
    }

    public static void main(String[] args) {
        SysDataSourceDefManagerImpl impl = new SysDataSourceDefManagerImpl();
        //org.apache.commons.dbcp.BasicDataSource
        //com.alibaba.druid.pool.DruidDataSource
//        System.out.println(JSON.toJSONString(impl.initAttributes("org.apache.commons.dbcp.BasicDataSource")));
        System.out.println(JacksonUtil.pojo2Json(impl.initAttributes("org.apache.commons.dbcp.BasicDataSource")));
    }
}
