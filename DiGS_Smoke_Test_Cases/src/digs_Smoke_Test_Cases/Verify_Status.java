package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Verify_Status {
	Wrapper wp = new Wrapper();
	@Test(dataProvider = "VerifyStatus")
	public void verifyStatus(String username,String url,String upc,String status) throws InterruptedException, IOException
	{
		
		//Launch the browser and enter the URL
		wp.launchBrowser("firefox", url);
		Thread.sleep(3000);
		
		//To enter the user name
		wp.enterTextByID("userName", username);
		wp.clickById("loginButton");
		Thread.sleep(3000);
		System.out.println("Started Test case: Verify the status of the UPC-Create Product");
		
		//Enter the UPC in the quick search
		wp.enterTextByIDQuickSearch("searchText", upc, "//label[@style='white-space:normal']", "icon-search");
		Thread.sleep(30000);
		wp.getTextAndCompareUsingXpath("//*[@id='sectionCoreScheduling']/div/div/div[2]/span[1]", status);
		wp.closeBrowser();
				
}
	@DataProvider(name = "VerifyStatus")
	public  Object[][] loginData() throws IOException {
		
		Object[][] arrayObject =Wrapper.getXlsData("VerifyUPCStatus_CreateProduct");
		return arrayObject;
	}
}
