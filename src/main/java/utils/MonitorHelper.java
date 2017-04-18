package utils;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import parser.AbstractParser;

/**
 * 
 * @author yangyudong
 *该类用来监视进程队列中有无停止不动的线程，如果有，就重启该线程
 */
public class MonitorHelper {
	
	List<ParserThread> parserThreads;

	public MonitorHelper(List<ParserThread> parserThreads) {
		this.parserThreads=parserThreads;
	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		System.out.println("hello");
//	}
	
	public void init() {
		Runnable runnable = new Runnable() {
			public void run() {
				checkIfStop();
			}
		};
		
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 5, 20, TimeUnit.MINUTES);
//		service.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.SECONDS);
//		service.
	}
	
	private void checkIfStop(){
		System.out.println("checking start!");
		System.out.println(parserThreads.size());
		ListIterator<ParserThread> iter = parserThreads.listIterator();
		while(iter.hasNext()){
//			System.out.println("进入循环");
			ParserThread t =  iter.next();  
			if(t.ifParseStop()){
				t.stopParser();
				iter.remove();
				try {
					Thread.sleep(2000);
//					t.getParser().restart();
					//利用反射的原理取到爬取类
					AbstractParser par=(AbstractParser) Class.forName(t.getParser().getClass().getName()).newInstance();
					par.setDelayTime(t.getParser().getDelayTime());
					ParserThread t2=new ParserThread(par);
//					ParserThread t2=new ParserThread(t.getParser());
					iter.add(t2);
					t2.start();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("checking finished!");
//			System.out.println("here");
		}
//		for(ParserThread t:parserThreads){
//			
//			if(t.ifParseStop()){
//				t.stopParser();
//				t.reParser();
//			}
//		}
	}
	
	
	
	

}
