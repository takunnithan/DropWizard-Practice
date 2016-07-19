package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.glassfish.jersey.jaxb.internal.XmlCollectionJaxbProvider;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by takunnithan on 7/13/2016.
 */
public class AppConfiguration extends Configuration {

    public AppConfiguration(){

    }

    @NotEmpty
    @JsonProperty
    private String message;

    @NotEmpty
    @JsonProperty("couchbase.nodes")
    private String couchbaseNodes;

    @JsonProperty
    public String getMessage() {
        return message;
    }

    public String getCouchbaseNodes() {
        return couchbaseNodes;
    }
}
