package org.example.JNIModule;

public class JNI {
    static {
        System.loadLibrary("JNI");
    }

    public native int getNumber();
}
