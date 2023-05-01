package org.example.app.spi;

import org.example.app.Plugin2ExampleService;
import org.example.app.api.ExampleService;
import org.example.app.api.spi.ExampleServiceFactory;

public class Plugin2ExampleServiceFactory implements ExampleServiceFactory {
    @Override
    public ExampleService create() {
        return new Plugin2ExampleService();
    }
}
