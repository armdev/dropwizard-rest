package com.project.dropwizard;

import com.project.dropwizard.resources.ContactResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackendApp extends Application<BackendConfiguration> {

    //run from console : java -jar dropwizard_backend-xxxx.jar server config.yaml
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendApp.class);

    public static void main(String[] args) throws Exception {
        new BackendApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<BackendConfiguration> b) {
    }

    @Override
    public void run(BackendConfiguration c, Environment e) throws Exception {
        LOGGER.info("Method App#run() called");
        for (int i = 0; i < c.getMessageRepetitions(); i++) {
            System.out.println(c.getMessage());
        }
        System.out.println("message@@@ " + c.getAdditionalMessage());

        // Create a DBI factory and build a JDBI instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory
                .build(e, c.getDataSourceFactory(), "mysql");
        // Add the resource to the environment
        e.jersey().register(new ContactResource(jdbi, e.getValidator()));
    }
}
