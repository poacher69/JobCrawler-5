package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import parser.ITaskCrawl;
import parser.LagouCrawler;

public class TaskItem {

	private String key;
	private String url;
	private String crawler;
	private Map<String,String> properties;

	

	public TaskItem(String key,String url, String crawler, Map<String, String> properties) {
		super();
		this.key=key;
		this.url = url;
		this.crawler = crawler;
		this.properties = properties;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCrawler() {
		return crawler;
	}

	public void setCrawler(String crawler) {
		this.crawler = crawler;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
	
	
//	public static void main(String[] args) {
////		Class<?> demo1=null;
//		try {
//			 Class<?> demo1=Class.forName("parser.LagouCrawler");
//			 System.out.println(demo1.getName());
////			 ;
////			 demo1.getMethod("test").invoke(null);
//			 ITaskCrawl crawler= (ITaskCrawl)(demo1.getMethod("getCrawlerInstance").invoke(null));
//			 crawler.crawl(new TaskItem("https://www.lagou.com/gongsi/", "LagouCrawler"));
////			 crawler.crawl(new TaskItem("https://www.lagou.com/gongsi/", "LagouCrawler"));
//			 
////			 demo1.getMethod(name, parameterTypes)
//			 
//			 
////			 System.class.getMethod("setProperty", new Class[]{String.class,String.class}).invoke(null,"app_name","app_name_value");  
//
////			 ITaskCrawl crawler=(ITaskCrawl) demo1.newInstance();
////			 crawler.crawl();
////			System.out.println(crawler.test());
//			
//			
//		} catch (ClassNotFoundException |  IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}
