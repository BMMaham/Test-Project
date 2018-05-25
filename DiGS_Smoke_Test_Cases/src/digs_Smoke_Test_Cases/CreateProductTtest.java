package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class CreateProductTtest {
	Wrapper wp = new Wrapper();
	@Test(dataProvider = "CreateProduct")
	public void createProduct(String username,String url,String upc,String ot,String oc,String od,String ol,String rf,String viewTrackInfoHeader,String tab1,String tab2,String tab3,String viewProductHistoryHeader,String status) throws InterruptedException, IOException
	{
		//Launch the browser and enter the URL
		wp.launchBrowser("firefox", url);
		Thread.sleep(3000);
		
		//To enter the user name
		wp.enterTextByID("userName", username);
		wp.clickById("loginButton");
		System.out.println("Started Test case: Create Product");
		Thread.sleep(3000);
		
		//To click on Create then click on Create Product
		wp.clickByXpath("//*[@id='searchMenu']");
		wp.clickByXpath("//*[@id='createProductMenu']");
		Thread.sleep(15000);
		
		//To enter the UPC and click on Get R2 info
	    wp.enterTextByID("metadata.UPC", upc);
		wp.scrollUp();
		Thread.sleep(3000);
		wp.clickById("r2Button");
		Thread.sleep(90000);
		
		//To click on Marketing Rights.Select the territories
	    wp.clickByXpath("//*[@id='marketingRightsBtn']/i"); 
	    Thread.sleep(15000);
	    wp.clickByXpath("//select[@id='marketingRightsIds']//option[@id='320_source']");
		wp.clickById("addBtnMarketingRights");
		Thread.sleep(6000);
		wp.clickByXpath("//select[@id='marketingRightsIds']//option[@id='540_source']");
		wp.clickById("addBtnMarketingRights");
		Thread.sleep(6000);
		wp.clickByXpath("//select[@id='marketingRightsIds']//option[@id='130_source']");
		wp.clickById("addBtnMarketingRights");
		Thread.sleep(3000);
		wp.clickById("savemarketingRightsButton");
		Thread.sleep(15000);
		
		//To select the Specific date and to enter the release date
		wp.clickByXpath("//*[@id='s2id_coreScheduling.releaseDate.type']/a/div/b");
		wp.clickByXpath("/html/body/div[5]/ul/li[1]/div");
		Thread.sleep(6000);
		wp.clickByXpath("//*[@id='coreScheduling.releaseDate.releaseDate']");
		Thread.sleep(2000);
		wp.clickByXpath("//*[@id='ui-datepicker-div']/div/a[2]/span");
		Thread.sleep(2000);
		wp.clickByXpath("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[7]/a");
		Thread.sleep(8000);
		
	    //To select the local row territories
		wp.scrollDown();
		wp.clickByXpath("//*[@id='s2id_territoryFilter']/a/div/b");
		wp.clickByXpath("/html/body/div[5]/ul/li[1]/div");
		Thread.sleep(45000);
		
		wp.clickByXpath("//*[@id='countryRights[85].optOutImg']");
		Thread.sleep(10000);
		wp.clickByXpath("//*[@id='countryRights[113].optOutImg']");
		Thread.sleep(10000);
	
		//To click on Save.
		wp.clickByXpath("//*[@id='saveBtn']");
		Thread.sleep(15000);
		wp.getTextAndCompareUsingXpath("//*[@id='successAlert']/h4", "Success!");
		//Enter the UPC in the quick search
				
				
				Thread.sleep(45000);
				
			    wp.getTextAndCompareUsingXpath("//*[@id='meta_row1']/div[3]/span[2]/span", ot);
				wp.getTextAndCompareUsingXpath("//*[@id='meta_row2']/div[3]/span[2]/span", oc);
				wp.getTextAndCompareUsingXpath("//*[@id='meta_row3']/div[3]/span[2]/span", od);
				wp.getTextAndCompareUsingXpath("//div[@id='wrap']/div[2]/div[1]/div[3]/form/div[3]/div/div[2]/div/div/div[4]/div[3]/span[2]/span", ol);
				wp.getTextAndCompareUsingXpath("//*[@id='s2id_additionalInfoViewBean.releasingFamilyId']/a/span", rf);		
				//To click on 'View Track Info' and to get the data from first row
			    wp.clickByXpath("//*[@id='viewTrackInfo']");
				Thread.sleep(6000);
				wp.getTextAndCompareUsingXpath("//*[@id='ui-id-2']", viewTrackInfoHeader);
				wp.getTextByXpath("//*[@id='label00']");
				wp.clickByXpath("/html/body/div[5]/div[1]/a/span");
						
				//To click on 'View Additional Info' and to verify the headers.
				wp.clickByXpath("//*[@id='viewAdditionalInfo']");
				Thread.sleep(6000);
				wp.getTextAndCompareUsingXpath("//*[@id='ui-id-3']", tab1);
				wp.getTextAndCompareUsingXpath("//*[@id='ui-id-4']", tab2);
				wp.getTextAndCompareUsingXpath("//*[@id='ui-id-5']", tab3);
				wp.clickByXpath("/html/body/div[5]/div[1]/a/span");
						
				//To click on 'View Product History' and to verify the headers
				wp.clickByXpath("//*[@id='viewProductHistory']");
				Thread.sleep(6000);
				wp.getTextAndCompareUsingXpath("//*[@id='ui-id-7']", viewProductHistoryHeader);
				wp.getTextByXpath("//*[@id='label00']");
				wp.clickByXpath("/html/body/div[5]/div[1]/a/span");
						
				//To select the local row territories
				wp.scrollDown();
				wp.clickByXpath("//*[@id='s2id_territoryFilter']/a/div/b");
				wp.clickByXpath("/html/body/div[5]/ul/li[1]/div");
				Thread.sleep(30000); 
						
				//To verify the local row territories
				wp.tableVerificationVerifyProduct("//*[@id='territoriesTable']");
				wp.clickByXpath("//*[@id='saveAndValidateBtn']");
				Thread.sleep(30000);
				wp.getTextAndCompareUsingXpath("//*[@id='sectionCoreScheduling']/div/div/div[2]/span[1]", status);

	}
	@DataProvider(name = "CreateProduct")
	public  Object[][] loginData() throws IOException {
		
		Object[][] arrayObject =Wrapper.getXlsData("CreateProduct");
		return arrayObject;
	}
}

