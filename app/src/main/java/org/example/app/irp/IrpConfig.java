package org.example.app.irp;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithParentName;

import java.util.List;
import java.util.Optional;

@ConfigMapping(prefix = "irp")
public interface IrpConfig {
    @WithParentName
    Optional<Config> config();

    interface Config {
        String classpathBasePath();

        List<Plugin> plugins();
    }

    interface Plugin {
        String name();

        @WithDefault("true")
        boolean enabled();
    }
}
