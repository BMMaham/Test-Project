package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Chart_Rules_Search {
	Wrapper wp = new Wrapper();
	@Test(dataProvider = "Credentials")
	public void chartRulesSearch(String username,String url) throws InterruptedException, IOException
	{
		//Launch the browser and enter the URL
		wp.launchBrowser("firefox", url);
		Thread.sleep(6000);
		wp.autoitAuthen();
	    Thread.sleep(3000);
		//To enter the user name
		wp.enterTextByID("userName", username);
		wp.clickById("loginButton");
		Thread.sleep(3000);
		System.out.println("Started Test case: Admin:Chart Rules - Verify Search functionality");
		//To click on Admin tab and click on Chart rule
		wp.clickById("searchAdminMenu");
		Thread.sleep(3000);
		wp.clickById("searchAssetMenu");
		Thread.sleep(15000);
		//wp.getTitleAndCompare(page);
		//To select all territories and add to selection.
		wp.clickByXpath("//div[@class='tr_select_btns']//input[@id='countryIds_add_all_btn']");
		//To click on Search
		wp.clickById("chartRuleSearch");
		Thread.sleep(3000);
		//To get the datum from first row of search results grid
		//wp.getTextByID("label9390377225090");
		wp.closeBrowser();
	}

	@DataProvider(name = "Credentials")
	public Object[][] loginData() throws  IOException
	{
	Object[][] arrayobject = Wrapper.getXlsData("Chart_Rules");
	return arrayobject;
	}

}
