package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

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

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

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