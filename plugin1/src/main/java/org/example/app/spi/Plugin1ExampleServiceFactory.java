package org.example.app.spi;

import org.example.app.Plugin1ExampleService;
import org.example.app.api.ExampleService;
import org.example.app.api.spi.ExampleServiceFactory;

public class Plugin1ExampleServiceFactory implements ExampleServiceFactory {
    @Override
    public ExampleService create() {
        return new Plugin1ExampleService();
    }
}
