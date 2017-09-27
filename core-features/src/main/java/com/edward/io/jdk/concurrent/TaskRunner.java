package com.edward.io.jdk.concurrent;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by edwardcheng on 2017/9/8.
 */
public class TaskRunner {

    private static List<Runnable> list = new Vector<>();

    public static void add(Runnable runnable) {
        list.add(runnable);
    }

    public static void remove(Runnable runnable) {
        list.remove(runnable);
    }

    public static void clear() {
        list.clear();
    }

    public static void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (Runnable runnable : list) {
            executorService.submit(runnable);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
