package org.example.app.irp;

import java.util.List;

public interface IrpPluginLoader {
    <T> List<T> load(Class<T> type);
}
