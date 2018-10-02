package org.minxc.emp.system.impl.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.minxc.emp.basis.api.constant.EnvironmentConstant;
import org.minxc.emp.core.impl.model.AbstractCommonModel;
import org.minxc.emp.core.util.CryptoUtil;



/**
 * 描述：SYS_PROPERTIES 实体对象
 */
public class SystemPropertiesEntity extends AbstractCommonModel{

   
	private static final long serialVersionUID = 971325618699701881L;

	/**
     * 主键
     */
    protected String id;

    /**
     * 参数名
     */
    protected String name;

    /**
     * 别名
     */
    protected String alias;

    /**
     * 分组
     */
    protected String group;

    /**
     * 参数值
     */
    protected String value;

    /**
     * 分类使用逗号进行分割。
     */
    protected List<String> categorys = new ArrayList<String>();

    /**
     * 值是否加密存储。
     * 在编辑的时候不显示具体的值。
     */
    protected int encrypt = 0;

    /**
     * 描述。
     */
    protected String description = "";

    protected String environment = EnvironmentConstant.DEV.key();


    public void setId(String id) {
        this.id = id;
    }

    /**
     * 返回 主键
     *
     * @return
     */
    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回 参数名
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 返回 别名
     *
     * @return
     */
    public String getAlias() {
        return this.alias;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * 返回 分组
     *
     * @return
     */
    public String getGroup() {
        return this.group;
    }

    public void setValue(String val) throws Exception {
        this.value = val;
    }

    /**
     * 返回 参数值
     *
     * @return
     */
    public String getValue() {
        return this.value;
    }

    /**
     * 如果是加密的情况，将值进行加密。
     *
     * @throws Exception
     */
    public void setValByEncrypt() throws Exception {
        if (this.encrypt == 1) {
//            this.value = EncryptUtil.encrypt(this.value);
            this.value = CryptoUtil.encodeBASE64(this.value);
        }
    }

    /**
     * 返回值时如果是加密情况，则将密码解密。
     *
     * @return
     * @throws Exception
     */
    public String getRealVal() {
        if (this.encrypt == 1) {
            try {
//                return EncryptUtil.decrypt(this.value);
                return CryptoUtil.decodeBASE64(this.value);
            } catch (Exception e) {
                return "";
            }
        }
        return this.value;
    }


    public List<String> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<String> categorys) {
        this.categorys = categorys;
    }

    public int getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(int encrypt) {
        this.encrypt = encrypt;
    }

    public String getDescription() {
        return description;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("name", this.name)
                .append("alias", this.alias)
                .append("group", this.group)
                .append("value", this.value)
                .append("createTime", this.createTime)
                .toString();
    }
}