package repositories.couchbase.jsonconverter;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonInclude;
import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonProcessingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static com.couchbase.client.deps.com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * Created by takunnithan on 08/08/2016.
 */
public class JsonConverter {

    private final ObjectMapper mapper =
        new ObjectMapper()
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));

    public <T> T fromJson(String source, Class<T> valueType) throws Exception {
        try {
            return mapper.readValue(source, valueType);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public <T> String toJson(T source) throws Exception {
        try {
            return mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new Exception(e);
        }
    }
}
