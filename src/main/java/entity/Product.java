package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by takunnithan on 08/08/2016.
 */
public class Product {

    @NotEmpty
    private String productId;
    @NotEmpty
    private String productName;
    @NotEmpty
    private String category;
    @NotEmpty
    private String price;

    @JsonProperty
    public String getProductId() {
        return productId;
    }

    @JsonProperty
    public void setProductId(String productId) {
        this.productId = productId;
    }

    @JsonProperty
    public String getProductName() {
        return productName;
    }

    @JsonProperty
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty
    public String getCategory() {
        return category;
    }

    @JsonProperty
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty
    public String getPrice() {
        return price;
    }

    @JsonProperty
    public void setPrice(String price) {
        this.price = price;
    }
}
