package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by takunnithan on 7/13/2016.
 */
public class AppConfiguration extends Configuration {

    public AppConfiguration() {

    }

    @NotEmpty
    public String couchBaseNode;

    @NotEmpty
    public String couchBaseBucket;

    @NotEmpty
    public String couchBasePassword;

    @JsonProperty("couchbase.nodes")
    public String getCouchBaseNode() {
        return couchBaseNode;
    }

    @JsonProperty("couchbase.bucket")
    public String getCouchBaseBucket() {
        return couchBaseBucket;
    }

    @JsonProperty("couchbase.password")
    public String getCouchBasePassword() {
        return couchBasePassword;
    }
}