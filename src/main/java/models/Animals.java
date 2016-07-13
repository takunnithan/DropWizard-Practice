package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by takunnithan on 7/13/2016.
 */
public class Animals {

    @JsonProperty
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
