package org.example.app.api.spi;

import org.example.app.api.ExampleService;

public interface ExampleServiceFactory {
    ExampleService create();
}
