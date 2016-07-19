import config.AppConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import models.Animals;
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
        Animals animal = new Animals();
        animal.setMessage(appConfiguration.getMessage());
        BasicResource resource = new BasicResource(animal);
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
