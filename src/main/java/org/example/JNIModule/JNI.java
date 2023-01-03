package org.example.JNIModule;

public class JNI {
    static {
        //System.load("/usr/local/lib/JNI.so");
        //System.load("/usr/local/include/wiringPi.h");
        System.load("/usr/local/lib/libwiringPi.so");
        System.load("/home/choi/DIDCommProtocol/src/main/java/org/example/JNIModule/JNI.so");
        //System.load("/usr/local/lib/libwiringPiDev.so");
    }

    public native float getSensorResult();
}
