package com.rails.demoapp.core.thread;

import java.util.Date;

/**
 * 自定义子线程基础类
 * @author wengshengyuan
 *
 */
public class ConfigurableThread extends Thread{
	private boolean firstExe = false;
	private boolean enableTimeLimit = false;
	private boolean enableExeLimit = false;
	private Date firstExeTime;
	private Integer exeLimit=0;
	private Long timeLimit = 0L;
	private Long increaseStep = 0L;
	
	protected Long timeInterval = 1000L;
	protected Integer tryCount = 1;
	protected boolean successFlag = false;
	
	@Override
	public void run(){
		if(!firstExe){
			this.firstExe=true;
			this.firstExeTime=new Date();
		}
		
		try{
			while(execuable()){
				doRun();
				tryCount++;
				Thread.sleep(this.timeInterval);
				this.timeInterval +=this.increaseStep;
			}
			return;
		}catch(Exception e){
			threadExceptionHandle(e);
		}
	}
	
	protected void doRun() throws Exception{
		throw new Exception("Thread doRun method not determined.");
	}
	protected void onTimeout(){
		System.out.println("Thread timeout and quit.");
	}
	protected void onTryout(){
		System.out.println("Thread reach try time limit and quit.");
	}
	protected void onSuccess(){
		System.out.println("Thread success and quit.");
	}
	protected void threadExceptionHandle(Exception e){
		System.out.println("Thread exception occured."+e);
	}
	
	private boolean execuable(){
		if(enableTimeLimit && (System.currentTimeMillis()-this.firstExeTime.getTime()) > this.timeLimit){
			onTimeout();
			return false;
		}
		if(enableExeLimit && this.tryCount > this.exeLimit){
			onTryout();
			return false;
		}
		if(successFlag){
			onSuccess();
			return false;
		}
		return true;
	}
	
	public final ConfigurableThread enableTimeLimit(){
		this.enableTimeLimit=true;
		return this;
	}
	public final ConfigurableThread disableTimeLimit(){
		this.enableTimeLimit = false;
		return this;
	}
	public final ConfigurableThread enableExeLimit(){
		this.enableExeLimit = true;
		return this;
	}
	public final ConfigurableThread disableExeLimit(){
		this.enableExeLimit =false;
		return this;
	}
	public final ConfigurableThread setExeLimit(int exeLimit){
		this.exeLimit = exeLimit;
		return this;
	}
	public final ConfigurableThread setTimeLimit(long timeLimit){
		this.timeLimit = timeLimit;
		return this;
	}
	public final ConfigurableThread setInterval(long interval){
		this.timeInterval = interval;
		return this;
	}
	public final ConfigurableThread setIncreaseStep(long step){
		this.increaseStep=step;
		return this;
	}
}
