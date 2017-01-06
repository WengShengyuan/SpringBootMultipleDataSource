package com.rails.demoapp.core.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("ThreadManager")
public class ThreadManager {
	private static final Logger LOGGER = LogManager.getLogger(ThreadManager.class);
	
	private ThreadPoolExecutor threadPoolExecutor;
	private BlockingQueue<Runnable> threadQueue;
	
	public ThreadManager(){
		try {
			init();
		} catch (Exception e) {
			LOGGER.error("ThreadManager init fail. "+e);
		}
	}
	
	public ThreadPoolExecutor getThreadPoolExecutor() {
		return threadPoolExecutor;
	}

	private void init() throws Exception {
		threadQueue = new ArrayBlockingQueue<Runnable>(1024);
		
		/* int corePoolSize 并行工作线程数量
		 * int maximumPoolSize 最大并行工作线程数量
		 * long keepAliveTime (maximumPoolSize - corePoolSize)线程保留时间
		 * TimeUnit unit 时间单位
		 * BlockingQueue<Runnable> workQueue 线程队列 
		 * RejectedExecutionHandler handler 拒绝策略 
		 */
		threadPoolExecutor = new ThreadPoolExecutor(128, 256, 60L, TimeUnit.SECONDS, threadQueue, new ThreadPoolExecutor.CallerRunsPolicy());
		LOGGER.info("pool initialized!");
	}
	
	
}
