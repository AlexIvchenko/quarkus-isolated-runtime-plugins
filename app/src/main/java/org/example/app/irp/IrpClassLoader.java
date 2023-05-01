package org.example.app.irp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public final class IrpClassLoader extends URLClassLoader {
    private static final Logger LOG = LoggerFactory.getLogger(IrpClassLoader.class);

    public static IrpClassLoader forPlugin(String classpathBasePath, String pluginName) throws Exception {
        Path pluginDir = Path.of(classpathBasePath,pluginName);
        List<URL> jars = new ArrayList<>();
        Files.walkFileTree(pluginDir, new FileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().toLowerCase().endsWith(".jar")) {
                    jars.add(file.toUri().toURL());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                LOG.warn("Failed to visit file {}", file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });
        LOG.debug("IRP class path for {} is {}", pluginName, jars);
        return new IrpClassLoader(pluginName, jars.toArray(new URL[0]));
    }

    private IrpClassLoader(String pluginName, URL[] urls) {
        super(pluginName, urls, ClassLoader.getSystemClassLoader());
    }
}
