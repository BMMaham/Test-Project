package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class User_Profile_Change_Basic_Preferences {
Wrapper wp = new Wrapper();
	
	@Test(dataProvider = "Credential")
			public void  userProfileChangeBasicPreferences(String username,String url,String Text, String Text1,String message,String login_page_preference) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
		
		 //Launch the browser and enter the URL
		 wp.launchBrowser("firefox", url);
		 Thread.sleep(10000);
		 wp.autoitAuthen();
		    Thread.sleep(3000);
		 //To enter the user name
		 wp.enterTextByID("userName", username);
		 wp.clickById("loginButton");
		 Thread.sleep(3000);
		 System.out.println("Started Test case: User profile: Change Basic Preferences");
	     wp.getTitle();
	     //To click on user name tab and click on My Profile
	     wp.clickById("searchMyProfile");
	     wp.clickById("configLink");
	     Thread.sleep(15000);
	     //wp.getTitleAndCompare(page);
	     //To select Create page in Login Page Preference and click on Save
		 wp.scrollIntoView("//*[@id='basic-info']/div[1]");
		 Thread.sleep(3000);
		// wp.clickByXpath("//*[@id='s2id_loginPage']/a/div/b"); 
		 wp.selectDropdownValueBasicPref("LoginPage", Text, "");
		 Thread.sleep(3000);
		 wp.clickByXpath("/html/body/div[3]/ul/li[1]/div");
		 wp.scrollDown();
		/* wp.clickById("save-basic-pref");
		 Thread.sleep(5000);
		 //To verify the message
		 wp.getTextAndCompareUsingId("successAlert", message);*/
		 //To click on Search tab
		 wp.clickById("searchProduct");
		 Thread.sleep(15000);
		 //To click on user name and click on My Profile
		 wp.clickById("searchMyProfile");
		 wp.clickById("configLink");
		 Thread.sleep(15000);
		 //To verify whether Create page is getting displayed
		 wp.scrollIntoView("//*[@id='basic-info']/div[1]");
		 Thread.sleep(3000);
		 wp.getTextAndCompareUsingXpath("//*[@id='s2id_loginPage']/a/span", login_page_preference);
		 Thread.sleep(3000);
		 //To select another option from the drop down
		 wp.clickByXpath("//*[@id='s2id_loginPage']/a/div/b");
		// wp.selectDropdownValueBasicPref("LoginPage", Text1, "", Text);
		 Thread.sleep(3000);
		 wp.clickByXpath("/html/body/div[3]/ul/li[2]/div");
		 wp.scrollDown();
		 //To click on Save
		 wp.clickById("save-basic-pref");
		 Thread.sleep(3000);
		 //To verify the message
		 wp.getTextAndCompareUsingId("successAlert", message);
		 
		 wp.closeBrowser();
			
			
		}
	@DataProvider(name = "Credential")
	public  Object[][] loginData() throws IOException {
		
		Object[][] arrayObject =Wrapper.getXlsData("UserProfile_ChangeBasicPreferen");
		return arrayObject;
	}


}
