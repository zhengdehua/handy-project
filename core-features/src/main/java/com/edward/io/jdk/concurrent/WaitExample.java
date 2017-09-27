package com.edward.io.jdk.concurrent;

/**
 * Created by lori on 2017/9/7.
 */
public class WaitExample {

    private static volatile boolean flag;

    public static void main(String[] args) {

        TaskRunner.add(() -> {
            synchronized (WaitExample.class) {
                flag = true;
                WaitExample.class.notify();
            }
        });
        TaskRunner.add(() -> {
            synchronized (WaitExample.class) {
                while (!flag) {
                    try {
                        WaitExample.class.wait(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("get flag -> " + flag);
            }
        });
        TaskRunner.start();
    }
}

