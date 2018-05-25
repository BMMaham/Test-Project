package digs_Smoke_Test_Cases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Invalid_Credentials {
	Wrapper wp = new Wrapper();
	
	@Test(dataProvider = "Credential")
	
			public void  invalidCredentials(String username,String url,String error_message) throws IOException, InterruptedException, AWTException {
			// TODO Auto-generated method stub
		  
			//Launch the browser and enter the URL
		    wp.launchBrowser("chrome",url);
		    Thread.sleep(6000);
		    wp.autoitAuthen();
		    Thread.sleep(3000);
			//To enter the user name
			wp.enterTextByID("userName", username);
			wp.clickById("loginButton");
			Thread.sleep(3000);
			System.out.println("Started Test case: Invalid Credential");
			//To verify the error message
			wp.getTextAndCompareUsingId("error", error_message);
			wp.closeBrowser();


}
@DataProvider(name = "Credential")
public  Object[][] loginData() throws IOException {
	
	Object[][] arrayObject =Wrapper.getXlsData("VerifyLogin_InvalidCredential");
	return arrayObject;
}
	}
