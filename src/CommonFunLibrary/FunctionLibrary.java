package CommonFunLibrary;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;
public class FunctionLibrary {
static WebDriver driver;
//method for launching browser
public static WebDriver startBrowser(WebDriver driver)throws Throwable
{
if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
{
System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
driver=new ChromeDriver();
}
else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
{
System.setProperty("webdriver.gecko.driver", "./CommonJars/geckodriver.exe");
driver=new FirefoxDriver();	
}
else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("ie"))
{
}
else{
	System.out.println("Browser value not matching");
}
return driver;
}
//launching url in browser
public static void openApplication(WebDriver driver)throws Throwable
{
	driver.get(PropertyFileUtil.getValueForKey("Url"));
	driver.manage().window().maximize();
}
//method for locating textboxes
public static void typeAction(WebDriver driver,String locatortype,
	String locatorvalue,String testdata)
{
	if(locatortype.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(locatorvalue)).clear();
		driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
	}
	else if(locatortype.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(locatorvalue)).clear();
		driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
	}
	else if(locatortype.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(locatorvalue)).clear();
		driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
	}
	else{
		System.out.println("Locator not matching for typeAction method");
	}
}
//method for clickaction
public static void clickAction(WebDriver driver,String locatortype,
		String locatorvalue)
{
	if(locatortype.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(locatorvalue)).click();
	}
	else if(locatortype.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(locatorvalue)).click();
	}
	else if(locatortype.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(locatorvalue)).click();
}
	else{
	System.out.println("Unable to locate for ClickAction method");	
	}
}
//method for closing browser
public static void closeBrowser(WebDriver driver)
{
driver.close();
}
//method for wait for element
public static void waitForElement(WebDriver driver,String locatortype,
String locatorvalue,String waittitme)
{
WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waittitme));
if(locatortype.equalsIgnoreCase("id"))
	{
mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
	}
	else if(locatortype.equalsIgnoreCase("name"))
	{
mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
	}
	else if(locatortype.equalsIgnoreCase("xpath"))
	{
mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
	}
	else
	{
System.out.println("unable to locate for waitForElement method");
}
}
//method for date generate
public static String generateDate()
{
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
	return sdf.format(date);	
}
//method for capture data into notepad
public static void captureData(WebDriver driver,String locatortytpe,
	String locatorvalue)throws Throwable
{
	String supplierdata="";
	if(locatortytpe.equalsIgnoreCase("id"))
	{
supplierdata=driver.findElement(By.id(locatorvalue)).getAttribute("value");
	}
	else if(locatortytpe.equalsIgnoreCase("xpath"))
	{
supplierdata=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");		
	}
	FileWriter fw=new FileWriter("./CaptureData/suppnumber.txt");
	BufferedWriter bw=new BufferedWriter(fw);
	bw.write(supplierdata);
	bw.flush();
	bw.close();
}
//table validation for supplier creation
public static void tableValidation(WebDriver driver,String column)
throws Throwable{
FileReader fr=new FileReader("./CaptureData/suppnumber.txt");
BufferedReader br=new BufferedReader(fr);
String Exp_data=br.readLine();
//convert column data into integer
int column1=Integer.parseInt(column);
if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).isDisplayed())
{
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).clear();
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).sendKeys(Exp_data);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("click-search"))).click();
Thread.sleep(5000);
}
else{
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Click-searchpanel"))).click();
Thread.sleep(5000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).clear();
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search-box"))).sendKeys(Exp_data);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("click-search"))).click();
}
WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("sup-table")));
List<WebElement>rows=table.findElements(By.tagName("tr"));
System.out.println(rows.size());
for(int i=1;i<rows.size()-1;i++)
{
String act_data=driver.findElement(By.xpath
("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+column1+"]/div/span/span")).getText();
Assert.assertEquals(act_data, Exp_data);
System.out.println(act_data+"   "+Exp_data);
break;
}
}
public static void stockCategories(WebDriver driver)throws Throwable
{
	System.out.println("Stock");
	WebElement stockItem=driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a"));	
	WebElement stockCategory=driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']/a"));
Actions ac=new Actions(driver);
ac.moveToElement(stockItem).build().perform();
ac.moveToElement(stockCategory).click().build().perform();
Thread.sleep(3000);
}
public static void tableValidation1(WebDriver driver,String Exp_data)throws Throwable
{
	if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).isDisplayed())
	{
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).clear();
Thread.sleep(3000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).sendKeys(Exp_data);
Thread.sleep(3000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn1"))).click();
Thread.sleep(3000);
	}
	else{
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.panel"))).click();
Thread.sleep(3000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).clear();
Thread.sleep(3000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.box1"))).sendKeys(Exp_data);
Thread.sleep(3000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn1"))).click();
Thread.sleep(3000);
	}
WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable.path1")));
List<WebElement> rows=table.findElements(By.tagName("tr"));
System.out.println(rows.size());
for (int i = 1; i < rows.size(); i++)
{
String act_data=driver.findElement(By.xpath
("//table[@id='tbl_a_stock_categorieslist']/tbody/tr["+i+"]/td[4]/div/span/span")).getText();
Thread.sleep(4000);
Assert.assertEquals(act_data, Exp_data);
System.out.println(act_data+"    "+Exp_data);
	break;
	}
}
public static void main(String[] args) throws Throwable{
	FunctionLibrary.stockCategories(driver);
}
}



















