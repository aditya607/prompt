package com.prompt.dropWizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class PromptService extends Application<AppConfiguration> {


    public static void main(final String[] args) throws Exception {
        new PromptService().run(args);
    }

    @Override
    public void run(final AppConfiguration configuration, final Environment environment)
            throws Exception {
        environment.jersey().register(new PromptServiceRest());
    }
}