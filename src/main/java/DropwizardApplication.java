import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import config.AppConfiguration;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.couchbase.CouchbaseRepository;
import repositories.couchbase.Repository;
import resources.BasicResource;
import security.BasicAuthenticator;
import security.BasicAuthorizer;
import security.User;


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

        // CouchBase configuration

        Repository repo = configureCouchbase(environment, appConfiguration);

        // Configure Swagger

        configureSwagger(environment);

        // Configure Security

        configureSecurity(environment);

        // Registering resources with the environment

        BasicResource resource = new BasicResource(repo);
        environment.jersey().register(resource);
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
     * Configuring couchbase for the application
     * @param environment environment variable
     * @param appConfiguration configuration file
     * @return Repository object
     */
    private Repository configureCouchbase(Environment environment, AppConfiguration appConfiguration ){

        String nodes = appConfiguration.getCouchBaseNode();
        String password = appConfiguration.getCouchBasePassword();
        String bucket = appConfiguration.getCouchBaseBucket();
        Cluster cluster = CouchbaseCluster.create(nodes);
        return
            new CouchbaseRepository(cluster, bucket, password);
    }

    /**
     * Configuration for swagger
     *
     * @param environment, dropwizard environment variable
     */
    private void configureSwagger(Environment environment) {
        // Swagger Resource
        logger.info("Configuring Swagger");
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

    /**
     * Configure security for Application
     * @param environment environment variable
     */
    private void configureSecurity(Environment environment){
        environment.jersey().register(new AuthDynamicFeature(
            new BasicCredentialAuthFilter.Builder<User>()
            .setAuthenticator(new BasicAuthenticator())
            .setAuthorizer(new BasicAuthorizer())
            .setRealm("Secure REALM")
            .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        // for @Auth annotation
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

}
