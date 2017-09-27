package com.edward.io.jdk.concurrent;

/**
 * Created by lori on 2017/9/7.
 */
public class FinalExample {

    private final int i;
    private int j;

    private static FinalExample obj;

    public FinalExample() {
        synchronized (FinalExample.class) {
            obj = this;
            FinalExample.class.notify();
        }

        synchronized (FinalExample.class) {
            try {
                FinalExample.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        i = 1;
        j = 2;

        synchronized (FinalExample.class) {
            obj = this;
            FinalExample.class.notify();
        }
    }

    public static void writer() {
        obj = new FinalExample();
    }

    public static void reader() {
        while (obj == null) {
            synchronized (FinalExample.class) {
                FinalExample.class.notify();
            }
        }

        synchronized (FinalExample.class) {
            System.out.println("i=" + obj.i);
            System.out.println("j=" + obj.j);
            FinalExample.class.notify();
        }

        synchronized (FinalExample.class) {
            try {
                FinalExample.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("i=" + obj.i);
        System.out.println("j=" + obj.j);
    }

    public static void main(String[] args) {

        TaskRunner.add(() -> reader());
        TaskRunner.add(() -> writer());
        TaskRunner.start();
        TaskRunner.clear();

    }
}
