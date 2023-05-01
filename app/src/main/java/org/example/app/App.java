package org.example.app;

import io.quarkus.runtime.StartupEvent;
import org.example.app.api.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private final Instance<List<ExampleService>> serviceInstances;

    public App(Instance<List<ExampleService>> serviceInstances) {
        this.serviceInstances = serviceInstances;
    }

    public void load(@Observes StartupEvent startupEvent) {
        for (List<ExampleService> serviceInstance : serviceInstances) {
            for (ExampleService service : serviceInstance) {
                LOG.info(service.example());
            }
        }
    }
}
