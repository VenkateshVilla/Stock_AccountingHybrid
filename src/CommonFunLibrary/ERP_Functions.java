package CommonFunLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions {
WebDriver driver;
String res;
public String lanchAPP(String url)
{
System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
driver=new ChromeDriver();
driver.get(url);
driver.manage().window().maximize();
if(driver.findElement(By.id("btnsubmit")).isDisplayed())
{
	res="Pass";
}
else{
	res="Fail";
}
return res;
}
//login method
public String login(String username,String password)
{
	driver.findElement(By.name("username")).clear();
driver.findElement(By.name("username")).sendKeys(username);
driver.findElement(By.name("password")).clear();
driver.findElement(By.name("password")).sendKeys(password);	
driver.findElement(By.name("btnsubmit")).click();
if(driver.findElement(By.id("logout")).isDisplayed())
{
	res="Login success";
}
else{
	res="Login Fail";
}
return res;
}
public String supplier(String sname,String address,String city,String country,
String cperson,String pnumber,String mail,String mnumber,String note)
 throws Throwable{
	//click on supplier lin
driver.findElement(By.linkText("Suppliers")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")).click();
Thread.sleep(2000);
String expdata=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
driver.findElement(By.name("x_Supplier_Name")).sendKeys(sname);
driver.findElement(By.name("x_Address")).sendKeys(address);
driver.findElement(By.name("x_City")).sendKeys(city);
driver.findElement(By.name("x_Country")).sendKeys(country);
driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
driver.findElement(By.name("x__Email")).sendKeys(mail);
driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
driver.findElement(By.name("x_Notes")).sendKeys(note);
driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
Thread.sleep(3000);
driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("//button[@class='ajs-button btn btn-primary']")).click();
Thread.sleep(3000);
if(driver.findElement(By.xpath("//input[@id='psearch']")).isDisplayed())
{
	driver.findElement(By.xpath("//input[@id='psearch']")).clear();
	driver.findElement(By.xpath("//input[@id='psearch']")).sendKeys(expdata);
	driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
}
else{
	//click on search panel
driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
driver.findElement(By.xpath("//input[@id='psearch']")).clear();
driver.findElement(By.xpath("//input[@id='psearch']")).sendKeys(expdata);
driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
}

String actdata=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
if(expdata.equals(actdata))
{
	res="pass";
}
else{
	res="fail";
}
Thread.sleep(2000);
driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
return res;
}
public String logout()throws Throwable
{
	driver.findElement(By.xpath("//*[@id='mi_logout']/a")).click();
	Thread.sleep(2000);
	if(driver.findElement(By.name("btnsubmit")).isDisplayed())
	{
		res="pass";
	}
	else{
		res="Fail";
	}
	return res;
}
public void closebrw()
{
	driver.close();
}





}











