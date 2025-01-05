package com.example;

import com.example.resources.MinimumCoinsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * @ClassName MinimumCoinsApplication
 * @Description
 * @Author HUANG ZHENJIA
 * @Date 2025/1/3
 * @Version 1.0
 */
public class MinimumCoinsApplication extends Application<MinimumCoinsConfiguration> {
    public static void main(String[] args) throws Exception {
        new MinimumCoinsApplication().run(args);
    }

    @Override
    public void run(MinimumCoinsConfiguration configuration, Environment environment) {
        System.out.println("Minimum Coins Service is starting...");

        environment.jersey().register(new MinimumCoinsResource());

        // enable cors
        enableCors(environment);
    }

    private void enableCors(Environment environment) {
        // create filter of cors
        FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*"); // allow all
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
