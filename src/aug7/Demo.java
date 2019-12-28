package aug7;

import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;

public class Demo {

@Test	
public void erp()throws Throwable
{
		ERP_Functions obj=new ERP_Functions();
	String lau=	obj.lanchAPP("http://webapp.qedge.com");
	System.out.println(lau);
	String log=	obj.login("admin", "master");
	System.out.println(log);
String sup=obj.supplier("akhilesh", "Hyderabad1", "ameerpet", "india", "ranga101", "098767565",
"test@gmail.com","987655432", "iam planning to purchase");
System.out.println(sup);
String out=obj.logout();
System.out.println(out);
obj.closebrw();
	}

}
