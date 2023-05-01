package org.example.app;

import org.example.app.api.ExampleService;
import org.example.app.api.spi.ExampleServiceFactory;
import org.example.app.irp.IrpPluginLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class AppConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(AppConfiguration.class);
    private final IrpPluginLoader loader;

    public AppConfiguration(IrpPluginLoader loader) {
        this.loader = loader;
    }

    @Singleton
    @Produces
    public List<ExampleService> exampleServices() {
        List<ExampleServiceFactory> plugins = loader.load(ExampleServiceFactory.class);
        for (ExampleServiceFactory plugin : plugins) {
            // init / configure / etc
            plugin.create();
        }
        return plugins.stream().map(ExampleServiceFactory::create).collect(Collectors.toList());
    }
}
