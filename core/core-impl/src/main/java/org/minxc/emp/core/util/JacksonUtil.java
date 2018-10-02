
/**  

* @Title: JacksonUtil.java 

* @Package com.minxc.emp.core.util 

* @Description: TODO(用一句话描述该文件做什么) 

* @author Xianchang.min  

* @date 2018年8月24日 上午12:40:53 

* @version V1.0  

*/

package org.minxc.emp.core.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/*
 * 
* 项目名称：core-impl   
* 类名称：JacksonUtil   
* 类描述：   JacksonUtil
* 创建人：Xianchang.min   
* 创建时间：2018年8月24日 下午8:48:57   
* 修改人：Xianchang.min   
* 修改时间：2018年8月24日 下午8:48:57   
* 修改备注：   
* @version  1.0  
*
 */
public class JacksonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static ObjectMapper xmlMapper = new XmlMapper();
//	private static JsonNodeFactory jsonNodeFactory = new JsonNodeFactory(false);

	/**
	 * 防止反射调用构造器创建对象
	 */
	private JacksonUtil() {
		throw new AssertionError();
	}

	/**
	 * 自定义日期序列化处理类 LocalDateTime jdk8 support
	 */
	public static class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
		@Override
		public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
				SerializerProvider serializerProvider) throws IOException {
			String localdateTimeStr = localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
			jsonGenerator.writeString(localdateTimeStr);
		}
	}

	/**
	 * 自定义日期序列化处理类 LocalDateTime jdk8 support
	 */
	public static class JsonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
		@Override
		public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException, JsonProcessingException {
			String str = jsonParser.getText().trim();
			return LocalDateTime.parse(str, DateTimeFormatter.ISO_DATE_TIME);
		}
	}

	/**
	 * 自定义日期反序列化处理类 LocalDate jdk8 support
	 */
	public static class JsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {
		@Override
		public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
				throws IOException {
			String str = jsonParser.getText().trim();
			return LocalDate.parse(str, DateTimeFormatter.ISO_DATE);
		}
	}

	/**
	 * 自定义日期序列化类 LocalDate jdk8 support
	 */
	public static class JsonLocalDateSerializer extends JsonSerializer<LocalDate> {
		@Override
		public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException {
			String localdateStr = localDate.format(DateTimeFormatter.ISO_DATE);
			jsonGenerator.writeString(localdateStr);
		}
	}

	/**
	 * json数据转pojo
	 *
	 * @param jsonStr json字符串
	 * @param cls     映射类型
	 * @param         <T> 推导类型
	 * @return 推导类型json对象
	 */
	public static <T> T json2Pojo(String jsonStr, Class<T> cls) {
		T object = null;
		try {
			object = objectMapper.readValue(jsonStr, cls);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * json数据转PojoList
	 *
	 * @param jsonStr json数据
	 * @param cls     类型
	 * @param         <T> 推导类型
	 * @return pojoList
	 */
	public static <T> List<T> jsonArray2PojoList(String jsonStr, Class<T> cls) {
		List<T> pojoList = null;
		try {
			CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, cls);
			pojoList = objectMapper.readValue(jsonStr, listType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pojoList;
	}

	/**
	 * pojo转json
	 *
	 * @param obj pojo
	 * @return json字符串
	 */
	public static String pojo2Json(Object obj) {
		String jsonStr = "";
		try {
			jsonStr = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * json转listMap
	 *
	 * @param jsonArray jsonArray字符串
	 * @return Listmap对象
	 */
	public static List<Map<String, Object>> jsonArray2ListMap(String jsonArray) {
		List<Map<String, Object>> convertedListMap = null;
		try {
			convertedListMap = objectMapper.readValue(jsonArray, new TypeReference<List<Map<String, Object>>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertedListMap;
	}

	/**
	 * json转map
	 *
	 * @param json json字符串
	 * @return map对象
	 */
	public static Map<String, Object> json2Map(String json) {
		Map<String, Object> convertedMap = null;
		try {
			convertedMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertedMap;
	}

	/**
	 * listMap转json
	 *
	 * @param listMap listMap
	 * @return
	 */
	public static String listMap2JsonArray(List<Map<String, Object>> listMap) {
		String jsonStr = "";
		try {
			jsonStr = objectMapper.writeValueAsString(listMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * listMap转json
	 *
	 * @param listMap listMap
	 * @return
	 */
	public static String listObject2Json(List<?> list) {
		String jsonStr = "";
		try {
			jsonStr = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	/**
	 * xml转pojo
	 *
	 * @param xmlStr xml字符串
	 * @param cls    映射对象
	 * @param        <T> 推导类型
	 * @return pojo
	 */
	public static <T> T xml2Pojo(String xmlStr, Class<T> cls) {
		T pojo = null;
		try {
			pojo = xmlMapper.readValue(xmlStr, cls);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pojo;
	}

	/**
	 * xml转pojoList
	 *
	 * @param xmlStr xml字符串
	 * @param cls    映射对象
	 * @param        <T> 推导类型
	 * @return pojo
	 */
	public static <T> List<T> xml2PojoList(String xmlStr, Class<T> cls) {
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, cls);
		List<T> pojoList = null;
		try {
			pojoList = xmlMapper.readValue(xmlStr, listType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pojoList;
	}

	/**
	 * pojo转xml
	 *
	 * @param object
	 */
	public static String pojo2Xml(Object object) {
		String xml = "";
		try {
			xml = xmlMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return xml;
	}

	/**
	 * xml转map
	 *
	 * @param xmlStr xml字符串
	 * @return map对象
	 */
	public static Map<String, Object> xml2Map(String xmlStr) {
		Map<String, Object> map = null;
		try {
			map = xmlMapper.readValue(xmlStr, new TypeReference<Map<String, Object>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * xml转ListMap
	 *
	 * @param xmlStr xml字符串
	 * @return map对象
	 */
	public static List<Map<String, Object>> xml2ListMap(String xmlStr) {
		List<Map<String, Object>> listMap = null;
		try {
			listMap = xmlMapper.readValue(xmlStr, new TypeReference<List<Map<String, Object>>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listMap;
	}


	/**
	 * Factory method for constructing an empty JSON Object ("struct") node
	 */
	public static ObjectNode jsonObject(){
		return xmlMapper.createObjectNode();
	}
	/**
	 * Factory method for constructing an empty JSON Array node
	 */
	public static ArrayNode jsonArray(){
		return xmlMapper.createArrayNode();
	}



	public static JsonNode parseToJsonObject(String jsonString) throws Exception{
		return objectMapper.readTree(jsonString);
	}


	public static void main(String[] args){

		ObjectMapper mapper = new ObjectMapper();
		try {
//			JsonNode node = mapper.readTree(new File("E:\\iEMP\\iEMPv7.1\\core\\core-impl\\src\\resources\\test.json"));
//			JsonNode node = mapper.readTree(new File("E:\\iEMP\\iEMPv7.1\\core\\core-impl\\src\\resources\\biz_object_relation.json"));
//			JsonNode node = mapper.readTree(new File("E:\\iEMP\\iEMPv7.1\\core\\core-impl\\src\\resources\\student.json"));
			JsonNode node = mapper.readTree(new File("E:\\iEMP\\iEMPv7.1\\core\\core-impl\\src\\resources\\rights.json"));
			System.out.println(node.getNodeType());
			System.out.println(node.fieldNames());
			System.out.println(node);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	  /**
     * 根据键获取值。
     *
     * @param obj
     * @param key
     * @param defaultValue
     * @return String
     */
//    public static String getString(JSONObject obj, String key, String defaultValue) {
//        if (!obj.containsKey(key)) return defaultValue;
//        return obj.getString(key);
//    }

    /**
     * 根据键获取值。
     *
     * @param obj
     * @param key
     * @return String
     */
//    public static String getString(JSONObject obj, String key) {
//        return getString(obj, key, "");
//    }

    /**
     * 根据键获取int值。
     *
     * @param obj
     * @param key
     * @return int
     */
//    public static int getInt(JSONObject obj, String key) {
//        if (!obj.containsKey(key)) return 0;
//        return obj.getIntValue(key);
//    }

    /**
     * 根据键获取int值。
     *
     * @param obj
     * @param key
     * @param defaultValue
     * @return int
     */
//    public static int getInt(JSONObject obj, String key, int defaultValue) {
//        if (!obj.containsKey(key)) return defaultValue;
//        return obj.getIntValue(key);
//    }

//    public static boolean getBoolean(JSONObject obj, String key) {
//        if (!obj.containsKey(key)) return false;
//        return obj.getBoolean(key);
//    }

    /**
     * 根据键获取boolean值。
     *
     * @param obj
     * @param key
     * @param defaultValue
     * @return boolean
     */
//    public static boolean getBoolean(JSONObject obj, String key, boolean defaultValue) {
//        if (!obj.containsKey(key)) return defaultValue;
//        return obj.getBoolean(key);
//    }


    /**
     * 判断是jsonArray是否为空
     *
     * @param jsonArrStr
     * @return
     */
//    public static boolean isNotEmptyJsonArr(String jsonArrStr) {
//        return !isEmptyJsonArr(jsonArrStr);
//    }

    /**
     * 判断是jsonArray是否为空
     *
     * @param jsonArrStr
     * @return
     */
//    public static boolean isEmptyJsonArr(String jsonArrStr) {
//        if (StringUtil.isEmpty(jsonArrStr))
//            return true;
//        try {
//            JSONArray jsonAry = JSONArray.parseArray(jsonArrStr);
//            return jsonAry.size() > 0 ? false : true;
//        } catch (Exception e) {
//            LOGGER.debug(e.toString());
//            return true;
//        }
//    }

    /**
     * 替换掉包含富文本的json 字符串中特殊的字符
     *
     * @param str
     * @return
     */
    public static String escapeSpecialChar(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 删除的空项，主要controller请求返回的时候 如果数据有{a:null}换转换失败
     *
     * @param jsonObject void
     * @throws
     * @since 1.0.0
     */
//    public static void removeNull(JSONObject jsonObject) {
//        for (String key : jsonObject.keySet()) {
//            Object val = jsonObject.get(key);
//            if (val == null) {
//                jsonObject.put(key, "");
//            }
//        }
//    }

    /**
     * 删除的空项，主要controller请求返回的时候 如果数据有{a:null}换转换失败
     *
     * @param jsonArray void
     * @throws
     * @since 1.0.0
     */
//    public static void removeNull(JSONArray jsonArray) {
//        for (int i = 0; i < jsonArray.size(); i++) {
//            removeNull(jsonArray.getJSONObject(i));
//        }
//    }

    /**
     * <pre>
     * JSONArray转成JSONObject
     * eg:
     * [{id:"1",name:"a"},{id:"2",name:"b"}] 当keyName设置为id是，转换成
     * {1:{id:"1",name:"a"},2:{id:"2",name:"b"}}
     * </pre>
     *
     * @param jsonArray
     * @param keyName   :以哪个字段为key
     * @return JSONObject
     * @throws
     * @since 1.0.0
     */
//    public static JSONObject arrayToObject(JSONArray jsonArray, String keyName) {
//        JSONObject jsonObject = new JSONObject();
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject temp = jsonArray.getJSONObject(i);
//            jsonObject.put(temp.getString(keyName), temp);
//        }
//        return jsonObject;
//    }

    /**
     * <pre>
     * 把jsonObject 转到jsonArray,通常用于以下这种情况
     * 为了保证jsonArray里的某个值是唯一的所以先用jsonObject来保存着
     * eg:
     * {a:{id:1,name:a},b:{id:2,name:b}}
     * 转成：[{id:1,name:a},{id:2,name:b}]
     * </pre>
     *
     * @param jsonObject
     * @return JSONArray
     * @throws
     * @since 1.0.0
     */
//    public static JSONArray objectToArray(JSONObject jsonObject) {
//        JSONArray jsonArray = new JSONArray();
//        for (Object key : jsonObject.keySet()) {
//            jsonArray.add(jsonObject.get(key));
//        }
//        return jsonArray;
//    }

    /**
     * <pre>
     * 优化了JSON.parseObject()方法
     * </pre>
     *
     * @param jsonStr
     * @param cls
     * @return
     */
//    public static <T> T parseObject(String jsonStr, Class<T> cls) {
//        if (StringUtil.isEmpty(jsonStr)) {
//            return null;
//        }
//        return JSON.parseObject(jsonStr, cls);
//    }

    /**
     * <pre>
     * 优化了JSON.parseArray()方法
     * </pre>
     *
     * @param jsonStr
     * @param cls
     * @return
     */
//    public static <T> List<T> parseArray(String jsonStr, Class<T> cls) {
//        if (StringUtil.isEmpty(jsonStr)) {
//            return null;
//        }
//        return JSON.parseArray(jsonStr, cls);
//    }

    /**
     * <pre>
     * 优化了JSON.toJSONString()方法
     * </pre>
     *
     * @param obj
     * @return
     */
//    public static String toJSONString(Object obj) {
//        if (obj == null) {
//            return null;
//        }
//        return JSON.toJSONString(obj);
//    }

    

}
