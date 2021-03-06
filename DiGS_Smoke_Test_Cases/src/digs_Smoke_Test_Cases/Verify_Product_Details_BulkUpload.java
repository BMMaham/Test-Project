package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Verify_Product_Details_BulkUpload {
	Wrapper wp = new Wrapper();
	@Test(dataProvider = "VerifyProduct")
	public void verifyProductDetails(String username,String url,String upc,String ot,String oc,String od,String ol,String rf,String View_Track_Info,String View_Additional_Info_Tab1,String View_Additional_Info_Tab2,String View_Additional_Info_Tab3,String View_Product_History,String status) throws InterruptedException, IOException
	{
		//Launch the browser and enter the URL
		wp.launchBrowser("firefox",url);
		Thread.sleep(3000);
		
		//To enter the user name
		wp.enterTextByID("userName",username);
		wp.clickById("loginButton");
		Thread.sleep(3000);
		System.out.println("Started Test case: Verify the product details - Bulk Upload");
		
		//Enter the UPC in the quick search
		//wp.enterTextByIDQuickSearch("searchText", upc, "//label[@style='white-space:normal']", "icon-search");
		wp.enterTextByID("searchText", upc);
	    wp.clickByClassNameLocalRowFilter("//*[@id='basictitleText']","icon-search");
		Thread.sleep(45000);
	    wp.getTextAndCompareUsingXpath("//*[@id='meta_row1']/div[3]/span[2]/span", ot);
	    wp.getTextAndCompareUsingXpath("//*[@id='meta_row2']/div[3]/span[2]/span", oc);
		wp.getTextAndCompareUsingXpath("//*[@id='meta_row3']/div[3]/span[2]/span", od);
		wp.getTextAndCompareUsingXpath("//div[@id='wrap']/div[2]/div[1]/div[3]/form/div[3]/div/div[2]/div/div/div[4]/div[3]/span[2]/span", ol);
		wp.getTextAndCompareUsingXpath("//*[@id='s2id_additionalInfoViewBean.releasingFamilyId']/a/span", rf);
		Thread.sleep(15000);
		
		//To click on 'View Track Info' and to get the data from first row
		wp.clickByXpath("//*[@id='viewTrackInfo']");
		Thread.sleep(6000);
		wp.getTextAndCompareUsingXpath("//*[@id='ui-id-2']", View_Track_Info);
		wp.getTextByXpath("//*[@id='label00']");
		wp.clickByXpath("/html/body/div[5]/div[1]/a/span");
				
		//To click on 'View Additional Info' and to verify the headers.
		wp.clickByXpath("//*[@id='viewAdditionalInfo']");
		Thread.sleep(6000);
		wp.getTextAndCompareUsingXpath("//*[@id='ui-id-3']", View_Additional_Info_Tab1);
		wp.getTextAndCompareUsingXpath("//*[@id='ui-id-4']", View_Additional_Info_Tab2);
		wp.getTextAndCompareUsingXpath("//*[@id='ui-id-5']", View_Additional_Info_Tab3);
		wp.clickByXpath("/html/body/div[5]/div[1]/a/span");
				
		//To click on 'View Product History' and to verify the headers
		wp.clickByXpath("//*[@id='viewProductHistory']");
		Thread.sleep(6000);
		wp.getTextAndCompareUsingXpath("//*[@id='ui-id-7']", View_Product_History);
		wp.getTextByXpath("//*[@id='label00']");
		wp.clickByXpath("/html/body/div[5]/div[1]/a/span");
				
		//To select the local row territories
		wp.scrollDown();
		wp.clickByXpath("//*[@id='s2id_territoryFilter']/a/div/b");
		wp.clickByXpath("/html/body/div[5]/ul/li[1]/div");
		Thread.sleep(45000); 
				
		//To verify the local row territories
		wp.tableVerificationVerifyProduct("//*[@id='territoriesTable']");
		wp.getTextAndCompareUsingXpath("//*[@id='sectionCoreScheduling']/div/div/div[2]/span[1]", status);
		wp.closeBrowser();
				
	}
	@DataProvider(name = "VerifyProduct")
	public  Object[][] loginData() throws IOException {
		
		Object[][] arrayObject =Wrapper.getXlsData("CreateUPC_BulkUpload");
		return arrayObject;
	}

}
