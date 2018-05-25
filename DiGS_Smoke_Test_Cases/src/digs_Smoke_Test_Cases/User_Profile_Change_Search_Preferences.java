package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class User_Profile_Change_Search_Preferences {
	
Wrapper wp = new Wrapper();
	
	@Test(dataProvider = "Credential")
			public void  userProfileChangeSearchPreferences(String username,String url) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
		
		//Launch the browser and enter the URL
			wp.launchBrowser("firefox", url);
			Thread.sleep(3000);
			wp.autoitAuthen();
		    Thread.sleep(3000);
			//To enter the user name
			wp.enterTextByID("userName", username);
			wp.clickById("loginButton");
			Thread.sleep(6000);
			System.out.println("Started Test case: User profile : Change Search Preferences");
		    //To click on user name and click on My Profile
		    wp.clickById("searchMyProfile");
		    wp.clickById("configLink");
		  Thread.sleep(8000);
		  //wp.getTitleAndCompare(page);

		  //To select Product Configuration = * Album and add it to the selection 
		    wp.scrollIntoView("//*[@id='basic-info']/div[1]");
		    Thread.sleep(15000);
		    wp.clickByXpath("//*[@id='tab_japanese']/a");
		   wp.scrollIntoView("//*[@id='basic-info']/div[1]");
		    wp.clickByXpath("//select[@id='releaseConfigurations_source']//option[@id='G_Album_source']");
		    wp.clickById("releaseConfigurations_add_btn");
		  Thread.sleep(15000);
		  wp.verifyNumberOfOptions("releaseConfigurations", 7);
		    wp.scrollDown();
		    //To save
		    wp.clickById("saveSearchPreferencesButton");
		   Thread.sleep(15000);
		   //To click on Search tab and to verify whether 7 configurations are auto populated
		    wp.clickById("searchProduct");
		    Thread.sleep(8000);
		    wp.verifyNumberOfOptions("releaseConfigurationIds", 7);
		    Thread.sleep(3000);
		    //To click on user name and click on My Profile 
		    wp.clickById("searchMyProfile");
		    wp.clickById("configLink");
		    Thread.sleep(8000);
		    //To verify whether 7 configurations are getting displayed and to remove it.
		    wp.scrollIntoView("//*[@id='basic-info']/div[1]");
		    Thread.sleep(15000);
		    wp.clickByXpath("//*[@id='tab_japanese']/a");
		    Thread.sleep(15000);
		    wp.verifyNumberOfOptions("releaseConfigurations", 7);
		    wp.clickById("releaseConfigurations_remove_btn");
		    Thread.sleep(15000);
		    wp.verifyNumberOfOptions("releaseConfigurations", 0);
		    wp.scrollDown();
		    //To save
		    wp.clickById("saveSearchPreferencesButton");
		    Thread.sleep(5000);
		    wp.closeBrowser();
		    
}
	@DataProvider(name = "Credential")
	public  Object[][] loginData() throws IOException {
		
		Object[][] arrayObject =Wrapper.getXlsData("UserProfile_ChangeSearchPrefer");
		return arrayObject;
	}
}
