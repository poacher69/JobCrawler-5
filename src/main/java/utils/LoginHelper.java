package utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class LoginHelper {
	
	public static boolean login(MyWebDriver driver){
		String userName="547428014@qq.com";
		String pwd="yangshao121";

		try{
			driver.get("https://www.douban.com/accounts/login?source=main");
			
			WebElement nameBox = driver.findElement(By.id("email"));
			WebElement pwdBox = driver.findElement(By.id("password"));
			nameBox.sendKeys(userName);
			pwdBox.sendKeys(pwd);
			WebElement btn = driver.findElement(By.name("login"));
			Thread.sleep(5000);
			btn.click();
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

}
