package tasecases;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import crm.base.baseClass;
import crm.pages.CallPage;
import crm.pages.HomePage;
import crm.pages.LoginPage;
import crm.util.utilityClass;

public class CallPageTest extends baseClass {
	CallPageTest()
	{
		super();
	}
	HomePage homepage;
	utilityClass testUtil;
	CallPage callpage;
	int TCID;
	
	@BeforeMethod
	public void tearUp() 
	{
		browserInitialization();
		homepage = new HomePage();
		testUtil=new utilityClass();
		LoginPage loginpage =new LoginPage();
		homepage=loginpage.login(prop.getProperty("username"),prop.getProperty("password"));
		testUtil.switchToFrame();
		callpage=homepage.clickOnCallLInk();
	}
	
	
	@DataProvider
	public Object[][] addinputsTxtInfo() 
	{
		return utilityClass.getMultiTestDataCredentials("calldata");
	}
	
	
	@Test(priority=1)
	public void verifyTitleOfCallPage()
	{
		
		Assert.assertEquals(callpage.getTitleOfCallPage(), "CRMPRO");
	}
	@Test 
	public void verifyLabelOnCallPage()
	{
		Assert.assertTrue(callpage.validatecallPageLabel());
	}
	@Test(priority=3,dataProvider="addinputsTxtInfo")
	public void verifyaddedInformation(String Deal, String Task, String Case,String Note)
	{ TCID=601;
		callpage.addInformationAndSave(Deal, Task, Case, Note);
	}
	
	
	
	
	
	
	@ AfterMethod
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
	}
}
