package digs_Smoke_Test_Cases;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Releasing_Family {
Wrapper wp = new Wrapper();

@Test(dataProvider = "Credentials")
public void releasingFamily(String username,String url) throws InterruptedException, IOException
{
	//Launch the browser and enter the URL
	wp.launchBrowser("firefox",url);
	Thread.sleep(3000);
	wp.autoitAuthen();
    Thread.sleep(3000);
	//To enter the user name
	wp.enterTextByID("userName", username);
	wp.clickById("loginButton");
	Thread.sleep(3000);
	System.out.println("Started Test case: Admin: Releasing Family");
	//To click on Admin tab and click on Releasing Family
	wp.clickById("searchAdminMenu");
	Thread.sleep(3000);
	wp.clickByXpath("//a[@href='/dsched/digitalsched/dsch_admin/relfamily.html']");
	Thread.sleep(3000);
	//wp.getTitleAndCompare(page);
	//To get value from first row under the grid -VIEW/ADD/EDIT RELEASING FAMILIES
	wp.tableVerification1("releasingFamiliesTable");
	//wp.getTextByID("label22591");
	//wp.getTextByID("label22592");
	wp.closeBrowser();
	
	
}

@DataProvider(name = "Credentials")
public Object[][] loginData() throws  IOException
{
Object[][] arrayobject = Wrapper.getXlsData("Releasing_Family");
return arrayobject;
}
}