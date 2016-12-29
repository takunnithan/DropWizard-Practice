package healthcheck;

import com.codahale.metrics.health.HealthCheck;

import javax.ws.rs.client.Client;

/**
 * Created by takunnithan on 30-12-2016.
 */
public class Healthcheck extends HealthCheck {

    private final String template;

    public Healthcheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
