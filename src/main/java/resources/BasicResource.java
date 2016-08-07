package resources;

import com.google.common.base.Optional;
import entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.couchbase.Repository;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by takunnithan on 7/13/2016.
 */
@Path("/{product}")
@Produces(MediaType.APPLICATION_JSON)
public class BasicResource {

    Logger logger = LoggerFactory.getLogger(BasicResource.class);

    private Repository repository;

    public BasicResource(Repository repository) {
        this.repository = repository;
    }

    @GET
    public Product getProduct(@PathParam("product") String productId){
        String documentKey = "product:" + productId;
        Product product = repository.findById(documentKey, Product.class);
        return product;
    }


//    @POST
//    @Path("/create")
//    public Product createProduct(@Valid Product product){
//        animal.setMessage("animal is created");
//        logger.info("Validation successful, Animal will be created now");
//        return  animal;
//    }
}
