package org.minxc.emp.system.impl.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.minxc.emp.system.api.model.SystemDataSource;
import org.minxc.emp.system.impl.model.def.SysDataSourceDefAttribute;

import com.minxc.emp.core.impl.model.AbstractCommonModel;
import com.minxc.emp.core.util.JacksonUtil;

import java.util.List;

/**
 * 数据源对象
 */
public class SystemDataSourceEntity extends AbstractCommonModel implements SystemDataSource {
	
	private static final long serialVersionUID = 3007721784206841377L;
	/**
     * 数据源的别名
     */
    @NotEmpty
    private String key;
    /**
     * 数据源的名字
     */
    @NotEmpty
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 数据库类型 枚举在com.dstz.base.api.db.DbType 的key
     */
    @NotEmpty
    private String dbType;
    /**
     * 类路径
     */
    @NotEmpty
    private String classPath;
    /**
     * <pre>
     * 属性字段json，为了简单就以json格式入库就行
     * 因为这个对象也不常用，这样保存是可以的，对于常用对象这样就不建议用这个了
     * </pre>
     */
    @NotEmpty
    private String attributesJson;
    /**
     * 属性字段
     */
    @NotNull
    @Valid
    private List<SysDataSourceDefAttribute> attributes;

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getAttributesJson() {
        return attributesJson;
    }

    public void setAttributesJson(String attributesJson) {
        this.attributesJson = attributesJson;
//        this.attributes = JsonUtil.parseArray(attributesJson, SysDataSourceDefAttribute.class);
        this.attributes = JacksonUtil.jsonArray2PojoList(attributesJson, SysDataSourceDefAttribute.class);
    }

    public List<SysDataSourceDefAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<SysDataSourceDefAttribute> attributes) {
        this.attributes = attributes;
//        this.attributesJson = JsonUtil.toJSONString(attributes);
        this.attributesJson = JacksonUtil.listObject2Json(attributes);
    }

}
