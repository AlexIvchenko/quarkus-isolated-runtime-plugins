package org.example.app;

import org.example.app.api.ExampleService;
import org.example.lib.Lib;

public class Plugin1ExampleService implements ExampleService {
    @Override
    public String example() {
        return Lib.methodV1();
    }
}
