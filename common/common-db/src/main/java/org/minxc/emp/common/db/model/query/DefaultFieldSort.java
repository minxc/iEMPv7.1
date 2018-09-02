package org.minxc.emp.common.db.model.query;

import org.minxc.emp.core.api.query.FieldSort;
import org.minxc.emp.core.api.query.SortDirection;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
* 项目名称：common-db   
* 类名称：DefaultFieldSort   
* 类描述：  字段排序逻辑 
* 创建人：Xianchang.min   
* 创建时间：2018年9月2日 下午4:35:44   
* 修改人：Xianchang.min   
* 修改时间：2018年9月2日 下午4:35:44   
* 修改备注：   
* @version  1.0  
*
 */
public class DefaultFieldSort implements FieldSort, Serializable {

    private static final long serialVersionUID = 5742164735225460363L;
    
    private SortDirection direction;
    private String field;

    public DefaultFieldSort(String field) {
        this(field, SortDirection.ASC);
    }

    public DefaultFieldSort(String field, SortDirection direction) {
        this.direction = direction;
        this.field = field;
    }

    public SortDirection getSortDirection() {
        return direction;
    }


    @Override
    public String getField() {
        return field;
    }

    public void setSortDirection(SortDirection direction) {
        this.direction = direction;
    }

    public void setField(String field) {
        this.field = field;
    }


    private static String INJECTION_REGEX = "[A-Za-z0-9\\_\\-\\+\\.]+";

    public static boolean isSQLInjection(String str) {
        return !Pattern.matches(INJECTION_REGEX, str);
    }

    @Override
    public String toString() {
        if (isSQLInjection(field)) {
            throw new IllegalArgumentException("SQLInjection property: " + field);
        }
        return field + (direction == null ? "" : " " + direction.name());
    }

    /**
     *   将sql片段转化成排序对象。
     *
     * @param orderSegment ex: "id asc,code desc"
     */
    public static List<DefaultFieldSort> formString(String orderSegment) {
    	
        if (orderSegment == null || orderSegment.trim().equals("")) {
            return Lists.newArrayListWithCapacity(0);
        }

        List<DefaultFieldSort> results = Lists.newArrayList();
        String[] orderSegments = orderSegment.trim().split(",");
        for (int i = 0; i < orderSegments.length; i++) {
            String sortSegment = orderSegments[i];
            DefaultFieldSort order = _formString(sortSegment);
            if (order != null) {
                results.add(order);
            }
        }
        return results;
    }


    private static DefaultFieldSort _formString(String orderSegment) {
    	
        if (orderSegment == null || orderSegment.trim().equals("")) {
            return null;
        }

        String[] array = orderSegment.trim().split("\\s+");
        if (array.length != 1 && array.length != 2) {
            throw new IllegalArgumentException("orderSegment pattern must be {property}.{direction}, input is: " + orderSegment);
        }

        return create(array[0], array.length == 2 ? array[1] : "asc");
    }


    /**
     * @param property
     * @param direction
     * @return
     */
    public static DefaultFieldSort create(String property, String direction) {
        return new DefaultFieldSort(property, SortDirection.fromString(direction));
    }

}
