package com.edward.io.jdk.concurrent;

/**
 * Created by lori on 2017/9/7.
 */
public class ShutdownHookExample {

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("goodbye JVM");
            }
        });

        System.out.println("hello JVM");
        System.exit(0);
    }
}
