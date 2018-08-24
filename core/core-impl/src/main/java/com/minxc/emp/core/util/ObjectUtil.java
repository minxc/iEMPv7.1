
/**  

* @Title: ObjectUtil.java 

* @Package com.minxc.emp.core.util 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月24日 下午1:06:14 

* @version V1.0  

*/ 

package com.minxc.emp.core.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

/**      
* 项目名称：core-impl   
* 类名称：ObjectUtil   
* 类描述：    对象工具类
* 创建人：Xianchang.min   
* 创建时间：2018年8月24日 下午1:06:14   
* 修改人：Xianchang.min   
* 修改时间：2018年8月24日 下午1:06:14   
* 修改备注：   
* @version  1.0  
**/

public class ObjectUtil {

    private static final Class<?>[] BASIC_NUMBER_CLASSES = new Class[] {
            short.class, int.class, long.class, float.class, double.class };

    /**
     * 对象克隆
     *
     * @param t
     *            a T object.
     * @param <T>
     *            a T object.
     * @return a T object.
     */
    @SuppressWarnings("unchecked")
    public static final <T> T clone(T t) {
        if (t == null) {
            return null;
        }
        if (t instanceof Serializable) {
            return (T) SerializationUtils.clone((Serializable) t);
        }
        T result = null;
        if (t instanceof Cloneable) {
            try {
                result = (T) ObjectUtils.clone(t);
            } catch (Throwable e) {
            }
        }
        if (result == null) {
            String json = JsonUtils.toJson(t);
            result = (T) JsonUtils.fromJson(json, t.getClass());
        }
        return result;
    }

    /**
     * 访问对象成员属性值值
     * 
     * @param obj
     * @param field
     * @return
     * @throws YichaException
     */
    @SuppressWarnings("rawtypes")
    private static final Object getFieldValue(Object obj, String field) throws Exception {
        Object result = null;
        if (obj instanceof Map) {
            return ((Map) obj).get(field);
        }

        if (obj == null) {
            return null;
        }

        Method getterMethod = null;
        try {
            getterMethod = obj.getClass().getMethod("get" + StringUtils.capitalize(field));
        } catch (Exception e) {
        }
        if (getterMethod == null) {
            try {
                getterMethod = obj.getClass().getMethod("is" + StringUtils.capitalize(field));
            } catch (Exception e) {
            }
        }
        if (getterMethod == null) {
            Field privateField;
            try {
                privateField = obj.getClass().getDeclaredField(field);
                privateField.setAccessible(true);
                result = privateField.get(obj);
            } catch (Exception e) {
                throw new Exception("field[" + field + "] doesn't exist.");
            }
        } else {
            try {
                result = getterMethod.invoke(obj);
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * 获取对象属性值
     *
     * @param obj
     *            被取值的对象
     * @param clazz
     *            返回值的类型
     * @param path
     *            格式:field1.field2.field3
     * @param <T>
     *            a T object.
     * @return a T object.
     */
    @SuppressWarnings("unchecked")
    public static final <T> T getValue(Object obj, Class<T> clazz, String path) {
        Object o = getValue(obj, path);
        return o == null ? null : (T) o;
    }

    /**
     * <p>
     * getValue.
     * </p>
     *
     * @param obj
     *            a {@link java.lang.Object} object.
     * @param path
     *            a {@link java.lang.String} object.
     * @return a {@link java.lang.Object} object.
     */
    public static final Object getValue(Object obj, String path) {
        if (obj == null || StringUtils.isBlank(path)) {
            return null;
        }
        String[] arr = StringUtils.split(path, ".");
        Object o = obj;
        for (int i = 0, len = arr.length; i < len; i++) {
            final String field = StringUtils.strip(arr[i]);
            try {
                o = getFieldValue(o, field);
            } catch (Exception e) {
                o = null;
            }
        }
        return o;
    }

    /**
     * 判断是否是数字类型
     *
     * @param obj
     *            a {@link java.lang.Object} object.
     * @return a boolean.
     */
    public static final boolean isNumberType(Object obj) {
        if (obj == null) {
            throw new RuntimeException("object is null.");
        }
        if (obj instanceof Number) {
            return true;
        } else {
            for (Class<?> clazz : BASIC_NUMBER_CLASSES) {
                if (obj.getClass().equals(clazz)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断对象是否为零
     *
     * @param obj
     *            a {@link java.lang.Object} object.
     * @return a boolean.
     */
    public static final boolean isZero(Object obj) {
        if (!isNumberType(obj)) {
            return false;
        }
        final String foo = String.valueOf(obj);
        return StringUtils.equals(foo, "0") || StringUtils.equals(foo, "0.0");
    }

    /**
     * Map转换对象
     *
     * @param map
     *            a {@link java.util.Map} object.
     * @param clazz
     *            a {@link java.lang.Class} object.
     * @param <T>
     *            a T object.
     * @return a T object.
     */
    public static final <T> T fromMap(final Map<String, Object> map, Class<T> clazz) {
        return JsonUtils.fromJson(JsonUtils.toJson(map), clazz);
    }

    /**
     * 对象转Map
     *
     * @param object
     *            a {@link java.lang.Object} object.
     * @return a {@link java.util.Map} object.
     */
    public static final Map<String, Object> toMap(final Object object) {
        return JsonUtils.fromJson(JsonUtils.toJson(object));
    }

    /**
     * 设置对象属性
     *
     * @param object
     *            a {@link java.lang.Object} object.
     * @param field
     *            a {@link java.lang.String} object.
     * @param value
     *            a T object.
     * @param paramType
     *            a {@link java.lang.Class} object.
     * @param <T>
     *            a T object.
     */
    @SuppressWarnings("rawtypes")
    public static final <T> void setValue(final Object object, final String field, final T value, final Class paramType) {
        try {
            Method md = object.getClass().getMethod("set" + StringUtils.capitalize(field), paramType);
            if (md != null) {
                md.invoke(object, value);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置对象属性
     *
     * @param object
     *            a {@link java.lang.Object} object.
     * @param field
     *            a {@link java.lang.String} object.
     * @param value
     *            a T object.
     * @param <T>
     *            a T object.
     */
    public static final <T> void setValue(final Object object, final String field, final T value) {
        try {
            for (Method method : object.getClass().getMethods()) {
                if (StringUtils.equals(method.getName(), "set" + StringUtils.capitalize(field))) {
                    method.invoke(object, value);
                    break;
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}