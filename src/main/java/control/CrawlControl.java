package control;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;

import parser.ITaskCrawl;
import utils.JedisUtil;

public class CrawlControl {

	private static final String queueName = "JobCrawler";

	private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	public static void startCrawling() {
		System.out.println("system has been starting");
		executor.scheduleAtFixedRate(() -> {
			System.out.println("scheduling.........");
			String itemStr = JedisUtil.getListElement(queueName);
			if (itemStr != null) {
				System.out.println(itemStr);
//				Object item = JSON.parseObject(itemStr, ITaskCrawl.class);
//				((ITaskCrawl) item).crawl();
			}
		}, 1, 1, TimeUnit.SECONDS);
	}
	
	public static void enqueueTask(ITaskCrawl task){
		JedisUtil.setListElement(queueName, JSON.toJSONString(task));
	}
	
	

}

