package utils;

import parser.AbstractParser;

public class ParserThread extends Thread{
	
	AbstractParser parserClass;

	public ParserThread(AbstractParser parserClass) {
		this.parserClass=parserClass;
	}
	
	public AbstractParser getParser(){
		return parserClass;
	}
	
	public void run() {
		parserClass.parser();
	}
	
	//检查爬虫是否卡住了
	public boolean ifParseStop(){
		return parserClass.isStop();
	}
	
	//重新启动爬虫
//	public void reParser(){
//		parserClass.restart();
//	}
	
	//用于终止爬虫
	public void stopParser(){
		parserClass.stopParser();
		stop();
	}
	
	
	
	
	
	

}
