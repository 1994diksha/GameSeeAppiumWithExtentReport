package GameSeePKG;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Help.JavaFakerAPI;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class GameSeeClass {
	
	 private String reportDirectory = "reports";
	  private String reportFormat = "xml";
	  public AndroidDriver<AndroidElement> driver ;
	  JavaFakerAPI jf = new JavaFakerAPI();
	  DesiredCapabilities dc = new DesiredCapabilities();
	  
	  
	  public void setUp() throws MalformedURLException, Exception 
	  {
		   
		  dc.setCapability("reportDirectory", reportDirectory);
	      dc.setCapability("reportFormat", reportFormat);
          dc.setCapability(MobileCapabilityType.UDID, "MNFYBMT4RKUCGY45");
          dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "tv.gamesee");
          dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.auth.activities.SplashActivity");
          driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
          driver.setLogLevel(Level.INFO);
	  }   

	  public void registerwithGMAIL() throws InterruptedException, MalformedURLException
	  {
		  Thread.sleep(5000);
			 
		   driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
	       driver.setLogLevel(Level.INFO);

			if(driver!=null){
				Thread.sleep(3000);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
				  driver.findElement(By.xpath(Constant. registerGMAILbtn)).click();
				  driver.findElement(By.xpath(Constant.emailAddress)).click();
				
				
				
	  }
			else {
			    System.out.println("driver == null");
			    
			}
		  
      }
	  
	  public void CompleteProfileScreenFORM() throws InterruptedException
	  {
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		  driver.findElement(By.xpath(Constant.editFullName)).clear();
		  driver.findElement(By.xpath(Constant.editFullName)).sendKeys(jf.getFirstName());
          driver.findElement(By.xpath(Constant.CompleteProfileUSERNAME)).sendKeys(jf.getPassword());
          new TouchAction(driver).press(492, 514).waitAction(Duration.ofMillis(3800)).moveTo(578, -1035).release().perform();
          new TouchAction(driver).press(807, 1010).waitAction(Duration.ofMillis(957)).moveTo(682, 121).release().perform();
          Thread.sleep(5000);
          new TouchAction(driver).press(492, 514).waitAction(Duration.ofMillis(3800)).moveTo(578, -1035).release().perform();
          new TouchAction(driver).press(807, 1010).waitAction(Duration.ofMillis(957)).moveTo(682, 121).release().perform();
          driver.findElement(By.xpath(Constant.CompleteProfileSAVE)).click();
          Thread.sleep(10000);
          
          try
          {
          driver.findElement(By.xpath(Constant.closebtnCoin)).click();
          System.out.println("Coins For Signup Successfully Get");
          }
          catch(Exception e)
          {
        	System.out.println("Unable to get signup coin----SIGNUP COIN CASE FAILED");;  
          }
          
          driver.findElement(By.xpath(Constant.profileTab)).click();
          driver.findElement(By.xpath(Constant.logoutBTN)).click();
          driver.findElement(By.xpath(Constant.logoutbtnClick)).click();
	  }
	  
	  public void registerWithPhoneNumber()
	  {
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.findElement(By.xpath(Constant.registerPhoneNumberbtn)).click();
		  driver.findElement(By.xpath(Constant.noneoftheAboveBtn)).click();
		  driver.findElement(By.xpath(Constant.enterPhoneNumber)).sendKeys(Constant.phoneNumber);
		  driver.findElement(By.xpath(Constant.getOTPbtn)).click();
		  
		  try
		  {
			 WebElement otp= driver.findElement(By.xpath(Constant.submitbtnonOTPscreen));
			 WebElement pin= driver.findElement(By.xpath("//*[@id='firstPinView']"));
			 //driver.findElement(By.xpath(Constant.submitbtnonOTPscreen)).isDisplayed();
			 new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(otp));
			 
			 
			 
			
			 
			 if(pin.toString().isEmpty())
			 {
				 Reporter.log("OTP Pin Is Empty");
				 driver.findElement(By.xpath("//*[@class='android.widget.ImageButton']")).click();
				 registerwithGMAIL();
				 
			 }
			 else
			 {
				///Comment
				 //** Here We Are verifying Whether user is redirection on Homescreen after successfully register/login by phone number
				 WebElement screenAfterRegisterWithPhoneSuccessfully = driver.findElement(By.xpath(Constant.Manualoption));
				 if(screenAfterRegisterWithPhoneSuccessfully.isDisplayed())
				 {
					 System.out.println("Complete Profile Options successfully Displayed to the user");
				 }
			 }
			
			 
		  }
		  catch(Exception e)
		  {
			  System.out.println("Nothing Happened");
		  }
	  }
	  
	  public void CompleteProfileManuallyandGmail() throws InterruptedException
	  {
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  Thread.sleep(20000);
		  driver.findElement(By.xpath(Constant.Manualoption)).click();
		  CompleteProfileScreenFORM();
		  ///comment
		  //Then you need to delete user again in order to test "COMPLETE PROFILE WITH GMAIL" case
		  Thread.sleep(10000);
		  registerWithPhoneNumber();
	  }
	  
      public void VerifyHomeTopStreamerName() throws InterruptedException
      {
    	  Thread.sleep(3000);
    	  String StreamerName=driver.findElement(By.xpath(Constant.TopStreamerName)).getText();
          System.out.println("TopStreamer name is :" +StreamerName);
          driver.findElement(By.xpath(Constant.TopStreamerName)).click();
          Thread.sleep(5000);
          String StreamerNameOnProfile= driver.findElement(By.xpath(Constant.NameOnProfile)).getText();
          System.out.println("Actual StreamerName on profile is :" +StreamerNameOnProfile);
          Thread.sleep(3000);
          driver.navigate().back();
          if(StreamerName.equals(StreamerNameOnProfile))
          {
        	  System.out.println("Correct Profile Is Opened");
          }
      }
      
      public void HomeTopStreamerFollowBtn() throws InterruptedException
      {
    	  Thread.sleep(3000);
          driver.findElement(By.xpath(Constant.TopStreamerName)).click();
          Thread.sleep(2000);
          try
          {
        	  driver.findElement(By.xpath(Constant.UnFollowBTN)).getText();
        	  Thread.sleep(3000);
        	  System.out.println("User is already followed, now unfollow the user");
        	  driver.findElement(By.xpath(Constant.UnFollowBTN)).click();
        	  Thread.sleep(7000);
        	  driver.navigate().back();
        	  
          }
          catch(Exception e)
          {
        	 driver.findElement(By.xpath(Constant.FollowBTN)).getText();
        	 Thread.sleep(3000);
        	 System.out.println("User is not followed yet, now follow the user");
        	 driver.findElement(By.xpath(Constant.FollowBTN)).click();
        	 Thread.sleep(7000);
        	 driver.navigate().back();
          }
      }
      
      public void HomeTopStreamerFollowerFollowingList() throws InterruptedException
      {
    	  Thread.sleep(3000);
          driver.findElement(By.xpath(Constant.TopStreamerName)).click();
          Thread.sleep(2000);
          try
          {
        	 String BeforeFollowerCount= driver.findElement(By.xpath(Constant.FollowerLINK)).getText();
        	 System.out.println("Currently, the follower count is :" +BeforeFollowerCount);
        	 Thread.sleep(4000);
        	 driver.findElement(By.xpath(Constant.FollowBTN)).getText();
        	 driver.findElement(By.xpath(Constant.FollowBTN)).click();
        	 Thread.sleep(3000);
        	 String AfterFollowerCount=driver.findElement(By.xpath(Constant.FollowerLINK)).getText();
             System.out.println("After follow user, Follower Count is : " +AfterFollowerCount);
          }
          catch(Exception e)
          {
        	 String BeforeFollowerCount= driver.findElement(By.xpath(Constant.FollowerLINK)).getText();
        	 System.out.println("Currently, the follower count is :" +BeforeFollowerCount);
        	 Thread.sleep(4000);
        	 driver.findElement(By.xpath(Constant.UnFollowBTN)).getText();
        	 driver.findElement(By.xpath(Constant.UnFollowBTN)).click();
        	 Thread.sleep(3000);
        	 String AfterFollowerCount=driver.findElement(By.xpath(Constant.FollowerLINK)).getText();
             System.out.println("After Unfollow user, Follower Count is : " +AfterFollowerCount);
          }
      }
      
      public void HomeTopStreamerFollowerFollowingClickable() throws InterruptedException
      {
    	  try
    	  {
    		 String Status= driver.findElement(By.xpath(Constant.FollowBTN)).getText();
    		 System.out.println("Now the current status is : " +Status);
    		 driver.findElement(By.xpath("FollowerLINK")).click();
    		 Thread.sleep(4000);
    		 driver.findElement(By.xpath(Constant.FollowingLINK)).click();
             System.out.println("List not opened as status is :"+Status);
    		 
    	  }
    	  catch(Exception e)
    	  {
    		 Thread.sleep(8000);
    		 String Status= driver.findElement(By.xpath(Constant.UnFollowBTN)).getText();
     		 System.out.println("Now the current status is : " +Status);
     		 driver.findElement(By.xpath(Constant.FollowerLINK)).click();
     		 Thread.sleep(2000);
     		 driver.navigate().back();
     		 Thread.sleep(2000);
     		 driver.findElement(By.xpath(Constant.FollowingLINK)).click();
     		 driver.navigate().back();
     		 System.out.println("List opened as status is :"+Status); 
    	  }
      }
      
      public void AboutTab() throws Exception
      {
    	 driver.findElement(By.xpath(Constant.AboutTAB)).click();
    	 driver.findElement(By.xpath(Constant.GenderOPTION)).isDisplayed();
    	 System.out.println("Data Is Displayed In About Tab");
    	 driver.navigate().back();
    	 Thread.sleep(3000);
      }
      
      public void Trending()
      {
    	 String UserTitle = driver.findElement(By.xpath("//*[@id='tvUserTitle']")).getText();
    	 driver.findElement(By.xpath("//*[@id='tvUserTitle']")).click();
    	 // driver.findElement(By.xpath("//*[@id='tvVideoTitle']")).click();
    	 String TitleAfterClick = driver.findElement(By.xpath("//*[@id='tvVideoTitle']")).getText();
    	  
    	   if(UserTitle.equals(TitleAfterClick))
    	    {
    		  System.out.println("Correct video is opened");
    	    }

      }
}

          
          

      
	

	




