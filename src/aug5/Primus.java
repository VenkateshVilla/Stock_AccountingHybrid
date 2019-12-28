package aug5;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Primus {

	public static void main(String[] args) throws Throwable{
		Properties p=new Properties();
FileInputStream f=new FileInputStream("./PropertiesFile/Repository.properties");
p.load(f);
System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
WebDriver driver=new ChromeDriver();
driver.get(p.getProperty("Url"));	
driver.findElement(By.name(p.getProperty("username"))).sendKeys("Admin");
driver.findElement(By.name(p.getProperty("password"))).sendKeys("Admin");
driver.findElement(By.name(p.getProperty("loginbtn"))).click();
driver.close();

	}

}
