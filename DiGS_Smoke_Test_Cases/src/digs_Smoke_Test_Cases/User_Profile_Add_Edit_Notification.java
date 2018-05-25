package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class User_Profile_Add_Edit_Notification {
	Wrapper wp = new Wrapper();
	
	@Test(dataProvider = "Credential")
			public void  userProfileAddEditNotification(String username,String url,String territory) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
		//Launch the browser and enter the URL
		wp.launchBrowser("firefox", url);
		Thread.sleep(8000);
		wp.autoitAuthen();
	    Thread.sleep(3000);
		//To enter the user name
		wp.enterTextByID("userName", username);
		wp.clickById("loginButton");
		Thread.sleep(15000);
		System.out.println("Started Test case: User Profile : Add/Edit Notification");
	    wp.getTitle();
	    //To click on user name tab and My Profile 
	    wp.clickById("searchMyProfile");
	    wp.clickById("configLink");
	    Thread.sleep(30000);
	   // wp.getTitleAndCompare(page);
		 //To select the Notification type
		 wp.clickByXpath("//*[@id='s2id_notificationTypeIds']/a/div/b");
		 //To select the territory
		 wp.clickByXpath("/html/body/div[3]/ul/li[24]/div");
		 wp.selectValueUsingVisibleText("countryIds_source", territory);
		 Thread.sleep(15000);
         wp.clickById("countryIds_add_btn");
         Thread.sleep(45000);
         //To select the check box
         wp.scrollUp();
         wp.clickById("email-chkbox");
         //To click on Add button
		 wp.scrollIntoView("//*[@id='notification-section-form-id']/div[7]/div[1]");
		 Thread.sleep(15000);
		 wp.clickById("notification-add-button-id");
		 Thread.sleep(25000);
		 //To click on Notification
		 wp.clickByXpath("//*[@id='basic-info']/div[1]"); 

	     //wp.scrollDown();
	     Thread.sleep(90000);
	     //To get the first page of data from the grid and to remove the notification.
	     //wp.clickByXpath("//a[@class='delete-control']/i");
	    //wp.tableVerification("//*[@id='notifications_table']");
	     wp.clickByXpath("//div[@id='label012']/div/a[2]/i");//*[@id="notifications_table_controls_13229542532"]/a[2]/i
	     wp.closeBrowser();
			
		}
	@DataProvider(name = "Credential")
	public  Object[][] loginData() throws IOException {
		
		Object[][] arrayObject =Wrapper.getXlsData("UserProfile_AddEditNotification");
		return arrayObject;
	}

}
