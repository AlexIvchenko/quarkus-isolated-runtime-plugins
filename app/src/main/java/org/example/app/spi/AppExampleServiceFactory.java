package org.example.app.spi;

import org.example.app.api.ExampleService;
import org.example.app.api.spi.ExampleServiceFactory;

public class AppExampleServiceFactory implements ExampleServiceFactory {
    @Override
    public ExampleService create() {
        return new AppExampleService();
    }
}
