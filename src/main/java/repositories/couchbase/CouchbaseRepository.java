package repositories.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.transcoder.JsonTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.couchbase.jsonconverter.JsonConverter;

/**
 * Created by takunnithan on 08/08/2016.
 */
public class CouchbaseRepository implements Repository {

    Logger logger = LoggerFactory.getLogger(CouchbaseRepository.class);
    private Cluster cluster;
    private Bucket bucket;
    private JsonConverter jsonConverter = new JsonConverter();
    private final JsonTranscoder transcoder = new JsonTranscoder();


    public CouchbaseRepository(Cluster cluster, String bucket, String password){
        this.cluster = cluster;
        this.bucket = cluster.openBucket(bucket,password);
    }

    public <T> T findById(String id, Class<T> type) {
        JsonDocument doc = bucket.get(id);
        return doc == null ? null : fromJsonDocument(doc, type);
    }

    public <T> T create(T entity, Class<T> type) {
        return null;
    }

    public <T> T update(T entity, Class<T> type) {
        return null;
    }

    public <T> T upsert(T entity, Class<T> type) {
        return null;
    }

    public <T> void delete(T entity) {

    }


    /**
     * Converts a JsonDocument into an object of the specified type
     *
     * @param doc JsonDocument to be converted
     * @param type Class<T> that represents the type of this or a parent class
     * @return Reference to an object of the specified type
     */
    protected <T> T fromJsonDocument(JsonDocument doc, Class<T> type){
        if (doc == null) {
            throw new IllegalArgumentException("document is null");
        }
        JsonObject content = doc.content();
        if (content == null) {
            throw new IllegalStateException("document has no content");
        }
        if (type == null) {
            throw new IllegalArgumentException("type is null");
        }
        T result = null;
        try {
            result = jsonConverter.fromJson(content.toString(), type);
        } catch (Exception e) {
            logger.error("Error while retrieving document!", e.getMessage());
        }
        return result;
    }

//    /**
//     * Converts an object to a JsonDocument
//     *
//     * @param source Object to be converted
//     * @return JsonDocument that represents the specified object
//     */
//    protected <T> JsonDocument toJsonDocument(T source) throws Exception {
//        if (source == null) {
//            throw new IllegalArgumentException("entity is null");
//        }
//        try {
//            JsonObject content =
//                transcoder.stringToJsonObject(jsonConverter.toJson(source));
//            JsonDocument doc = JsonDocument.create(id, content,);
//            return doc;
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
}
