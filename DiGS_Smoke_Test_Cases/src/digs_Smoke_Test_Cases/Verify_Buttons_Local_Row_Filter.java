package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Verify_Buttons_Local_Row_Filter {
	Wrapper wp = new Wrapper();

	@Test(dataProvider = "UPC")
	public void localRowFilter(String username,String url,String upc,String marketingRights_header1,String marketingRights_header2,String marketingTerritory_header,String autoExcluded_header,String legalRights_header1,String legalRights_header2,String exception_header1,String exception_header2) throws InterruptedException, IOException
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
	wp.getTextAndCompareUsingId("searchProduct", "Search");
	//To enter the UPC in quick search and search the product

	wp.enterTextByID("searchText", upc);
	Thread.sleep(6000);
	wp.clickByClassName("icon-search");
	//wp.enterTextByIDQuickSearch("searchText", upc, "//label[@style='white-space:normal']", "icon-search");
	
	Thread.sleep(6000);
	//wp.getTitleAndCompare(page);
	//To click on Local Row Filter
	wp.scrollDown();
	wp.clickByXpath("//*[@id='s2id_territoryFilter']/a/div/b");
	//To select 'All NA(1)'.
	wp.clickByXpath("/html/body/div[5]/ul/li[3]/div");
	//To click on Marketing rights icon and verify the header in the pop up
	Thread.sleep(15000);
	wp.clickByXpath("//*[@id='marketingRightsBtn']/i");
	Thread.sleep(3000);
	//To verify whether the expected header is getting displayed
	wp.getTextAndCompareUsingXpath("//*[@id='sectionMarketingrightsPopUp']/div/div[1]", marketingRights_header1);
	wp.getTextAndCompareUsingXpath("//*[@id='sectionMarketingrightsPopUp']/div/div[4]/div[1]", marketingRights_header2);
		
	//To close the pop up.
	wp.clickByXpath("/html/body/div[5]/div[1]/a/span");
	//To click on Marketing Territories and verify the headers in the pop up.
	wp.clickByXpath("//*[@id='totalMarketingTerritoriesPopup']/i");
	wp.getTextAndCompareUsingXpath("//*[@id='ui-id-3']", marketingTerritory_header);
	//To close the pop up.
	wp.clickByXpath("/html/body/div[5]/div[11]/div/button"); 
	//To click on Auto Excluded and to verify the headers in the pop up
	wp.clickByXpath("//*[@id='totalMarketingAutoExcludedPopup']/i");
	wp.getTextByXpath("//*[@id='ui-id-4']");
	wp.getTextAndCompareUsingXpath("//*[@id='ui-id-4']", autoExcluded_header); 
	wp.clickByXpath("/html/body/div[5]/div[11]/div/button");
	//To verify Legal Rights received from GRCS
	wp.clickByXpath("//*[@id='legalRightsBtn']/i");
	wp.getTextByXpath("//*[@id='sectionLegalrightsPopUp']/div/div[1]/div[1]");
	wp.getTextAndCompareUsingXpath("//*[@id='sectionLegalrightsPopUp']/div/div[1]/div[1]", legalRights_header1);
	wp.getTextAndCompareUsingXpath("//*[@id='sectionLegalrightsPopUp']/div/div[3]/div[1]", legalRights_header2);
	wp.clickByXpath("/html/body/div[5]/div[1]/a/span");
	//To click on Total Territories
	wp.clickByXpath("//*[@id='totalTerritoriesPopup']/i");
	//To get the text from the first row to know whether datum is getting displayed.
	wp.getTextByID("legalRightsTotalTerritoriesTable_info");
	wp.getTextByXpath("//*[@id='label00']");
	//To close the pop up.
	wp.clickById("closeTotalTerritoriesButton");
	//To verify the Add/ViewExceptions
	wp.scrollUp();
	wp.clickById("managementExceptionBtn");
	wp.getTextAndCompareUsingXpath("//*[@id='sectionCoreManagementExceptionPopUp']/div/div[1]/div[1]", exception_header1);
	wp.getTextAndCompareUsingXpath("//*[@id='sectionCoreManagementExceptionPopUp']/div/div[3]/div[1]", exception_header2);
	//To close the pop up
	wp.clickByXpath("/html/body/div[5]/div[1]/a/span");
	//To verify whether the user can click on Export Territory option.
	wp.scrollDown();
	wp.clickById("exportTerritoriesTable");
	wp.closeBrowser();
	}
	@DataProvider(name = "UPC")
	public Object[][] upc() throws IOException
	{
		Object[][] arrayobject = Wrapper.getXlsData("VerifyUPC_localFilter");
		return arrayobject;
	}
}
