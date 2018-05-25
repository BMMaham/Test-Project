package digs_Smoke_Test_Cases;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

public class Quick_Search_For_UPC {
	Wrapper wp = new Wrapper();

	@Test(dataProvider = "UPC")
	public void quickSearchForUPC(String username,String url,String upc) throws InterruptedException, IOException
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
		System.out.println("Started Test case:Search : Quick Search for UPC");
		//Enter the UPC in the quick search
		wp.enterTextByID("searchText", upc);
		wp.clickByClassName("icon-search");
	    //wp.clickByClassNameLocalRowFilter("//*[@id='basictitleText']","icon-search");
    	Thread.sleep(5000);
		//To verify whether the same UPC number is getting displayed
		wp.getTextAndCompareUsingXpath("//label[@style='white-space:normal']",upc);
		wp.closeBrowser();
		
		
		
	}
	@DataProvider(name = "UPC")
	public Object[][] upc() throws IOException
	{
		Object[][] arrayobject = Wrapper.getXlsData("Verify_QuickSearch");
		return arrayobject;
	}

}
