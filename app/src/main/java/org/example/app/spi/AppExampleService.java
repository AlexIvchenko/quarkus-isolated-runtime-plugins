package org.example.app.spi;

import org.example.app.api.ExampleService;
import org.example.lib.Lib;

public final class AppExampleService implements ExampleService {
    @Override
    public String example() {
        return Lib.methodV3();
    }
}
