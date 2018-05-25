package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Save_And_Validate_Product_BulkUpload {
	Wrapper wp = new Wrapper();
	@Test(dataProvider = "SAV_Bulk")
	public void saveAndValidateProductBulkUpload(String username,String url,String upc) throws InterruptedException
	{
		//Launch the browser and enter the URL
		wp.launchBrowser("firefox", url);
		Thread.sleep(3000);
		
		//To enter the user name
		wp.enterTextByID("userName", username);
		wp.clickById("loginButton");
		Thread.sleep(3000);
		
		//Enter the UPC in the quick search
		wp.enterTextByIDQuickSearch("searchText", upc, "//label[@style='white-space:normal']", "icon-search");
		
		Thread.sleep(30000);
		wp.clickByXpath("//*[@id='saveAndValidateBtn']");
		Thread.sleep(15000);
		wp.closeBrowser();
		
}
	@DataProvider(name = "SAV_Bulk")
	public  Object[][] loginData() throws IOException {
		
		Object[][] arrayObject =Wrapper.getXlsData("SaveAndValidate_BulkUpload");
		return arrayObject;
	}
}


