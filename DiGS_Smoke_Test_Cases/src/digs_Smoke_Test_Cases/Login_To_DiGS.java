package digs_Smoke_Test_Cases;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Login_To_DiGS {
	{
		//System.setProperty("atu.reporter.config", "atu.properties");
	}
	Wrapper wp = new Wrapper();
		
        @Test(dataProvider = "Credential")

		public void  login(String username,String url,String upc) throws IOException, InterruptedException, AWTException {
		// TODO Auto-generated method stub
	
		//Launch the browser and enter the URL
        wp.launchBrowser(username,url);
		Thread.sleep(6000);
		wp.autoitAuthen();
	    Thread.sleep(3000);
		//To enter the user name
		wp.enterTextByID("userName", username);
		wp.clickById("loginButton");
		
		
		
	}
@DataProvider(name = "Credential",parallel=true)
public  Object[][] loginData() throws IOException {
	
	Object[][] arrayObject =Wrapper.getXlsData("VerifyLogin_MultipleUser");
	return arrayObject;
}
	}
	
		
