package cn.sst.scd.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/9/8 11:32 上午
 * @Version 1.1.0
 **/
public class LocalDateTimeSerializer extends JsonSerializer {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

    }


}
