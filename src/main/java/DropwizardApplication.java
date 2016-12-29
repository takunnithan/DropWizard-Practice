import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import config.AppConfiguration;
import dao.UserDao;
import healthcheck.Healthcheck;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;
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

        // configure mysql

        UserDao dao = configureMysql(environment, appConfiguration);

        // Configure Security

        configureSecurity(environment, dao);

        // Registering resources with the environment

        BasicResource resource = new BasicResource(repo, dao);
        environment.jersey().register(resource);

        // Health check
        final Healthcheck healthCheck =
            new Healthcheck(appConfiguration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
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
    private void configureSecurity(Environment environment, UserDao dao){
        environment.jersey().register(new AuthDynamicFeature(
            new BasicCredentialAuthFilter.Builder<User>()
            .setAuthenticator(new BasicAuthenticator(dao))
            .setAuthorizer(new BasicAuthorizer())
            .setRealm("Secure REALM")
            .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        // for @Auth annotation
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

    /**
     * Configuring Mysql for user credentials
     * @param environment environment variable
     * @param configuration configuration file
     * @return UserDao object
     */
    private UserDao configureMysql(Environment environment, AppConfiguration configuration){
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final UserDao dao = jdbi.onDemand(UserDao.class);
        return dao;
    }

}
