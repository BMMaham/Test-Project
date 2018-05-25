package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Advanced_Search {
Wrapper wp = new Wrapper();

@Test(dataProvider = "Credentials")

public void advancedSearch(String username,String url,String Version_Title,String Product_Configuration) throws InterruptedException, IOException
{
	//Launch the browser and enter the URL
	wp.launchBrowser("firefox", url);
	Thread.sleep(8000);
	wp.autoitAuthen();
    Thread.sleep(3000);
	//Enter the user name
	wp.enterTextByID("userName", username);
	//Click on Login
	wp.clickById("loginButton");
	Thread.sleep(3000);
	System.out.println("Started Test case Title: Search : Advanced Search");
	//Click on Search header
	wp.clickById("searchProduct");
	Thread.sleep(30000);
	//Click on Core/Local results = Core
	wp.clickByXpath("//*[@id='s2id_searchResultLevelStr']/a/div/b");
	wp.clickByXpath("/html/body/div[5]/ul/li[1]/div");
	Thread.sleep(3000);
	//set Version title = Uncut, Video Category = Video under Advanced search and click on Search button
	wp.scrollIntoView("//*[@id='advchevronB']");
	wp.clickById("advtitleText");
	wp.enterTextByID("releaseSearchRequest.versionTitle.value", Version_Title);
	wp.clickByXpath("//select[@id='videoCategory_source']//option[@id='MUSVID_source']");
	Thread.sleep(3000);
	wp.scrollIntoView("//*[@id='s2id_deliveryCategory']/ul");
	Thread.sleep(3000);
	wp.clickByXpath("//div[@class='tr_select_btns']//input[@id='videoCategory_add_btn']");
	Thread.sleep(6000);
	wp.scrollDown();
	wp.clickById("searchProductButton");
	Thread.sleep(8000);
	wp.scrollDown();
	Thread.sleep(15000);
	//To verify the Version Title, Product Configuration 
	wp.clickByXpath("//*[@id='label01']/a");
	Thread.sleep(3000);
	wp.getTextAndCompareUsingXpath("//label[contains(text(),'Uncut - Closed Captioned')]", Version_Title);
	wp.getTextAndCompareUsingXpath("//*[@id='meta_row1']/div[2]/span[2]/span", Product_Configuration);
	wp.closeBrowser();
}

@DataProvider(name = "Credentials")
public Object[][] loginData() throws  IOException
{
Object[][] arrayobject = Wrapper.getXlsData("Advanced_Search");
return arrayobject;
}


}
