package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Automatic_OptOut 
{
Wrapper wp = new Wrapper();
	
	@Test(dataProvider = "Credential")
			public void  automaticOptOut(String username,String url,String header) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
		
			//Launch the Browser and enter the URL
			wp.launchBrowser("firefox", url);
			Thread.sleep(10000);
			wp.autoitAuthen();
		    Thread.sleep(3000);
			//Enter the user name
			wp.enterTextByID("userName", username);
			wp.clickById("loginButton");
			Thread.sleep(6000);
			System.out.println("Started Test case: Admin: Automatic opt-out  - search functionality ");
			//Click on Admin tab and select Automatic Opt-Out
			wp.clickById("searchAdminMenu");
			Thread.sleep(3000);
			wp.clickByXpath("//*[@id='searchProductMenu']");
			Thread.sleep(3000);
			//wp.getTitleAndCompare(page);
			//To select "All UMG Int'l" and add to selection. 
			wp.clickByXpath("//*[@id='R01_source']");
			Thread.sleep(3000);
			wp.clickByXpath("//*[@id='releasingTerritoryIds_add_btn']");
			Thread.sleep(6000);
			wp.scrollDown();
			//To click on Search 
			wp.clickByXpath("//*[@id='searchAutoOptOut']");
			//To verify the header of the search results grid
			wp.getTextByXpath("//*[@id='search_result']/div[1]/div");
			wp.getTextAndCompareUsingXpath("//*[@id='search_result']/div[1]/div", header);
			wp.closeBrowser();
	}
	@DataProvider(name = "Credential")
	public Object[][] loginData() throws  IOException
	{
	Object[][] arrayobject = Wrapper.getXlsData("Automatic_Optout");
	return arrayobject;
	}


}
