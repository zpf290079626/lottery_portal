package com.zpf.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Josn-Java转换工具类
 *
 * @author
 * @createDate
 */
public class JsonUtil {

    private JsonUtil() {

    }

    private static final Logger LOGGER = Logger.getLogger(JsonUtil.class.getName());

    /**
     * java object to json object
     *
     * @return str jsonstr
     */
    public static String fromJavaToJson(Object obj) {
        String jsonstr = null;
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        Writer strWriter = new StringWriter();
        try {
            mapper.writeValue(strWriter, obj);
            jsonstr = strWriter.toString();
        } catch (JsonGenerationException e) {
            LOGGER.info("转换到json异常:", e);
        } catch (JsonMappingException e) {
            LOGGER.info("转换到json异常:", e);
        } catch (IOException e) {
            LOGGER.info("转换到json异常:", e);
        }
        return jsonstr;
    }

    /**
     * json object to java object
     *
     * @return obj javaobj
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJsonToJava(String jsonStr, Class<T> valueType) {
        Object obj = null;
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);//忽略json串中有但类里没有的属性
        try {
            obj = mapper.readValue(jsonStr, valueType);
        } catch (JsonParseException e) {
            LOGGER.info("转换到java异常:", e);
        } catch (JsonMappingException e) {
            LOGGER.info("转换到java异常:", e);
        } catch (IOException e) {
            LOGGER.info("转换到java异常:", e);
        }
        return (T) obj;

    }

}