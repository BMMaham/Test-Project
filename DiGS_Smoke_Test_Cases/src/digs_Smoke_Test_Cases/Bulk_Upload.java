package digs_Smoke_Test_Cases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })

	
public class Bulk_Upload {
	Wrapper wp = new Wrapper();
	@Test(dataProvider = "Credentials")
	public void bulkUpload(String username,String url) throws InterruptedException, IOException
	{
		//To launch the browser
		wp.launchBrowser("firefox", url);
		Thread.sleep(3000);
		
		//To enter the user name
		wp.enterTextByID("userName", username);
		wp.clickById("loginButton");
		Thread.sleep(3000);
		System.out.println("Started Test case: Bulk Upload");
		
		//To click on Create then click on Bulk Upload
		wp.clickByXpath("//*[@id='searchMenu']");
		wp.clickById("bulkCreateMenu");
		Thread.sleep(15000);
		
		//To upload file
		wp.fileUpload();
		Thread.sleep(5000);
		wp.clickByXpath("//*[@id='bulkUploadBtn']");
		Thread.sleep(8000);
		wp.enterTextByIDTimeStamp("recapComment", "FileUpload");
		Thread.sleep(6000);
		wp.clickByXpath("/html/body/div[2]/div[11]/div/button[2]/span");
		Thread.sleep(30000);
		wp.getTextByXpath("//*[@id='label03']");
		wp.closeBrowser();
}
	@DataProvider(name = "Credentials")
	public Object[][] loginData() throws  IOException
	{
	Object[][] arrayobject = Wrapper.getXlsData("VerifyLogin_Credential");
	return arrayobject;
	}
}
