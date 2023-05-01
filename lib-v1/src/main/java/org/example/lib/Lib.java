package org.example.lib;

public class Lib {
    public static String methodV1() {
        return "Calling " + Lib.class.getName() + "#methodV1() loaded from " + Lib.class.getClassLoader().getName();
    }
}
