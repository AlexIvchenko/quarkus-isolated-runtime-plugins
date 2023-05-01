package org.example.lib;

public class Lib {
    public static String methodV2() {
        return "Calling " + Lib.class.getName() + "#methodV2() loaded from " + Lib.class.getClassLoader().getName();
    }
}
