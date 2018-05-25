package digs_Smoke_Test_Cases;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

	
	@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
	public class Wrapper {
		int i = 1;
		{
			
			}
		RemoteWebDriver driver;
		/**
		 * This method is used to launch the browser
		 * @param browsername : It will take the name of the browser which has to launch
		 * @param url : It will take the URL 
		 * @throws AWTException 
		 * @throws InterruptedException 
		 */
		public void launchBrowser(String browsername, String url)
		{
			try {
				if (browsername.equalsIgnoreCase("firefox")){
					//ProfilesIni profile = new ProfilesIni();
					// FirefoxProfile ffprofile = profile.getProfile("AUTOMATIONPROFILE");
					// driver = new FirefoxDriver();
					//driver = new FirefoxDriver();
					// System.setProperty("webdriver.gecko.driver","C:\\AUTOMATION\\Selenium\\DiGS_Smoke_Test_Cases\\drivers\\geckodriver.exe");
					//driver = new FirefoxDriver();
					System.setProperty("webdriver.gecko.driver","C:\\AUTOMATION\\Selenium\\DiGS_Smoke_Test_Cases\\drivers\\geckodriver.exe");	
						File  pathToBinary = new File("C:\\Program Files\\Nightly\\firefox.exe");
						FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
							//FirefoxProfile firefoxProfile = new FirefoxProfile();
							 
							ProfilesIni profile = new ProfilesIni();
							FirefoxProfile ffprofile = profile.getProfile("AUTOMATIONPROFILE");
							//FirefoxOptions fo = new FirefoxOptions().setProfile(ffprofile);
							FirefoxOptions fo = new FirefoxOptions().setBinary(ffBinary).setProfile(ffprofile);
							driver = new FirefoxDriver(fo);
				} else if (browsername.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver", "C:\\AUTOMATION\\Selenium\\DiGS_Smoke_Test_Cases\\drivers\\chromeDriver.exe");
					driver = new ChromeDriver();
				} else if (browsername.equalsIgnoreCase("IE")){
					System.setProperty("webdriver.ie.driver", "C:\\AUTOMATION\\Selenium\\DiGS_Smoke_Test_Cases\\drivers\\IE\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				}
				else {
					System.out.println("Browser does not exists");
				}
				
				//maximize the browser
				driver.manage().window().maximize();	
				

				ATUReports.setWebDriver(driver);
				ATUReports.indexPageDescription = "DiGS Smoke Test Cases";
				ATUReports.setAuthorInfo("B.M.MAHAM",  Utils.getCurrentTime(), "1.0");
				
				//driver.navigate().to("url");
				
				//launch URL
				driver.get(url);
				ATUReports.add("Browser launched successfully", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch (WebDriverException  e) {
				e.printStackTrace();
				ATUReports.add("Browser does not launched", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			catch(Exception e)
			{
			}
		}
		/**
		 * This method is used to
		 *  locate the element with ID and used to enter value to element
		 * @param idvalue : This argument will take the locater ID value
	     * @param inputvalue : This argument will take the string value which has to passed.
		*/
		public void enterTextByID(String idvalue, String data)
		{
			try { 
			driver.findElement(By.id(idvalue)).clear(); 
			
			
			driver.findElement(By.id(idvalue)).sendKeys(data);
			ATUReports.add(data  + "-value is entered", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			} catch (NoSuchElementException e) {
			//System.out.println("Element does not exist");
				ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			
		    } catch (WebDriverException e) {
				//System.out.println("Browser does not exist");
		    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		}
		/**
		 * 
	     * This method is used locate the element by Name and click the element 
	     * @param name : It will take argument with element locater name.
		*/
		public void clickByName(String name)
		{
			try {
			driver.findElement(By.name(name)).click();
			    ATUReports.add(name+"-Button is clicked successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			} catch (NoSuchElementException e) {
			    ATUReports.add("Expected Button does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		   }catch (WebDriverException ee){
			    ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			   
		   }
		}
		/**
		 * This method is used to get the value from the element using Xpath.  
		 * @param xpathname : It will take the xpath value of element.
		 * @return : this method return the value of the element.
		 */
		public String getTextByXpath(String xpathname)
		{
			String value = null;
			try {
				value = driver.findElement(By.xpath(xpathname)).getText();
				ATUReports.add("Text: "+value +"  exists", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			} catch (NoSuchElementException e) {
				ATUReports.add("Expected Text does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}catch (WebDriverException ee){
			    ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			return value;
		}
		/**
		 * This method is used to convert the string values to lower case and compare the values which are got from application and static string.
		 * @param value : this argument is used to take the value of the element from application
		 * @param inputtext : this argument will take the input text value what we provide to compare with application value.
		 * @return
		 */
		public void verifyText(String value, String inputtext)
		{
			boolean verifyflag = false;
			try {
				if (value.toLowerCase().equals(inputtext.toLowerCase())){
					verifyflag = true;
					ATUReports.add("Verified the Text:"+value, LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));			
				}
				else {
					verifyflag = false;
					ATUReports.add("Expected Text is not there", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
			} catch (NoSuchElementException e) {
				    ATUReports.add("Expected Element does not exists to verify the text", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}catch (WebDriverException ee){
				    ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
		}
		/**
		 * This method is used to click on- By using Xpath
		 * @param xpathvalue
		 */
		public void clickByXpath(String xpathvalue)
		{
			try{
				driver.findElement(By.xpath(xpathvalue)).click();
				//new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathvalue))).click();
				
				ATUReports.add(xpathvalue+"-Clicked on Successfully", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(NoSuchElementException e){
				ATUReports.add("Expected value/text does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
				e.printStackTrace();
				System.out.println("Browse is not exist");
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		}
		/**
		 * This method is used to close the browser
		 */
		public void closeBrowser()
		{
			try {
				driver.close();
				
			} catch (WebDriverException e) {
				
				//System.out.println("Browser does not exist");
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				
			}
		}
		/**
		 * This method is used to locate the element by LinkText and click the element
		 * @param linkname : It is used to take the linktext name
		 */
		public void clickByLinkText(String linkname)
		{
			try {
				
				driver.findElement(By.linkText(linkname)).click();
				ATUReports.add(linkname+"-link has been clicked successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			} catch (NoSuchElementException e) {
				ATUReports.add("Expected link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				//System.out.println("Element does not exist");
				
			}catch (WebDriverException ee){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		}
		/**
		 * This method is used to locate the element by ClassName and click the element
		 * @param classname : It is used to take the Class name of the element
		 */
		public void clickByClassName(String classname)
		{
			try {
				//new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.className(classname))).click();
				driver.findElement(By.className(classname)).click();
				ATUReports.add(classname+"-link has been clicked", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			} catch (NoSuchElementException e) {
				//System.out.println("No such element");
				ATUReports.add("Expected link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}catch (WebDriverException ee){
				//System.out.println("Browser does not present");
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
		}
		/**
		 * This method is used to read the data from excel
		 * @param sheetName : It will take the sheet name of the excel
		 * @return : It will return the multidimensional data
		 * @throws IOException
		 */
		public static String[][] getXlsData(String sheetName) throws IOException
		{
			String[][] data = null;
			
			try {
				
				FileInputStream fis = new FileInputStream(new File("C:\\AUTOMATION\\Selenium\\DiGS_Smoke_Test_Cases\\testData\\testData.xlsx"));
				
				XSSFWorkbook wb = new XSSFWorkbook(fis);
				//get the sheet name
				XSSFSheet ws = wb.getSheet(sheetName);
				
				//get the no.of rows and columns\
				int rowsCount = ws.getLastRowNum();
				
				int columnCount = ws.getRow(1).getLastCellNum();
				
				data = new String[rowsCount][columnCount];
				
			
				for(int i = 1;i<=rowsCount;i++){
					try {
						XSSFRow rowdata = ws.getRow(i);
						for (int j=0;j<columnCount;j++){
							String cellValue = "";
						
							try {
								
								cellValue = rowdata.getCell(j).getStringCellValue();							
							}catch(NullPointerException e){
							}
							//add data to data array
							data[i-1][j]= cellValue;
						
							}	
					
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
				fis.close();
				//wb.close();
			}catch(NoSuchFileException e){
				ATUReports.add("File does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
				
			return data;
			
		}
		/**
		 * This method is used to click on- by using Id
		 * @Param:id value
		 */
		public void clickById(String idValue)
		{
			try{
				driver.findElement(By.id(idValue)).click();
				ATUReports.add(idValue +"-Clicked on Successfully", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(NoSuchElementException e){
				ATUReports.add("Expected button/link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			}
		/**
		 * This method is used to get the Title
		 */
		public void getTitle()
		{
			driver.getTitle();
		}
		/**
		 * This method is used to get the text using Id and used to verify the text
		 * @Param:id value
		 */
		public void getTextAndCompareUsingId(String Value,String Text)
		{
			try
			{
		String w =driver.findElement(By.id(Value)).getText();
		System.out.println(w+" is getting displayed");
		if(w.contains(Text))
		{
			//ATUReports.add(Text, Text, Value, Value, LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			ATUReports.add(Text+" -Expected message/Text/Link is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		
			
		}
		else
		{
			
			ATUReports.add(Text+" Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
			}
			catch(NoSuchElementException e){
			ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
			}
		/**
		 * This method is used to select the drop down value using Class Name and to verify the text
		 * @Param: class name and id value
		 */
		public void selectDropdownValueAndVerifyText(String Value,String Text1,String Value1,String Text)
		{ 
			try
			{
			Select dropdown = new Select(driver.findElement(By.className(Value)));
			dropdown.selectByVisibleText(Text1);;
			String w =driver.findElement(By.id(Value1)).getText();
			if(w.contains(Text))
			{
				ATUReports.add("Expected message/Text/Link is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
				
			}
			else
			{
				
				ATUReports.add("Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
				}
				catch(NoSuchElementException e){
				ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
			
		}
		/**
		 * This method is used to locate the element by Xpath and used to verify the text
		 * @Param: Xpath
		 */
		@Test(alwaysRun=true)
		public void getTextAndCompareUsingXpath(String Value,String Text)
		{
			{
				try
				{
			String w =driver.findElement(By.xpath(Value)).getText();
			System.out.println(w+" is getting displayed");
			if(w.contains(Text))
			{
				ATUReports.add(Text+"  Expected message/Text/Link is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
				
			}
			else
			{
				
				ATUReports.add(Text +"-Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
				}
				catch(NoSuchElementException e){
				ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
					e.printStackTrace();
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
				
				}
		}
		/**
		 * This method is used to locate the element by Class Name and used to verify the text
		 * @Param: Class Name
		 */
		public void getTextandCompare_Class(String Value,String Text)
		{
			{
				try
				{
			String w =driver.findElement(By.className(Value)).getText();
			System.out.println(w+" is getting displayed");
			if(w.contains(Text))
			{
				ATUReports.add(Text+" Expected message/Text/Link is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
				
			}
			else
			{
				
				ATUReports.add(Text+" Expected message does not exsist", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
				}
				catch(NoSuchElementException e){
					System.out.println("Element does not exist");
					ATUReports.add("Element does not exist", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
					System.out.println("Browse is not exist");
					ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
				
				}
		}
		/**
		 * This method is used to locate the element by CSS selector and used to verify the text
		 * @Param: Class Name
		 */
		public void getTextandCompareUsingCSSselector(String Value,String Text)
		{
			{
				try
				{
			String w =driver.findElement(By.cssSelector(Value)).getText();
			System.out.println(w+" is getting displayed");
			if(w.contains(Text))
			{
				ATUReports.add(Text+" Expected message/Text/Link is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
				
			}
			else
			{
				
				ATUReports.add(Text+" Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
				}
				catch(NoSuchElementException e){
				ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
				
				}
		}
		/**
		 * This method is used to get the text by Id
		 * @Param: Id
		 */
		public void getTextByID(String Value)
		{
			{
				try
				{
			String w =driver.findElement(By.id(Value)).getText();
			System.out.println(w);
			
				ATUReports.add("The value in the first row of the grid is "+w, LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
				}
				catch(NoSuchElementException e){
				ATUReports.add("Element to verify the message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
				
				}
		}
		/**
		 * This method is used to select the drop down value by Class Name
		 * @Param: Class Name
		 */
		public void selectDropdownValue(String Value,int Index)
		{ 
			try
			{
			Select dropdown = new Select(driver.findElement(By.className(Value)));
			dropdown.selectByIndex(Index);
				ATUReports.add(Value+"-value got selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				
			}
		       	catch(NoSuchElementException e){
				ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
			
		}
		/**
		 * This method is used to scroll up the web page
		 */
		public void scrollUp()
		{
			try
			{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,-250)","");
			}
			catch(NoSuchElementException e){
				ATUReports.add("Unable to scroll: The field does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Unable to scroll Up", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
			
		}
		/**
		 * This method is used to scroll the web page to the point where the specific web element will be visible using xpath
		 * @param Value: It is used to take the xpath
		 * @throws InterruptedException
		 */
		public void scrollIntoView(String Value) throws InterruptedException
		{
			try
			{
				WebElement element= driver.findElement(By.xpath(Value));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
				Thread.sleep(500);
				ATUReports.add("Scrolled successfully", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				//driver.findElement(By.xpath(Value)).click();
				}
			catch(NoSuchElementException e){
				ATUReports.add("Unable to scroll: The field does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Unable to scroll", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
			
		}
		/**
		 * This method is used to scroll the web page to the point where the specific web element will be visible using ID
		 * @param Value: It is used to take the ID
		 * @throws InterruptedException
		 */
		public void scrollIntoViewUsingID(String Value) throws InterruptedException
		{
			try
			{
				WebElement element= driver.findElement(By.id(Value));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
				Thread.sleep(500);	
				//driver.findElement(By.xpath(Value)).click();
				}
			catch(NoSuchElementException e){
				ATUReports.add("Unable to scroll: The field does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Unable to scroll", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
			
		}
		/**
		 * This method is used to scroll down the web page
		 */
		public void scrollDown()
		{
			try
			{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,350)","");
			}
			catch(NoSuchElementException e){
				ATUReports.add("Unable to scroll Down", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
		}
		/**
		 * This method is used to select the value from the drop down by using ID
		 * @param Value: This is used to take the ID 
		 * @param Text: This is used to take the visible text
		 */
		public void selectValueUsingVisibleText(String Value,String Text)
		{
			try
			{
			Select dropdown = new Select(driver.findElement(By.id(Value)));
			dropdown.selectByVisibleText(Text);
				ATUReports.add(Text+"-value got selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				
			}
		       	catch(NoSuchElementException e){
				ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
		}
		/**
		 * This method is used to count the number of options available and to compare it with expected value using ID
		 * @param Value: This is used to take the ID
		 * @param Size: This is used to take the expected count
		 */
		public void verifyNumberOfOptions(String Value,int Size)
		{
			try
			{
			Select dropdown1 = new Select(driver.findElement(By.id(Value)));
		    List<WebElement> options= dropdown1.getOptions();
		    System.out.println(options.size());
			if(options.size()==Size)
			{
				ATUReports.add("Expected No.of options "+options.size()+"are getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				
			}
			else
			{
				ATUReports.add("Expected No.of options "+Size+"are not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				
				}
			}
		       	catch(NoSuchElementException e){
				ATUReports.add("Element to get the number of options does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
		}
		/**
		 * This method is used to accept the alert present
		 * @return 
		 */
		public Alert alertAccept()
		{
		try
		{
			Alert alert =driver.switchTo().alert();
	return alert;
		}
		catch(NoSuchElementException e){
			ATUReports.add("Pop up is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		return null;
		}
		/**
		 * This method is used to get the first 10 data from the grid and to verify whether expected datum is getting displayed in the first page and to remove the notification.
		 * @throws InterruptedException 
		 */
		public void tableVerification(String Value) throws InterruptedException
        {
        try
        {
        	 int i=0; 
        	//To locate table. 
        	WebElement mytable = driver.findElement(By.xpath(Value));
        	//To locate rows of table.
        	List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
        	//To calculate no of rows.
        	int rows_count = rows_table.size();
        	//Loop will execute till the last row of first page of the table. 
        	for (int row=0; row<rows_count; row++)
        	{ 
        		
        		//To locate columns(cells) of that specific row. 
        		List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); 
        		//To calculate no of columns(cells) In that specific row. 
        		int columns_count = Columns_row.size(); 
        		System.out.println("Number of cells In Row "+row+" are "+columns_count);
        		//Loop will execute till the last cell of that specific row.
        		for (int column=0; column<columns_count; column++)
        		{ 
        			//To retrieve text from that specific cell. 
        			String celtext = Columns_row.get(column).getText();
        			System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
        			//driver.findElement(By.xpath("//*[@id='notifications_table_controls_939048559600']/a[2]/i")).click();
        			if(celtext.contains("CU"))
        			{
        				ATUReports.add("Expected data is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
        				driver.findElement(By.xpath("//*[@id='notifications_table_controls_13226301711']/a[2]/i")).click();
        				//driver.findElement(By.xpath("//td[contains(text(),'CU')]/following-sibling[//a[@data-id='939048559600']]")).click();
        			    ATUReports.add("Delete option Clicked on Successfully", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
             
        				i++;
        				break;
        				
        			}
        		}
        	}
        	if(i==0)
        	{
        		WebElement element= driver.findElement(By.xpath("//*[@id='notification-section-form-id']/div[7]/div[1]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
					
        		
       		 Thread.sleep(15000);
        	driver.findElement(By.xpath("//*[@id='notification-cancel-button-id']")).click();
			ATUReports.add("Clear button Clicked on Successfully", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
        	}
        	
        }
        catch(NoSuchElementException e)
        {
	          ATUReports.add("Expected grid is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
	    }   
        catch(WebDriverException e)
        {
	          ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
	     }
	}
		/**
		 * This method is used to upload workbook from local path.
		 * @throws IOException 
		 */
		public void fileUpload() throws IOException
		{
		try
		{
			driver.findElement(By.id("uploadRecapFile")).click();
			//WebElement upload=driver.findElement(By.name("theFile"));
			
			Runtime.getRuntime().exec("C:/AUTOMATION/Selenium/DiGS_Smoke_Test_Cases/testData/FileUpload/FileUpload.exe");
		}
		catch(NoSuchElementException e){
			ATUReports.add("Pop up is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		}
        /**
		 * This method is used to locate the element with ID and used to enter value to element
		 * @param idvalue : This argument will take the locater ID value
	     * @param inputvalue : This argument will take the int value which has to passed.
		*/
		public void enterTextByID(String idvalue)
		{
			try { 
			driver.findElement(By.id(idvalue)).clear(); 
			
			ATUReports.add("date  -value is entered", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			} catch (NoSuchElementException e) {
			//System.out.println("Element does not exist");
				ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			
		    } catch (WebDriverException e) {
				//System.out.println("Browser does not exist");
		    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		}
	    /**
	     * This method is used to locate the element by using Xpath and used to enter the value
	     * @param Value: This argument will take locator Xpath value
	     * @param Data: This argument will take the value to be entered
	    */  
		public void enterTextByXpath(String Value,String Data)
		{
			try { 
			driver.findElement(By.xpath(Value)).clear(); 
			driver.findElement(By.xpath(Value)).sendKeys(Data);
			ATUReports.add(Data+"  -value is entered", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			} catch (NoSuchElementException e) {
			//System.out.println("Element does not exist");
				ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			
		    } catch (WebDriverException e) {
				//System.out.println("Browser does not exist");
		    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		}
		/**
			 * This method is used to take value from the table and it is used to click on specific WebElement
			 * @param Value : This argument will take the 
			 * @throws InterruptedException
			 */
		    public void tableVerification2VerifyProduct(String Value) throws InterruptedException
		    {
		    try
		    {
		    	 int i=0; 
		    	//To locate table. 
		    	WebElement mytable = driver.findElement(By.xpath(Value));
		    	//To locate rows of table.
		    	List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		    	//To calculate no of rows.
		    	int rows_count = rows_table.size();
		    	//Loop will execute till the last row of first page of the table. 
		    	for (int row=0; row<rows_count; row++)
		    	{ 
		    		
		    		//To locate columns(cells) of that specific row. 
		    		List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); 
		    		//To calculate no of columns(cells) In that specific row. 
		    		int columns_count = Columns_row.size(); 
		    		System.out.println("Number of cells In Row "+row+" are "+columns_count);
		    		//Loop will execute till the last cell of that specific row.
		    		for (int column=0; column<columns_count; column++)
		    		{ 
		    			//To retrieve text from that specific cell. 
		    			String celtext = Columns_row.get(column).getText();
		    			System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext); 
		    			if(driver.findElement(By.xpath("//div[@class='clickableIcon'/img[@src='/dsched/images/dsched/new-icons/red/icon-opt-out.png']")).isDisplayed())
		    				
		    			{
		    				driver.findElement(By.xpath("//div[@class='clickableIcon'/img[@src='/dsched/images/dsched/new-icons/red/icon-opt-out.png']")).click();
		    			}
		    			
		    			
		    		}
		    	}
		    	
		    	        	
		    }
		    catch(NoSuchElementException e)
		    {
		          ATUReports.add("Expected grid is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		    }   
		    catch(WebDriverException e)
		    {
		          ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		     }
		}
		/**
		 * This method is used to verify the value in the grid(Verify product)
		 * @param Value: This argument will take the locator Xpath value
		 * @throws InterruptedException
		 */
		public void tableVerificationVerifyProduct(String Value) throws InterruptedException
        {
        try
        {
        	 int i=0; 
        	//To locate table. 
        	WebElement mytable = driver.findElement(By.xpath(Value));
        	//To locate rows of table.
        	List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
        	//To calculate no of rows.
        	int rows_count = rows_table.size();
        	//Loop will execute till the last row of first page of the table. 
        	for (int row=0; row<rows_count; row++)
        	{ 
        		
        		//To locate columns(cells) of that specific row. 
        		List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); 
        		//To calculate no of columns(cells) In that specific row. 
        		int columns_count = Columns_row.size(); 
        		System.out.println("Number of cells In Row "+row+" are "+columns_count);
        		//Loop will execute till the last cell of that specific row.
        		for (int column=0; column<columns_count; column++)
        		{ 
        			//To retrieve text from that specific cell. 
        			String celtext = Columns_row.get(column).getText();
        			System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext); 
        			if(celtext.contains("United States"))
        			{
        				ATUReports.add(celtext+" is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
        			    i++;
        			}
        			else if(celtext.contains("Germany"))
        			{
        				ATUReports.add(celtext+" is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
        				i++;
        			}
        			else if(celtext.contains("Japan"))
        			{
        				ATUReports.add(celtext+" is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
        				i++;
        			}
        			
        		}
        	}
        	if(i!=3)
        	{
        		ATUReports.add("Expected territories are not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
        	}
        	        	
        }
        catch(NoSuchElementException e)
        {
	          ATUReports.add("Expected grid is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
	    }   
        catch(WebDriverException e)
        {
	          ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
	     }
	}
		/**
		 * This method is used to get the Title
		 */
		public void getTitleAndCompare(String Text)
		{
		try
		{
		
			String title = driver.getTitle();
			if(title.contains(Text))
			{
				ATUReports.add("Expected page is getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			else
			{
				ATUReports.add("Expected page is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			}
		 
        catch(WebDriverException e)
        {
	          ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
	     }
		}
		/**  
		 * This method is used to enter the current time stamp in the comments field
		 * @param idvalue: This argument will take the id value
		 * @param data  : This argument will take the comment which has to be entered in the field
		 */
		public void enterTextByIDTimeStamp(String idvalue, String data)
		{
			try { 
			driver.findElement(By.id(idvalue)).clear(); 
			
			String timeStamp = new SimpleDateFormat("MM.dd.yyyy - HH.mm.ss").format(new Date());
			driver.findElement(By.id(idvalue)).sendKeys(data+"-"+timeStamp);
			ATUReports.add(data  + "-value is entered", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			} catch (NoSuchElementException e) {
			//System.out.println("Element does not exist");
				ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			
		    } catch (WebDriverException e) {
				//System.out.println("Browser does not exist");
		    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		}
		/**
		 * This method is used to enter the UPC in quick search and it will verify whether application redirects to the expected page. If it is not redirected to the expected page, it will click on search icon.
		 * @param idvalue : This argument will take the idvalue
		 * @param data    :This argument will take the upc value which has to be entered
		 * @param xpathvalue: This argument will take the xpath value
		 * @param classvalue: This argument will take the class value
		 * @throws InterruptedException
		 */
		public void enterTextByIDQuickSearch(String idvalue,String data,String xpathvalue,String classvalue) throws InterruptedException
		{
			try { 
			driver.findElement(By.id(idvalue)).clear(); 
			driver.findElement(By.id(idvalue)).sendKeys(data);
			ATUReports.add(data+" -value is entered", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			Thread.sleep(30000);
			if(driver.findElement(By.xpath(xpathvalue)).isDisplayed())
			{
				ATUReports.add("Application redirects to the expected page", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));	
			}
			else 
			{
				Thread.sleep(3000);
				driver.findElement(By.className(classvalue)).click();
				ATUReports.add("search icon clicked on successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
			}
			} catch (NoSuchElementException e) {
			
				ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			
		    } catch (WebDriverException e) {
				
		    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		}
		public void clickByClassNameLocalRowFilter(String xpathvalue,String classname)
		{
			try {
				if(driver.findElement(By.xpath(xpathvalue)).isDisplayed()==true)
				{
				//new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfElementLocated(By.className(classname))).click();
				driver.findElement(By.className(classname)).click();
				ATUReports.add(classname+"-link has been clicked", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
				}
			} catch (NoSuchElementException e) {
				//System.out.println("No such element");
				ATUReports.add("Expected link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}catch (WebDriverException ee){
				//System.out.println("Browser does not present");
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
		}
		public void tableVerification1(String Value) throws InterruptedException
        {
        try
        {
        	 int i=0; 
        	//To locate table. 
        	WebElement mytable = driver.findElement(By.id(Value));
        	//To locate rows of table.
        	List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
        	//To calculate no of rows.
        	int rows_count = rows_table.size();
        	//Loop will execute till the last row of first page of the table. 
        	for (int row=0; row<rows_count; row++)
        	{ 
        		
        		//To locate columns(cells) of that specific row. 
        		List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td")); 
        		//To calculate no of columns(cells) In that specific row. 
        		int columns_count = Columns_row.size(); 
        		System.out.println("Number of cells In Row "+row+" are "+columns_count);
        		//Loop will execute till the last cell of that specific row.
        		for (int column=0; column<columns_count; column++)
        		{ 
        			//To retrieve text from that specific cell. 
        			String celtext = Columns_row.get(column).getText();
        			
        			System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext); 
        			
        				//ATUReports.add(celtext+" is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
        			  
        			i++;
        			
        		}
        	}
        	if(i==0)
        	{
        		ATUReports.add("There is no value in the grid", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
        	}
        	else
        	{
        		ATUReports.add("Data is there in the grid", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
        	}
        	
        
        	        	
        }
        catch(NoSuchElementException e)
        {
	          ATUReports.add("Expected grid is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
	    }   
        catch(WebDriverException e)
        {
	          ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
	     }
        }
		public void pressEnter()
		{
			  Actions action = new Actions(driver); 
			   action.sendKeys(Keys.ENTER).build().perform();
		}
		public void launchOnlyBrowser(String browsername)
		{
		
			{
				if (browsername.equalsIgnoreCase("firefox"))
				{
					ProfilesIni profile = new ProfilesIni();
					 FirefoxProfile ffprofile = profile.getProfile("AUTOMATIONPROFILE");
					// driver = new FirefoxDriver(ffprofile);
					 driver.get("http://digs-uat.umusic.net/");
				}
				
			
			}
	
		}
		
		public void getURL(String url)
		{
			
				driver.get(url);
			}
		public boolean alertVerify()
		{
			boolean presentFlag = false;

			  try {

			   // Check the presence of alert
			   Alert alert = driver.switchTo().alert();
			   // Alert present; set the flag
			   presentFlag = true;
			   // if present consume the alert
			   alert.accept();
System.out.println("Alert");
			  } catch (NoAlertPresentException ex) {
			   // Alert not present
			   ex.printStackTrace();
			  }

			  return presentFlag;

			 }
		public void autoitAuthen() throws IOException
		{
		try
		{
			
			Runtime.getRuntime().exec("C:/AUTOMATION/Selenium/DiGS_Smoke_Test_Cases/testData/new.exe");
		}
		catch(NoSuchElementException e){
			ATUReports.add("Pop up is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		}
		public void selectDropdownValueBasicPref(String Value,String Text,String Value1) throws InterruptedException
		{ 
			try
			{
				if(Value1.contains(Text))
				{
			
			
				}
				else
				{
					Select dropdown = new Select(driver.findElement(By.id(Value)));
					dropdown.selectByVisibleText(Text);
					driver.findElement(By.id("save-basic-pref"));
					Thread.sleep(6000);
				}
			
				ATUReports.add("Expected value got selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
				}
				catch(NoSuchElementException e){
				ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
			
		}
		public void getTextAndCompareVerifyEmpty(String Value)
		{
			try
			{
		String w =driver.findElement(By.id(Value)).getText();
		System.out.println(w);
		if(w.isEmpty())
		{
			ATUReports.add("The field is empty as expected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		
			
		}
		else
		{
			
			ATUReports.add("Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
			}
			catch(NoSuchElementException e){
			ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
			}
		public void selectDropdownValueUsingIndexUsingID(String Value,int Index)
		{ 
			try
			{
			Select dropdown = new Select(driver.findElement(By.id(Value)));
			dropdown.selectByIndex(Index);
				ATUReports.add(Value+"-value got selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				
			}
		       	catch(NoSuchElementException e){
				ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
			
		}
		public void mouseHover(String Value)
		{ 
			try
			{
			//Select dropdown = new Select(driver.findElement(By.id(Value)));
		
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(By.linkText(Value))).perform();
	//action.moveToElement(driver.findElement(By.xpath(Value))).click();
}	
			
		       	catch(NoSuchElementException e){
				ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				} catch(WebDriverException e){
				ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				}
			
		}
	}
		
	