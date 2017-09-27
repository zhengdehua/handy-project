package com.edward.io.jdk.concurrent;

/**
 * Created by edwardcheng on 2017/9/11.
 */
public class SingletonExample {

    private static class InstanceHolder {
        private static SingletonExample instance = new SingletonExample();
    }

    public static SingletonExample getInstance() {
        return InstanceHolder.instance;
    }

}
