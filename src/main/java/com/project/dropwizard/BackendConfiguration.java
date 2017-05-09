package com.project.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.NotEmpty;

public class BackendConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    private String message;

    @JsonProperty
    @Max(10)
    private int messageRepetitions;
    @JsonProperty
    private final String additionalMessage = "This is optional";
    @JsonProperty
    private final DataSourceFactory database = new DataSourceFactory();

    public String getMessage() {
        return message;
    }

    public int getMessageRepetitions() {
        return messageRepetitions;
    }


    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
