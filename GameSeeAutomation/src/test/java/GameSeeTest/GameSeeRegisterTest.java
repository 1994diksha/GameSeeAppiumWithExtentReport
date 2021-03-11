package GameSeeTest;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import GameSeePKG.GameSeeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class GameSeeRegisterTest {
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
	 GameSeeClass GSclass = new GameSeeClass();
	 public AndroidDriver<AndroidElement> driver ;
	
	
	 @BeforeSuite
	    public void setUp()
	    {
	        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/GameSeeRegressionReport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	         
	        extent.setSystemInfo("Device", "Android OPPO Reno 2F Version 10");
	        extent.setSystemInfo("Host Name", "Diksha AppSmartz");
	        extent.setSystemInfo("Build Version", "0.33");
	        extent.setSystemInfo("User Name", "Diksha Sharma");
	         
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("AutomationTesting.in GameSee");
	        htmlReporter.config().setReportName("GameSee Regression Report");
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	        htmlReporter.config().setTheme(Theme.DARK);
	    }
	  
	 
	 @BeforeTest
	 public static void setUp1() throws Exception  
	 {
		 
		 GameSeeClass GSclass = new GameSeeClass();
		 test = extent.createTest("Testcase For Launch The Device Before Test");
		  GSclass.setUp();
		
	 }
	 
   @Test(priority=1)
   public void GameseeRegisterationWithGmail() throws InterruptedException, MalformedURLException
   {
	   test = extent.createTest(" Testcase For Registeration With Gmail Account");
 	   GSclass.registerwithGMAIL();
   }
   
   @Test(priority=2)
   public void CompleteProfileScreen() throws InterruptedException
   {
	  test = extent.createTest(" Testcase For CompleteProfileScreen");
 	  GSclass.CompleteProfileScreenFORM();
   }
   
   
   @Test(priority=3)
   public void RegisterWithPhoneNumber()
   {
	   test = extent.createTest(" Testcase For Registeration With Phone Number");
	   GSclass.registerWithPhoneNumber();
   }
   
   @Test(priority=4)
   public void PhoneCompleteProfileOptionCase() throws InterruptedException
   {
	   test = extent.createTest(" Testcase For Complete Profile Using Manually And Gmail(Phone Number)");
	   GSclass.CompleteProfileManuallyandGmail();
   }
   
   //@Test(priority=3)
  // public void HomeTopStreamer() throws InterruptedException
  // {
 	 // GSclass.VerifyHomeTopStreamerName();
  // }
   
  // @Test(priority=4)
  // public void HomeTopStreamerFollowBtn() throws InterruptedException
   //{
 	 // GSclass.HomeTopStreamerFollowBtn();
  // }
   
 //  @Test(priority=5)
  // public void HomeTopStreamerFollowerFollowingList() throws InterruptedException
  // {
 	 // GSclass.HomeTopStreamerFollowerFollowingList();
  // }
   
  // @Test(priority=6)
 //  public void HomeTopStreamerFollowerFollowingClickable() throws InterruptedException
   //{
 	 // GSclass.HomeTopStreamerFollowerFollowingClickable();
 		  
   //}
   
  // @Test(priority=7)
   //public void AboutTab() throws Exception
  // {
 	 // GSclass.AboutTab();
  // }
   
 //  @Test(priority=8)
  // public void Trending_Section()
  // {
 	// GSclass.Trending(); 
  // }
  
 
   @AfterMethod
   public void getResult(ITestResult result)
   {
       if(result.getStatus() == ITestResult.FAILURE)
       {
           test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
           test.fail(result.getThrowable());
       }
       else if(result.getStatus() == ITestResult.SUCCESS)
       {
           test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
       }
       else
       {
           test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
           test.skip(result.getThrowable());
       }
   }
   
   @AfterSuite
   public void tearDown()
   {
       extent.flush();
   }

}
