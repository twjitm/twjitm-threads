package com.twjitm.threads.thread.policy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by IntelliJ IDEA.
 * User: 文江 Date: 2018/8/19  Time: 10:52
 * https://blog.csdn.net/baidu_23086307
 * 队列满抛出异常
 */
public class AbortPolicy extends ThreadPoolExecutor.AbortPolicy {
    private String threadName;
    private Logger logger = LoggerFactory.getLogger(AbortPolicy.class);

    public AbortPolicy() {
        this(null);
    }

    public AbortPolicy(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        if (threadName != null) {
            logger.error("THREAD POOL " + threadName + " IS EXHAUSTED, EXECUTOR=" + executor.toString());
        }
        String msg = String.format("Server["
                        + " THREAD NAME: %s, POOL SIZE: %d (ACTIVE: %d, CORE: %d, MAX: %d, LARGEST: %d), TASK: %d (COMPLETED: %d),"
                        + " EXECUTOR STATUS:(IS SHUTDOWN:%s, IS TERMINATED:%s, IS TERMINATING:%s)]",
                threadName, executor.getPoolSize(), executor.getActiveCount(), executor.getCorePoolSize(), executor.getMaximumPoolSize(), executor.getLargestPoolSize(),
                executor.getTaskCount(), executor.getCompletedTaskCount(), executor.isShutdown(), executor.isTerminated(), executor.isTerminating());
        logger.info(msg);
        super.rejectedExecution(runnable, executor);
    }
}
