package DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;
public class ERP_DataDriven {
WebDriver driver;
ERP_Functions erp=new ERP_Functions();
@BeforeTest
public void adminLogin()
{
String launch=erp.lanchAPP("http://webapp.qedge.com");
System.out.println(launch);
String login=erp.login("admin", "master");
System.out.println(login);
}
@Test
public void verify_supplier()throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil();
	int rc=xl.rowCount("Supplier");
	int cc=xl.colCount("Supplier");
	System.out.println("no of rows::"+rc+"  "+"no of columns ::"+cc);
	for(int i=1;i<=rc;i++)
	{
	String sname=xl.getData("Supplier", i, 0);
	String address=xl.getData("Supplier", i, 1);
	String city=xl.getData("Supplier", i, 2);
	String country=xl.getData("Supplier", i, 3);
	String cperson=xl.getData("Supplier", i, 4);
	String pnumber=xl.getData("Supplier", i, 5);
	String mail=xl.getData("Supplier", i, 6);
	String mnumber=xl.getData("Supplier", i, 7);
	String note=xl.getData("Supplier", i, 8);
String results=erp.supplier(sname, address, city, country, cperson, pnumber,
	mail, mnumber, note);
xl.setData("Supplier", i, 9, results);
	}
}
@AfterTest
public void logout()throws Throwable
{
	erp.logout();
	erp.closebrw();
}
}



















