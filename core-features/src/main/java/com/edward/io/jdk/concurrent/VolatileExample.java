package com.edward.io.jdk.concurrent;

/**
 * Created by lori on 2017/9/7.
 */
public class VolatileExample {

    private static volatile int num;

    public static void compositeIncrease() {
        int temp = num;
        num += (++temp);
    }

    public static void increase() {
        num++;
    }

    public static void main(String[] args) {

        int limit = 10;
        for (int i = 0; i < limit; i++) {
            TaskRunner.add(() -> {
                compositeIncrease();
            });
        }
        TaskRunner.start();
        System.out.println(num);
    }
}
