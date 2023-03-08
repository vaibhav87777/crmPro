package tasecases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import crm.base.baseClass;
import crm.pages.HomePage;
import crm.pages.LoginPage;
import crm.util.utilityClass;


public class LoginPageTest extends baseClass {
	LoginPageTest()
	{
		super();
	}
	LoginPage loginpage;
	HomePage homepage;
	int TCID;
	Logger log;
	
	@BeforeMethod
	public void setUp()
	{
		//Logger log = Logger.getLogger(loginPageTest.class);
		 log=Logger.getLogger("loginPageTest");
		PropertyConfigurator.configure("log4j.properties");
				
	    
		browserInitialization();
		 loginpage=new LoginPage();
		 
		 
		    log.info("entering application URL");
			log.warn("Hey this just a warning message");
			log.fatal("hey this is just fatal error message");
			log.debug("this is debug message");
	}
	
	
	@Test(priority=1)
	public void verifyloginPageTitleTest() throws InterruptedException
	{ TCID=101;
	log.info("*** starting test case ***");
	log.info("***freeCrmTitleTest***");
	
		Thread.sleep(3000);
		
		String actualtitle = loginpage.getTitleOfLoginPage();
		System.out.println(actualtitle);
		
		log.info("fetched titlt of login page="+actualtitle);
		
		String expectedtitle ="CRMPRO - CRM software for customer relationship management, sales, and support.";
		Assert.assertEquals(actualtitle, expectedtitle);
		
		log.info("*** ending test case***");
		log.info("** freeCrmTitleTest ***");
	}
	@Test(priority=2)
	public void crmLogoTest()
	{
		
		TCID=102;
		boolean result = loginpage.validateCRMImageLogo();
		Assert.assertTrue(result);
	}
	
	@Test(priority=3)
	public void verifyloginTest()
	{
		TCID=102;
	homepage=loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
	
	}
	

	@AfterMethod
	public void logOut(ITestResult result) 
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			utilityClass.captureSreenshot(driver,TCID);	
		}
		 
		driver.close();
	
		}

	
@AfterClass
public void tearDown()
{
	driver.quit();
	log.info("****** Browser is closed *******");
}
}
