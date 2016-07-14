package resources;

import com.google.common.base.Optional;
import models.Animals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by takunnithan on 7/13/2016.
 */
@Path("/basic")
@Produces(MediaType.APPLICATION_JSON)
public class BasicResource {

    Logger logger = LoggerFactory.getLogger(BasicResource.class);

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

    @POST
    @Path("/create")
    public Animals createAnimals(@Valid Animals animal){
        animal.setMessage("animal is created");
        logger.info("Validation successful, Animal will be created now");
        return  animal;
    }
}
