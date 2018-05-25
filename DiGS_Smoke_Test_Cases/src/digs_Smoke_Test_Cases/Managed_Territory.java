package digs_Smoke_Test_Cases;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

public class Managed_Territory {
	Wrapper wp = new Wrapper();
	@Test(dataProvider = "Credentials")
	public void managedTerritory(String username,String url) throws InterruptedException, IOException
	{
		//Launch the browser and enter the URL
		wp.launchBrowser("firefox", url);
		Thread.sleep(3000);
		wp.autoitAuthen();
	    Thread.sleep(3000);
		//To enter the user name
		wp.enterTextByID("userName", username);
		wp.clickById("loginButton");
		Thread.sleep(3000);
		System.out.println("Started Test case: Admin: Managed Territory");
		//To click on Admin tab and click on Managed Territory
		wp.clickById("searchAdminMenu");
		//wp.clickById("searchTaskMenu");
		Thread.sleep(3000);
		wp.clickByXpath("//a[@href='/dsched/digitalsched/dsch_admin/managedterritory.html']");
		Thread.sleep(4000);
		//wp.getTitleAndCompare(page);
		//To get the data from the grid
		wp.tableVerification1("managedTerritoriesTable");
		//wp.getTextByID("12357838356");
		wp.closeBrowser();
	}

	@DataProvider(name = "Credentials")
	public Object[][] loginData() throws  IOException
	{
	Object[][] arrayobject = Wrapper.getXlsData("Managed_Territory");
	return arrayobject;
	}
	

}
