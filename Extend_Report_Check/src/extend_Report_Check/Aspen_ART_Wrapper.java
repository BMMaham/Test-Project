package extend_Report_Check;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import com.ap
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.*;
//import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.*;
import com.aventstack.extentreports.reporter.converters.*;
import com.aventstack.extentreports.utils.*;
import com.aventstack.extentreports.view.*;
//import com.appvance.ds.webdriver.DS3WebDriver;
import com.aventstack.extentreports.gherkin.*;
import com.aventstack.extentreports.gherkin.model.*;
import com.aventstack.extentreports.markuputils.*;
import com.aventstack.extentreports.mediastorage.*;
import com.relevantcodes.extentreports.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class Aspen_ART_Wrapper {
	int i = 1;
	{
		
		}
	RemoteWebDriver driver;
	 ExtentReports extent;
	 ExtentTest test;
	/**
	 * This method is used to launch the browser
	 * @param browsername : It will take the name of the browser which has to launch
	 * @param url : It will take the URL 
	 */
	public void launchBrowser(String browsername, String url)
	{ 
		try {
			if (browsername.equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();
			} else if (browsername.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "./drivers/chromeDriver.exe");
				driver = new ChromeDriver();
			} else if (browsername.equalsIgnoreCase("IE")){
				System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			else {
				System.out.println("Browser does not exists");
			}
			
			//maximize the browser
			driver.manage().window().maximize();	
			

			ATUReports.setWebDriver(driver);
			ATUReports.indexPageDescription = "Aspen Asset Management";
			ATUReports.setAuthorInfo("B.M.MAHAM",  Utils.getCurrentTime(), "1.0");
			extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/MyReport.html", true);
			 //extent.addSystemInfo("Environment","Environment Name")
			 extent.addSystemInfo("Host Name", "B.M.MAHAM");
			 extent.addSystemInfo("Environment", "Automation Testing");
			 extent.addSystemInfo("User Name", "B.M.MAHAM");
			     
			                
			                
			                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
			                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
			 extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
			 
			//launch URL
			driver.get(url);
			
						
			//Initiate Implicit wait
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 test = extent.startTest("passTest");
			 Assert.assertTrue(true);
			 //To generate the log when the test case is passed
			 test.log(LogStatus.PASS, "Test Case Passed is passTest");
			
				 // ending test
				 //endTest(logger) : It ends the current test and prepares to create HTML report
				 extent.endTest(test);
			ATUReports.add("Browser launched Sueccessfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		} catch (WebDriverException  e) {
			ATUReports.add("Browser does not launched", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			test = extent.startTest("failTest");
			 Assert.assertTrue(false);
			 test.log(LogStatus.FAIL, "Test Case (failTest) Status is passed");
			 extent.endTest(test);
		}
	}
	/**
	 * This method is used to locate the element with ID and used to enter value to element
	 * @param idvalue : This argument will take the locater ID value
     * @param inputvalue : This argument will take the string value which has to passed.
	 * @throws InterruptedException 
	*/
	public void enterTextByID(String idvalue, String data) throws InterruptedException
	{
		try { 
		driver.findElement(By.id(idvalue)).clear(); 
		Thread.sleep(3000);
		driver.findElement(By.id(idvalue)).sendKeys(data);
		ATUReports.add(data  + "-value is entered", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		 test = extent.startTest("passTest");
		 Assert.assertTrue(true);
		 //To generate the log when the test case is passed
		 test.log(LogStatus.PASS, "Test Case Passed is passTest");
		} catch (NoSuchElementException e) {
		//System.out.println("Element does not exist");
			ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		
	    } catch (WebDriverException e) {
			//System.out.println("Browser does not exist");
	    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
	}
	/**
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
			ATUReports.add("Text:"+value+"-exists", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		} catch (NoSuchElementException e) {
			ATUReports.add("Expected Web Element does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
			//new WebDriverWait(driver,90).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathvalue))).click();
			ATUReports.add(xpathvalue+"-Clicked on Successfully", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		} catch(NoSuchElementException e){
			ATUReports.add("Expected value/text does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		} catch(WebDriverException e){
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
			
			FileInputStream fis = new FileInputStream(new File("C:\\AUTOMATION\\Selenium\\Aspen_ART\\testData\\testData.xlsx"));
			
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
						//add data to data arry
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
		String w=driver.getTitle();
		System.out.println(w);
		ATUReports.add(w+"-Title", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
	System.out.println(w);
	if(w.contains(Text))
	{
		ATUReports.add(Value+"-Expected message/Text/Link is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
	
		
	}
	else
	{
		
		ATUReports.add("Expected message/Text/Link does not exists."+"Actual"+w, LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
	public void getTextAndCompareUsingXpath(String Value,String Text)
	{
		try
		{
			String w =driver.findElement(By.xpath(Value)).getText().trim();
					System.out.println(w);
					System.out.println(Text);
					String s=w.replaceAll(" ", "");
					String Text1 = Text.replaceAll(" ", "");
					
					if(s.contains(Text1))
					{
						ATUReports.add("Expected message/Text/Link is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
					
						
					}
					else
					{
						
						ATUReports.add(s+";"+Text1+"-Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
					}
						}
						catch(NoSuchElementException e){
						ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
						} catch(WebDriverException e){
						ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
		System.out.println(w);
		if(w.contains(Text))
		{
			ATUReports.add("Expected message/Text/Link is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		
			
		}
		else
		{
			
			ATUReports.add(driver.findElement(By.className(Value)).getText() +" Expected message is not there", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
	 * This method is used to check the color of an image
	 * @Param: Class Name
	 */
	public void getColorUsingCSSselector(String Value,String color)
	{
		{
			try
			{
		String w =driver.findElement(By.id(Value)).getCssValue("color");
		System.out.println(w);
		if(w.contains(color))
		{
			ATUReports.add("The image is in the expected color", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		
			
		}
		else
		{
			
			ATUReports.add(color+"-The image is not in the expected color:"+"  Actual color:"+w, LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
	public void selectDropdownValueUsingIndex(String Value,int Index)
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
	public void scrollUp()
	{
		try
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,-250)","");
		}
		catch(NoSuchElementException e){
			ATUReports.add("Unable to scroll Up", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Unable to scroll Up", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		
	}
	public void scrollIntoView(String Value) throws InterruptedException
	{
		try
		{
			WebElement element= driver.findElement(By.xpath(Value));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			Thread.sleep(500);	
			//driver.findElement(By.xpath(Value)).click();
			}
		catch(NoSuchElementException e){
			ATUReports.add("Unable to scroll Up", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Unable to scroll Up", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		
	}
	public void scrollDown()
	{
		try
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)","");
		}
		catch(NoSuchElementException e){
			ATUReports.add("Unable to scroll Up", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Unable to scroll Up", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
	}
	public void tableVerification(String Value) throws InterruptedException
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
    		ATUReports.add("Expected values are not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
    	}
    	  
    	else
    	{
    		ATUReports.add("Expected value(s)are getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
	
	public void verifyCheckbox() throws InterruptedException
	{
		
			try
			{
		//String w =driver.findElement(By.id(Value))
		//System.out.println(w);
		if((driver.findElement(By.id("ctl00_ContentPlaceHolder1_gvRequests_ctl00_ctl04_PendingApproval")).isSelected()))
		{	
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_gvRequests_ctl00_ctl04_PendingApproval")).click();
			Thread.sleep(6000);
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_gvRequests_ctl00_ctl04_PendingApproval")).click();
				
			ATUReports.add("The check box has been selected ", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				
		}
		else
		{
			driver.findElement(By.id("ctl00_ContentPlaceHolder1_gvRequests_ctl00_ctl04_PendingApproval")).click();
			
			ATUReports.add("The check box has been selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
			}
			catch(NoSuchElementException e){
			ATUReports.add("Element to verify the message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
			}
	public void clear(String idvalue) throws InterruptedException
	{
		try { 
		driver.findElement(By.id(idvalue)).clear(); 
		Thread.sleep(3000);
		
		ATUReports.add(" The value has been cleared", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		} catch (NoSuchElementException e) {
		//System.out.println("Element does not exist");
			ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		
	    } catch (WebDriverException e) {
			//System.out.println("Browser does not exist");
	    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
	}
	public void tableVerificationspecificcolumn(String Value,String upc) throws InterruptedException
    {
    try
    {
    	 int i=0,j=0; 
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
    			if(column==2)
    			{
    			 
    				 if((celtext.contains(upc)))
    				 {
    			     j++;
    				 break;
    			 }
    			 
    			 else
    			 {
    				  
    			 }
    			}
    			i++;
    			
    		}
    	}
    	if(i<=1)
    	{
    		ATUReports.add("Expected grid is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
    	}
    	if(j>0)
    	{
    		ATUReports.add("Expected value is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
    	}
    	else
    	{
    		ATUReports.add("Expected value is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
	public void tableVerificationCopyright(String Value) throws InterruptedException
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
    			 if(column==10)
    			 {
    				 if((celtext=="Copyright"))
    			     ATUReports.add("Expected values are getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));	 
    			 }
    			 else
    			 {
    				 ATUReports.add("Expected values are not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP)); 
    			 }
    			i++;
    			
    		}
    	}
    	if(i==3)
    	{
    		ATUReports.add("Expected grid is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
	public void tableVerificationArtist(String Value) throws InterruptedException
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
    			if(column==10){
    			 if(column==10)
    			 {
    				 if((celtext.contains("Artist")))
    			     ATUReports.add("Expected values are getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));	 
    			 }
    			 else
    			 {
    				 ATUReports.add("Expected values are not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP)); 
    			 }}
    			i++;
    			
    		}
    	}
    	if(i==3)
    	{
    		ATUReports.add("Expected grid is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
	public void verifyCheckboxSelectedByDefault(String idValue) throws InterruptedException
	{
		
			try
			{
		//String w =driver.findElement(By.id(Value))
		//System.out.println(w);
		if((driver.findElement(By.id(idValue))).isSelected())
		{	
			
			ATUReports.add("The check box has selected bydefault ", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
				
		}
		else
		{
			
			ATUReports.add("The check box has not selected bydefault", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
			}
			catch(NoSuchElementException e){
			ATUReports.add("Element to verify the message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
			}
	public void scrollHorizondal()
	{
		try
		{
			  WebElement scroll = driver.findElement(By.xpath("//div[@id='gvLocationHorizontalRail']"));
			    JavascriptExecutor js = (JavascriptExecutor)driver;
			    js.executeScript("window.scrollBy(250,0)", "");
			    }
		catch(NoSuchElementException e){
			ATUReports.add("Unable to scroll Up", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Unable to scroll Up", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
	}
	public void selectDropdownValueUsingVisibleText(String idValue,String value)
	{ 
		try
		{
		Select dropdown = new Select(driver.findElement(By.id(idValue)));
		dropdown.selectByVisibleText(value);;
		
			ATUReports.add("Expected value has been selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		
			}
			catch(NoSuchElementException e){
			ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		
	}
	public void selectDropdownValueEndPeriod(String idValue)
	{ 
		try
		{
		Select dropdown = new Select(driver.findElement(By.id(idValue)));
		dropdown.selectByVisibleText("2014Q4");
		
			ATUReports.add("Expected value has been selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		
			}
			catch(NoSuchElementException e){
			ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		
	}
	/**
	 * This method is used to check the color of an image
	 * @Param: Class Name
	 */
	public void getColorUsingCSSselector1(String Value,String color1)
	{
		{
			try
			{
		String w =driver.findElement(By.id(Value)).getCssValue("color");
		System.out.println(w);
		if(w.contains(color1))
		{
			ATUReports.add("The image is in the expected color", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		
			
		}
		else
		{
			
			ATUReports.add(color1+"-The image is not in the expected color", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
			}
			catch(NoSuchElementException e){
			ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
			}
	}
	public void iframe()
	{
		{
			try
			{
				driver.switchTo().frame(driver.findElement(By.id("blockDates")));
		
		//if(w.contains(color1))
		{
			ATUReports.add("The image is in the expected color", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		
			
		}
		/*else
		{
			
			ATUReports.add(color1+"-The image is not in the expected color", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}*/
			}
			catch(NoSuchElementException e){
			ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
			}
	}
	public void fileUpload() throws IOException
	{
	try
	{
		//driver.findElement(By.id("ctl00_ContentPlaceHolder1_upload_fuLoader")).click();
		//WebElement upload=driver.findElement(By.name("theFile"));
		
		Runtime.getRuntime().exec("C:/AUTOMATION/Selenium/DiGS/DiGS/USRP_Smoke_Test_Cases/testData/FileUpload.exe");
	}
	catch(NoSuchElementException e){
		ATUReports.add("Pop up is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		} catch(WebDriverException e){
		ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
	}
	public void columnCount(String Value) throws InterruptedException
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
    	ATUReports.add("Row Count is:"+rows_count, LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
    	
    	        	
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
	 * This method is used to select the drop down value by ID
	 * @Param: ID
	 */
	public void selectDropdownValueUsingID(String idValue,int Index)
	{ 
		try
		{
		Select dropdown = new Select(driver.findElement(By.id(idValue)));
		dropdown.selectByIndex(Index);
			ATUReports.add(idValue+"-value got selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
		}
	       	catch(NoSuchElementException e){
			ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
	}
	public void enterTextByID(String idvalue, int data) throws InterruptedException
	{
		try { 
		driver.findElement(By.id(idvalue)).clear(); 
		Thread.sleep(3000);
		String strData = Integer.toString(data);
		driver.findElement(By.id(idvalue)).sendKeys(strData);
		ATUReports.add(data  + "-value is entered", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		} catch (NoSuchElementException e) {
		//System.out.println("Element does not exist");
			ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		
	    } catch (WebDriverException e) {
			//System.out.println("Browser does not exist");
	    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
	}
	public void getColorUsingCSSselector(String Value)
	{
		{
			try
			{
		String w =driver.findElement(By.id(Value)).getCssValue("color");
		System.out.println(w);
		
			ATUReports.add("The color is :"+w, LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		
			
			}
			catch(NoSuchElementException e){
			ATUReports.add("Element to verify the Expected message/Text/Link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
			
			}
	}
    /*public void selectDropdownValue(String Value,String data)
	{ 
		try
		{
		Select dropdown = new Select(driver.findElement(By.id(Value)));
		dropdown.selectByVisibleText("US");
			ATUReports.add(Value+"-value got selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
		}
	       	catch(NoSuchElementException e){
			ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		
	}*/
    public void selectDropdownValue1(String Value,String data)
	{ 
		try
		{
		Select dropdown = new Select(driver.findElement(By.id(Value)));
		dropdown.selectByVisibleText("Jun 2016");
			ATUReports.add(Value+"-value got selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
		}
	       	catch(NoSuchElementException e){
			ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		
	}
    public void imageVerification(String Value,String data)
	{ 
		try
		{
		Select dropdown = new Select(driver.findElement(By.id(Value)));
		dropdown.selectByVisibleText("Jun 2016");
			ATUReports.add(Value+"-value got selected", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
		}
	       	catch(NoSuchElementException e){
			ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		
	}
    /**
	 * This method is used to click on- by using CSS Selector
	 * @Param:id value
	 */
	public void clickByCSSSelector(String cssselectorValue)
	{
		try{
			//driver.findElement(By.id(cssselectorValue)).click();
			driver.findElement(By.cssSelector(cssselectorValue)).click();
			ATUReports.add(cssselectorValue +"-Clicked on Successfully", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		} catch(NoSuchElementException e){
			ATUReports.add("Expected button/link does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
		}
	public void getTextAndCompareUsingIdVerifyEmpty(String Value,String Text)
	{
		try
		{
	String w =driver.findElement(By.id(Value)).getText();
	System.out.println(w);
	if(w.contains(Text))
	{
		ATUReports.add(Value+"-Expected message/Text/Link is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
	
		
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
	public void enterTextByName(String namevalue, String data) throws InterruptedException
	{
		try { 
		driver.findElement(By.name(namevalue)).clear(); 
		Thread.sleep(3000);
		//String strData = Integer.toString(data);
		driver.findElement(By.name(namevalue)).sendKeys(data);
		ATUReports.add(data  + "-value is entered", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		} catch (NoSuchElementException e) {
		//System.out.println("Element does not exist");
			ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		
	    } catch (WebDriverException e) {
			//System.out.println("Browser does not exist");
	    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
	}
	public void verifyClickable(String linkText) throws InterruptedException
	{
		try { 
		
		WebElement linkT =driver.findElement(By.linkText(linkText));
		if(linkT.isEnabled())
		{
		ATUReports.add("The link is enabled", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		}
		else
			{
			ATUReports.add("The link is not enabled", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
			}
		}
		catch (NoSuchElementException e) {
			
		//System.out.println("Element does not exist");
			ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		
	    } catch (WebDriverException e) {
			//System.out.println("Browser does not exist");
	    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
		
	}
	public void tableVerificationMyFolder(String Value) throws InterruptedException
    {
    try
    {
    	 for(int j=0;j<=3;j++)
    	 {
    	    driver.findElement(By.id("Button_Refresh")).click();
    	 }
    	//To locate table. 
    	WebElement mytable = driver.findElement(By.id(Value));
    	//To locate rows of table.
    	List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
    	//To calculate no of rows.
    	int rows_count = rows_table.size();
    	System.out.println(rows_count);
    	int i=0; 
    	
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
    		ATUReports.add("Expected values are not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
    	}
    	  
    	else
    	{
    		ATUReports.add("Expected value(s)are getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
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
	public void verifyEnableUsingID(String idvalue)
	{
		try { 
		if(driver.findElement(By.id(idvalue)).isEnabled()==true);
		{
		//driver.findElement(By.id(idvalue)
		ATUReports.add(idvalue+"Button is in enable mode", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		}
		if(driver.findElement(By.id(idvalue)).isEnabled()==false)
		{
			ATUReports.add(idvalue+"Button is in disable mode", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		}
		
		} catch (NoSuchElementException e) {
		//System.out.println("Element does not exist");
			ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		
	    } catch (WebDriverException e) {
			//System.out.println("Browser does not exist");
	    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
	}
	public void verifyEnableUsingLinkText(String linkTextValue)
	{
		try { 
		if(driver.findElement(By.linkText(linkTextValue)).isEnabled()==true);
		{
		//driver.findElement(By.id(idvalue)
		ATUReports.add(linkTextValue+"Button is in enable mode", LogAs.PASSED, new CaptureScreen(ScreenshotOf.DESKTOP));
		}
		if(driver.findElement(By.linkText(linkTextValue)).isEnabled()==false)
		{
			ATUReports.add(linkTextValue+"Button is in disable mode", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		}
		
		} catch (NoSuchElementException e) {
		//System.out.println("Element does not exist");
			ATUReports.add("Expected field does not exists", LogAs.FAILED, new CaptureScreen(ScreenshotOf.DESKTOP));
		
	    } catch (WebDriverException e) {
			//System.out.println("Browser does not exist");
	    	ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
	}
	public void tableVerificationspecificValue(String gridValue,String userName,String deleteLink,String addUser) throws InterruptedException
    {
    try
    {
    	 int i=0,j=0; 
    	//To locate table. 
    	WebElement mytable = driver.findElement(By.id(gridValue));
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
    			if(column==2)
    			{
    			 
    				 if((celtext.contains(userName)))
    				 {
    					 driver.findElement(By.id(deleteLink)).click();
    					 Thread.sleep(15000);
    					 JavascriptExecutor jse = (JavascriptExecutor)driver;
    					 jse.executeScript("window.scrollBy(0,250)","");
    					 driver.findElement(By.id(addUser)).click();
    			     j++;
    				 break;
    			 }
    			 
    			 else
    			 {
    				  
    			 }
    			}
    			i++;
    			
    		}
    	}
    	if(i<=1)
    	{
    		ATUReports.add("Delete button has been clicked on successfully", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
    	}
    	if(j>0)
    	{
    		ATUReports.add("Expected value is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
    	}
    	else
    	{
    		driver.findElement(By.id(addUser)).click();
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
	public void tableRowCount(String Value) throws InterruptedException
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
    	System.out.println(rows_count);
    	//Loop will execute till the last row of first page of the table. 
    	ATUReports.add("Expected grid is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
    	
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
	public void getSRC(String Value)
	{ 
		try
		{
		WebElement element=driver.findElement(By.xpath(Value));
		String srcValue = element.getAttribute("src");
		System.out.println(srcValue);
			ATUReports.add("src value is:"+srcValue, LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			
		}
	       	catch(NoSuchElementException e){
			ATUReports.add("Element to select the value does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		
	}
	public void getSRCandCompare(String Value,String srcText)
	{ 
		try
		{
		WebElement element=driver.findElement(By.xpath(Value));
		String srcValue = element.getAttribute("src");
		System.out.println(srcValue);
		if(srcValue.contains(srcText))
		{
			ATUReports.add("Expected color is getting displayed", LogAs.PASSED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
		else
		{
			ATUReports.add("Expected color is not getting displayed", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
		}
		}
	       	catch(NoSuchElementException e){
			ATUReports.add("Element does not exists", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			} catch(WebDriverException e){
			ATUReports.add("Browser Issue", LogAs.FAILED, new CaptureScreen(CaptureScreen.ScreenshotOf.DESKTOP));
			}
		
	}

}
