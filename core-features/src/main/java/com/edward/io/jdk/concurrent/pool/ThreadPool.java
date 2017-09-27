package com.edward.io.jdk.concurrent.pool;

/**
 * Created by edwardcheng on 2017/9/21.
 */
public interface ThreadPool<Job extends Runnable> {

    void execute(Job job);

    void shutdown();

    void addWorkers(int num);

    void removeWorker(int num);

    int getJobSize();
}
