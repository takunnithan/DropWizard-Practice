package resources;

import entity.Product;
import io.dropwizard.auth.Auth;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repositories.couchbase.Repository;
import security.User;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by takunnithan on 7/13/2016.
 */
@Path("/product/{product}")
@Api(value = "/product")
@Produces(MediaType.APPLICATION_JSON)
public class BasicResource {

    Logger logger = LoggerFactory.getLogger(BasicResource.class);

    private Repository repository;

    public BasicResource(Repository repository) {
        this.repository = repository;
    }

    /**
     * <b>Get a product from couchbase</b>
     *
     * @param productId, String
     * @return <b>product</b>, Product
     */
    // TODO: Include authentication from database and ROLE based authentication
    @RolesAllowed("Admin")
    @GET
    @ApiOperation(
        value = "Find a product by ID",
        notes = "Returns a product with the provided ID if exists",
        response = Product.class)
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Product not found")})
    public Product getProduct(
        @ApiParam(value = "ID of product that needs to be fetched", allowableValues = "range[1,1000]", required = true)
        @PathParam("product") String productId, @Auth User user) {
        String documentKey = "product:" + productId;
        Product product = repository.findById(documentKey, Product.class);
        return product;
    }

    /**
     * <b>Create product document in couchbase</b>
     *
     * @param productId, String
     * @param product,   Product
     * @return <b>createdProduct</b>, Product
     */
    @POST
    public Product createProduct(@PathParam("product") String productId, @Valid Product product) {
        Product createdProduct = repository.create(product, Product.class);
        logger.info("Validation successful, Product is created");
        return createdProduct;
    }

    /**
     * <b>Delete a product from couchbase</b>
     *
     * @param product, Product
//     */
    @DELETE
    public String deleteProduct(@Valid Product product) {
        repository.delete(product);
        return "Product deleted successfully!";
    }

    /**
     * Update a product in couchbase
     *
     * @param productId, String
     * @param product,   Product
     * @return updatedProduct, Product
     */
    /*
    TODO: Update doesn't work .. needs to be fixed.
     */
    @PUT
    public Response updateProduct(@PathParam("product") String productId, @Valid Product product) {
        Product updatedProduct = null;
        if (repository.findById(productId, Product.class) != null) {
            updatedProduct = repository.update(product, Product.class);
        }
        return Response.status(Response.Status.CREATED).entity(updatedProduct).build();
    }

}
