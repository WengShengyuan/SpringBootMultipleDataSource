package com.rails.demoapp.core.thread.task;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rails.demoapp.core.module.tbdsone.service.TableOneService;
import com.rails.demoapp.core.thread.workers.TestWorker;

import groovyjarjarasm.asm.tree.IntInsnNode;

@Component
public class TestTask {

	@Autowired
	TestWorker testWorker;
	
	@Resource(name="TableOneServiceImpl")
	private TableOneService tableOneService;
	
	private static final Logger LOGGER = LogManager.getLogger(TestTask.class);
	
//	@Scheduled(fixedRate=5*1000L)
//	@Scheduled(cron="0 0/1 * * * *")
//	private void testTaskOne() {
//		LOGGER.info("testTaskOne start threads...");
//		for(int i=0; i<3;i++){
//			testWorker.execute();
//		}
//		LOGGER.info("testTaskOne executed.");
//	}
	
	@PostConstruct
	private void loop() {
		try {
			tableOneService.loopThrough();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
