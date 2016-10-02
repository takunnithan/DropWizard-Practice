import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import config.AppConfiguration;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.couchbase.CouchbaseRepository;
import repositories.couchbase.Repository;
import resources.BasicResource;


/**
 * Created by takunnithan on 7/13/2016.
 */
public class DropwizardApplication extends Application<AppConfiguration> {
    private static Logger logger = LoggerFactory.getLogger(DropwizardApplication.class);

    public static void main(String args[]) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {

        /*
        * CouchBase configuration
        * */
        String nodes = appConfiguration.getCouchBaseNode();
        String password = appConfiguration.getCouchBasePassword();
        String bucket = appConfiguration.getCouchBaseBucket();
        Cluster cluster = CouchbaseCluster.create(nodes);
        Repository repo =
            new CouchbaseRepository(cluster, bucket, password);

        /*
        * Setting up resources for dropwizard
        * */
        BasicResource resource = new BasicResource(repo);

        /*
        * Registering resources with the environment
        * */
        environment.jersey().register(resource);

        logger.info("Configuring Swagger");
        configureSwagger(environment);

    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addBundle(new AssetsBundle("/swagger", "/docs", "index.html"));
    }

    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Configuration for swagger
     *
     * @param environment, dropwizard environment variable
     */
    private void configureSwagger(Environment environment) {
        // Swagger Resource

        environment.jersey().register(
            io.swagger.jaxrs.listing.ApiListingResource.class);
        environment.jersey().register(
            io.swagger.jaxrs.listing.SwaggerSerializers.class);

        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setTitle("Sample Product API");
        swaggerConfig.setVersion("1.0");
        swaggerConfig.setBasePath("/");
        swaggerConfig.setResourcePackage("resources");
        swaggerConfig.setScan(true);
    }

}
