package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by takunnithan on 7/13/2016.
 */
public class Animals {

    @JsonProperty
    @Min(1)
    @Max(3)
    private int id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String message;

    public Animals() {
    }

    public Animals(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }

    @JsonProperty
    public void setMessage(String message) {
        this.message = message;
    }
}
