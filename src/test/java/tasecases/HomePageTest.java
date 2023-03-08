package tasecases;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import crm.base.baseClass;
import crm.pages.ContactsPage;
import crm.pages.HomePage;
import crm.pages.LoginPage;
import crm.util.utilityClass;


public class HomePageTest extends baseClass {
	
	 HomePageTest()
	 {
		 super();
	 }
	LoginPage loginpage;
	HomePage homepage;
	utilityClass testUtil;
	ContactsPage contactpage;
	@BeforeMethod
	public void setUp()
	{
	  baseClass.browserInitialization();
	  loginpage=new LoginPage();
	  testUtil=new utilityClass();
	  contactpage=new ContactsPage();
	  homepage=loginpage.login(prop.getProperty("username"),prop.getProperty("password"));	
	}
	
	@Test
	public void verifyHomepageTitleTest()
	{
	String actuualtitle = homepage.getTitleOfHomePage();
	String expectedtitle = "CRMPRO";
	Assert.assertEquals(actuualtitle, expectedtitle,"Title of Homepage not matched");
	}
	@Test
	public void verifyuserLabelTest()
	{
		testUtil.switchToFrame();
		boolean flag = homepage.validateUsername();
		Assert.assertTrue(flag);
	}
	@Test
	public void verifyContactsLinkTest()
	{
		testUtil.switchToFrame();
		contactpage=homepage.clickOnContacts();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	
	
}
