package utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class MyWebDriver extends FirefoxDriver{
	
	
	public MyWebDriver(){
		super();
//		manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	/*
	 * 重载的构造方法，如果指定ifcached为false，那么默认不加载cookies
	 */
	public MyWebDriver(boolean ifcached){
		super();
		if(ifcached==false){
			this.manage().deleteAllCookies();
		}
		
	}
	
	/*
	 * 重载的构造方法，使用profile配置浏览器
	 */
	public MyWebDriver(FirefoxProfile profile){
		super(profile);
//		manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	
	/**
	 * 该方法在页面上存在元素时会返回该元素，不存在时返回空而不报错
	 * @param by 
	 * @return 
	 */
	public WebElement getElement(By by){
		WebElement ele;
		try{
			ele=findElement(by);
			return ele;
		}catch(org.openqa.selenium.NoSuchElementException ex){
//			System.out.println("网页上不存在该元素");
			return null;
		}

	}
	
	public List<WebElement> getElements(By by){
		List<WebElement> eles;
		try{
			eles=findElements(by);
			return eles;
		}catch(org.openqa.selenium.NoSuchElementException ex){
			System.out.println("网页上不存在该元素");
			return null;
		}
	}
	
	/**
	 * 该方法用于找出某个网页元素下面的子元素
	 * @param by
	 * @param ele
	 * @return
	 */
	public WebElement getElement(By by,WebElement ele){
		WebElement e;
		try{
			e=ele.findElement(by);
			return e;
		}catch(org.openqa.selenium.NoSuchElementException ex){
			System.out.println("网页上不存在该元素");
			return null;
		}

	}
	
	
	/*
	 * 
	 */
	public List<WebElement> getElements(By by,WebElement ele){
		List<WebElement> eles;
		try{
			eles=ele.findElements(by);
			return eles;
		}catch(org.openqa.selenium.NoSuchElementException ex){
			System.out.println("网页上不存在该元素");
			return null;
		}
	}
	
	
	
	
	/**
	 * 该方法在每次请求url之前会让进程睡眠一段时间
	 * @param url
	 * @param deleay
	 */
	public void getWithDelay(String url,int delay){
		try {
			Thread.sleep(delay*1000);
			get(url);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}

}
