package extend_Report_Check;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import extend_Report_Check.Aspen_ART_Wrapper;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class Help {
	
  /*  static {
        System.out.println("./atu.properties");
        System.setProperty("atu.reporter.config", "./atu.properties");
    }*/

	Aspen_ART_Wrapper wp = new Aspen_ART_Wrapper();
	@Test(dataProvider = "Credential")
public void help(String url,String username,String password) throws InterruptedException
	{
		//Launch the browser and enter the URL
		wp.launchBrowser("IE", url);
		Thread.sleep(15000);
		System.out.println("Started Test Cases Title : Help");
		//Enter the Credentials and click on Login
		wp.enterTextByID("UserName", username);
		wp.enterTextByID("Password", password);
		wp.clickById("Button_Login");
		Thread.sleep(3000);
		//Verify whether the help hyperlink is in enable mode or not
		wp.verifyEnableUsingLinkText("Help");
		//Click on Help
		wp.clickByLinkText("Help");
		//Close the browser
		wp.closeBrowser();
	}
	@DataProvider(name = "Credential")
	public  Object[][] loginData() throws IOException {

	Object[][] arrayObject =Aspen_ART_Wrapper.getXlsData("Help");
	return arrayObject;
	}
}
