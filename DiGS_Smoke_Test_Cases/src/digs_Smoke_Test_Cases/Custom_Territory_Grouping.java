package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

public class Custom_Territory_Grouping {
	Wrapper wp = new Wrapper();
	@Test(dataProvider = "Credentials")
	public void customTerritoryGrouping(String username,String url) throws InterruptedException, IOException
	{
		//Launch the browser
		wp.launchBrowser("firefox", url);
		Thread.sleep(3000);
		wp.autoitAuthen();
	    Thread.sleep(3000);
		//To enter the user name
		wp.enterTextByID("userName", username);
		wp.clickById("loginButton");
		Thread.sleep(3000);
		System.out.println("Started Tets case: Admin: Custom Territory Grouping");
		//To click on Admin tab and click on Custom Territory Grouping
		wp.clickById("searchAdminMenu");
		Thread.sleep(3000);
		wp.clickByXpath("//a[@href='/dsched/digitalsched/dsch_admin/managegrouping.html']");
		Thread.sleep(4000);
		//wp.getTitleAndCompare(page);
		//To get the value from first row of results
		wp.getTextByID("label00");
		wp.getTextByID("label01");
		wp.closeBrowser();
		
	}

	@DataProvider(name = "Credentials")
	public Object[][] loginData() throws  IOException
	{
	Object[][] arrayobject = Wrapper.getXlsData("Custom_Territory_Grouping");
	return arrayobject;
	}
}
