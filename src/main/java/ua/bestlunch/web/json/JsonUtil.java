package ua.bestlunch.web.json;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import ua.bestlunch.util.mapper.HibernateAwareObjectMapper;

import java.io.IOException;

/**
 * Created by Виктор on 01.12.2016.
 */
//Serialize and deserialize objects to json
public class JsonUtil {

    private  static final ObjectMapper mapper = new HibernateAwareObjectMapper();

    public static <T> MappingIterator<T> readValues(String json, Class<T> clazz) throws IOException {
        ObjectReader reader = mapper.reader(clazz);
        return reader.readValues(json);
    }

    public static <T> T readValue(String json, Class<T> clazz) throws IOException{
        return mapper.readValue(json, clazz);
    }

    public static <T> String writeValue(T obj) throws IOException{
        return mapper.writeValueAsString(obj);
    }
}
