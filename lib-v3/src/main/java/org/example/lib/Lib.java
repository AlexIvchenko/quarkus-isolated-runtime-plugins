package org.example.lib;

public class Lib {
    public static String methodV3() {
        return "Calling " + Lib.class.getName() + "#methodV3() loaded from " + Lib.class.getClassLoader().getName();
    }
}
