package com.rails.demoapp.core.thread.workers;

import java.util.Random;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.FileEncodingApplicationListener;
import org.springframework.stereotype.Component;

import com.rails.demoapp.core.caculator.TestCalculator;
import com.rails.demoapp.core.module.tbdsone.domain.TableOne;
import com.rails.demoapp.core.module.tbdsone.service.TableOneService;
import com.rails.demoapp.core.thread.ConfigurableThread;
import com.rails.demoapp.core.thread.ThreadManager;

@Component
public class TestWorker {

	private static final Logger LOGGER = LogManager.getLogger(TestWorker.class);
	
	
	@Autowired
	ThreadManager threadManager;
	
	public void execute(){
		threadManager.getThreadPoolExecutor().execute(new Worker().setInterval(500).enableExeLimit().setExeLimit(2));
	}
	
	class Worker extends ConfigurableThread {
		@Override
		protected void doRun() {
			Integer a = new Random().nextInt();
			Integer b = new Random().nextInt();
			LOGGER.info("Calculation worker ["+this.getName()+"] calculating(attempt:"+tryCount+") -> "+a+" + "+b+" = "+TestCalculator.add(a, b));
		}
		
		@Override
		protected void onTryout() {
			LOGGER.info("Calculation worker reach try limit, thread quit.");
		}
		
	}

}
