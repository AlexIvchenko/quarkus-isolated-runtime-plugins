package org.example.app.irp;

import io.quarkus.arc.DefaultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

@DefaultBean
@ApplicationScoped
public class IrpPluginLoaderImpl implements IrpPluginLoader {
    private static final Logger LOG = LoggerFactory.getLogger(IrpPluginLoaderImpl.class);

    private final IrpConfig.Config config;

    public IrpPluginLoaderImpl(IrpConfig config) {
        this.config = config.config().orElse(null);
    }

    @Override
    public <T> List<T> load(Class<T> type) {
        if (config == null) {
            return List.of();
        }
        LOG.debug("Loading plugins...");
        String classpathBasePath = config.classpathBasePath();
        List<ServiceLoader<T>> loaders = new ArrayList<>();

        for (IrpConfig.Plugin pluginConfig : config.plugins()) {
            String pluginName = pluginConfig.name();
            if (!pluginConfig.enabled()) {
                continue;
            }
            try {
                ServiceLoader<T> loader = ServiceLoader.load(type, IrpClassLoader.forPlugin(classpathBasePath, pluginName));
                loaders.add(loader);
            } catch (Throwable t) {
                LOG.error("Failed to initialize ServiceLoader for {}", pluginName, t);
            }
        }
        ServiceLoader<T> appServiceLoader = ServiceLoader.load(type, Thread.currentThread().getContextClassLoader());
        loaders.add(appServiceLoader);
        List<T> plugins = new ArrayList<>();
        for (ServiceLoader<T> loader : loaders) {
            for (T plugin : loader) {
                plugins.add(plugin);
            }
        }
        return plugins;
    }
}
