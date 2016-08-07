import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import config.AppConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import repositories.couchbase.CouchbaseRepository;
import repositories.couchbase.Repository;
import resources.BasicResource;

/**
 * Created by takunnithan on 7/13/2016.
 */
public class DropwizardApplication extends Application<AppConfiguration> {

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

    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }

    @Override
    public String getName() {
        return super.getName();
    }

}
