package cn.itsource.pss;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {
	
	/**
	 * jdk提供的定时器
	 * 
	 * 我要每天早上10点钟执行我们某一个任务
	 * 
	 * 我要每个月最后一天执行某个任务
	 */
	public static void main(String[] args){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("我被执行了。。");
			}
		}, 1000,1000);
	}
}
