package com.spotify.outh2.pojo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
//@Jacksonized
//@Builder

@JsonInclude(JsonInclude.Include.NON_NULL)

@Generated("jsonschema2pojo")
public class Error {

    @JsonProperty("error")
    private InnerError error;

   /* @JsonProperty("error")
    public InnerError getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(InnerError error) {
        this.error = error;
    }*/

}
