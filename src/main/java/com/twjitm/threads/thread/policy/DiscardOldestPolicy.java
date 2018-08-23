package com.twjitm.threads.thread.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by IntelliJ IDEA.
 * User: 文江 Date: 2018/8/19  Time: 10:52
 * https://blog.csdn.net/baidu_23086307
 */
public class DiscardOldestPolicy extends ThreadPoolExecutor.DiscardOldestPolicy{
    private Logger logger= LoggerFactory.getLogger(DiscardOldestPolicy.class);

    private String threadName;

    public DiscardOldestPolicy() {
        this(null);
    }

    public DiscardOldestPolicy(String threadName) {
        this.threadName = threadName;
    }

    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        if (threadName != null) {
            logger.error("THREAD POOL "+threadName+" IS EXHAUSTED, EXECUTOR=" + executor.toString());
        }

        super.rejectedExecution(runnable, executor);
    }
}
