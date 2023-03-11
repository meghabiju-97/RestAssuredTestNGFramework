package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {


    public InnerError getInnerError() {
        return error;
    }

    public void setInnerError(InnerError error) {
        this.error = error;
    }

    @JsonProperty("error")
   InnerError error;



}