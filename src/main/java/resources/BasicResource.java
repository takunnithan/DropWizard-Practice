package resources;

import com.google.common.base.Optional;
import models.Animals;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by takunnithan on 7/13/2016.
 */
@Path("/basic")
@Produces(MediaType.APPLICATION_JSON)
public class BasicResource {

    private Animals animal;
    public BasicResource(Animals animal) {
        this.animal = animal;
    }

    @GET
    public Animals getAnimals(@QueryParam("name") Optional<String> name){
        animal.setId(1);
        animal.setName(name.get());
        return animal;
    }
}
