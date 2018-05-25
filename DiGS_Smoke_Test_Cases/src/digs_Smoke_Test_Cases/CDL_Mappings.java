package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class CDL_Mappings {
Wrapper wp = new Wrapper();
	
	@Test(dataProvider = "Credential")
			public void  cdlMappings(String username,String url,String text) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
		
			//Launch the browser and enter the URL
			wp.launchBrowser("firefox",url);
			Thread.sleep(15000);
			wp.autoitAuthen();
		    Thread.sleep(3000);
			//To enter the user name
			wp.enterTextByID("userName", username);
			wp.clickById("loginButton");
			Thread.sleep(6000);
			System.out.println("Started Test case: Admin: CDL Mappings  - search functionality");
			//To click on Admin tab and click on C/D/L Mappings
            wp.clickById("searchAdminMenu");
			Thread.sleep(6000);
			wp.clickById("searchTaskMenu");
			Thread.sleep(60000);
			//wp.getTitleAndCompare(page);
			wp.scrollDown();
			wp.clickByXpath("//*[@id='s2id_searchCountry']/a/div/b");
			Thread.sleep(6000);
			wp.clickByXpath("/html/body/div[4]/ul/li[6]/div");
			Thread.sleep(6000);
			wp.clickByXpath("//*[@id='s2id_searchCompany']/a/div/b");
			Thread.sleep(6000);
			wp.clickByXpath("/html/body/div[4]/ul/li/div");
			Thread.sleep(6000);
			wp.clickByXpath("//*[@id='s2id_searchDivision']/a/div/b");
			Thread.sleep(6000);
			wp.clickByXpath("/html/body/div[4]/ul/li/div");
			Thread.sleep(6000);
			wp.clickByXpath("//*[@id='s2id_searchLabel']/a/div/b");
			Thread.sleep(6000);
			wp.clickByXpath("/html/body/div[4]/ul/li/div");
			Thread.sleep(15000);
			wp.clickByXpath("//*[@id='s2id_localCountry']/a/div/b");
			Thread.sleep(6000);
			wp.clickByXpath("/html/body/div[4]/ul/li[35]/div");
			Thread.sleep(6000);
			wp.clickByXpath("//*[@id='s2id_localCompany']/a/div/b");
			Thread.sleep(6000);
			wp.clickByXpath("/html/body/div[4]/ul/li[4]/div");
			Thread.sleep(6000);
			wp.clickByXpath("//*[@id='s2id_localDivision']/a/div/b");
			Thread.sleep(6000);
			wp.clickByXpath("/html/body/div[4]/ul/li[1]/div");
			Thread.sleep(6000);
			wp.clickByXpath("//*[@id='s2id_localLabel']/a/div/b");
			Thread.sleep(6000);
			wp.clickByXpath("/html/body/div[4]/ul/li[1]/div");
			Thread.sleep(6000);
			wp.clickById("localMapAdd");
			//wp.clickByXpath("//form/div[2]/div[2]/div[3]/div/div/table/tr[2]/td[5]/div/input[@id='localMapAdd]'");
			Thread.sleep(6000);
			wp.getTextAndCompareUsingXpath("//*[@id='successAlert']/h4", text);
			wp.clickByXpath("//*[@id='successAlert']/a");
			Thread.sleep(6000);
			wp.tableVerification1("searchTable");
			
			wp.clickByXpath("//*[@id='dellocalcdlid']");
			wp.clickByXpath("/html/body/div[4]/div[3]/div/button[1]/span");
			
			
			/*
			//To select the Territory = American Samoa; Company = Digital Distribution American Samoa; Division = Digital Distribution ; Label = Universal Music. 
			wp.clickByXpath("//*[@id='s2id_searchCountry']/a/div/b");
			wp.clickByXpath("/html/body/div[4]/ul/li[5]/div");
			Thread.sleep(6000);
			wp.clickByXpath("//*[@id='s2id_searchCompany']/a/div/b");
			Thread.sleep(15000);
			wp.clickByXpath("/html/body/div[4]/ul/li/div");
			Thread.sleep(15000);
			wp.clickByXpath("//*[@id='s2id_searchDivision']/a/div/b");
			wp.clickByXpath("/html/body/div[4]/ul/li/div");
			Thread.sleep(15000);
			wp.clickByXpath("//*[@id='s2id_searchLabel']/a/div/b");
			wp.clickByXpath("/html/body/div[4]/ul/li/div");
			Thread.sleep(15000);
			//To click on Search
			wp.clickById("searchMapAdd");
			Thread.sleep(15000);
			//To get the datum from first row of search results grid
			//wp.getTextByXpath("//*[@id='12936777787']/td[1]");*/
			wp.closeBrowser();

}
	@DataProvider(name = "Credential")
	public  Object[][] loginData() throws IOException {
		
		Object[][] arrayObject =Wrapper.getXlsData("CDL_Mappings");
		return arrayObject;
	}
}
