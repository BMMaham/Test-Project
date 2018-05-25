package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import digs_Smoke_Test_Cases.Wrapper;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Investigate_Summary {
	Wrapper wp = new Wrapper();
	@Test(dataProvider = "Credential")
	public void help(String url,String username,String password,String header1,String header2,String header3,String header4,String header5,String order_ID,String header6,String header7,String ingestion_ID,String header8,String header9,String upc,String header10) throws InterruptedException
	{
		//Launch the browser and enter the URL
		wp.launchBrowser("chrome", url);
		Thread.sleep(15000);
		//Enter the Credentials and click on Login
		wp.enterTextByID("TextBox_UserName", username);
		wp.enterTextByID("TextBox_Password", password);
		wp.clickById("Button_Login");
		Thread.sleep(3000);
		//Click on Investigation Summary
		wp.mouseHover("Investigation Summary");
		Thread.sleep(5000);
		//Click on Summary
		//wp.clickByLinkText("Summary");
		wp.clickByXpath("//*[@id='dropmenudiv']/a[1]");
		//Verify the header ‘Summary’
		wp.getTextAndCompareUsingXpath("/html/body/table[2]/tbody/tr/td/div/div", header1);
		//Verify the header ‘Audio Orders (type = 'DAV')’
		wp.getTextAndCompareUsingXpath("//*[@id='Form1']/div[3]/div", header2);
		//Verify the header ‘Audio Orders (type = 'LOT')’
		wp.getTextAndCompareUsingXpath("//*[@id='Form1']/div[4]/div", header3);
		//Verify the header ‘Ingestion Orders (type = 'INGEST')’
		wp.getTextAndCompareUsingXpath("//*[@id='Form1']/div[5]/div", header4);
		
		//Click on ‘Investigation Summary’
		wp.mouseHover("Investigation Summary");
		//Thread.sleep(2000);
		//Click on ‘Order ID’
		wp.clickByXpath("//*[@id='dropmenudiv']/a[2]");
		//Verify the header ‘Enter Order ID’
		wp.getTextAndCompareUsingXpath("/html/body/table[2]/tbody/tr/td/div/div", header5);
		//Enter the Order ID and click on Clear
		wp.enterTextByID("TextBox_OrderID", order_ID);
		wp.clickById("Button_Clear");
		//Check whether the field is empty 
		wp.getTextAndCompareVerifyEmpty("TextBox_OrderID");
		//Enter the Order ID and click on Investigate
		wp.enterTextByID("TextBox_OrderID", order_ID);
		wp.clickById("Button_Investigate");
		//Verify the header 
		/*wp.getTextAndCompareUsingXpath("//*[@id='OrderDetailsPanel']/p/table[1]/tbody/tr[1]/td/label", header6);
		//Verify the Order id inside the information box
		wp.getTextAndCompareUsingXpath("//*[@id='OrderDetailsTable']/tbody/tr[3]/td[2]",order_ID);
		//Click on Investigation Summary */
		wp.mouseHover("Investigation Summary");
		Thread.sleep(5000);
		//Click on Ingestion
		wp.clickByXpath("//*[@id='dropmenudiv']/a[3]");
		//Verify the header
		wp.getTextAndCompareUsingXpath("/html/body/table[2]/tbody/tr/td/div/div", header7);
		//Enter value in the Search String
		wp.enterTextByID("TextBox_SearchString", ingestion_ID);
		//Select the Hint as Order ID and click on Investigate
		wp.selectDropdownValueUsingIndexUsingID("DropDownList_Hint", 4);
		wp.clickById("Button_Investigate");
		//Verify the header
		wp.getTextAndCompareUsingXpath("/html/body/table[2]/tbody/tr/td/div/div", header8);
		//Click on Investigation Summary
		wp.mouseHover("Investigation Summary");
		Thread.sleep(5000);
		//Click on Product
		wp.clickByXpath("//*[@id='dropmenudiv']/a[4]");
        //Verify the header
        wp.getTextAndCompareUsingXpath("/html/body/table[2]/tbody/tr/td/div/div", header9);
        //Enter the UPC and click on Investigate
        wp.enterTextByID("TextBox_SearchString", upc);
        wp.clickById("Button_Investigate");
        //Verify the header
        wp.getTextAndCompareUsingXpath("/html/body/table[2]/tbody/tr/td/div/div", header10);
        //Close the browser
        wp.closeBrowser();
		
	}
	@DataProvider(name = "Credential")
	public  Object[][] loginData() throws IOException {

	Object[][] arrayObject =Wrapper.getXlsData("Investigate_Summary");
	return arrayObject;
	}
	
}
