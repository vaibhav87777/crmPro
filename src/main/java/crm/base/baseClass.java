package crm.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.html.HTMLUListElement;

public class baseClass {
	
	public static WebDriver driver;
	public static Properties prop;
    public static String testData_PATH="C:\\Users\\user\\eclipse-workspace\\CRM_PRO\\src\\main\\java\\crm\\testData\\CRM pro.xlsx";
	
	public baseClass()
	{
		//data from properties file
		try {	FileInputStream file=new FileInputStream("C:\\Users\\user\\eclipse-workspace\\CRM_PRO\\src\\main\\java\\crm\\config\\crrmProProperties.properties");
		  prop=new Properties();  //Properties prop=new Properties();
			prop.load(file);}
			catch(FileNotFoundException e) {e.printStackTrace();}
		catch(IOException e1) {e1.printStackTrace();}
	}


//		//data from properties file
//		public static String getDataFromPropertiedfile(String Key) throws IOException
//		{
//		try {	FileInputStream file=new FileInputStream("C:\\Users\\user\\eclipse-workspace\\CRM_PRO\\src\\main\\java\\crm\\config\\crrmProProperties.properties");
//		  prop=new Properties();  //Properties prop=new Properties();
//			prop.load(file);}
//			catch(FileNotFoundException e) {e.printStackTrace();}
//			String value = prop.getProperty(Key);
//			return value;
//		}
		
	
		//brower initialization
		public static void browserInitialization()  
		{
			
		  String browsername = prop.getProperty("browser");
		

			//---pass options object in chromedriver consructor 
			if(browsername.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\eclipse-workspace\\CRM_PRO\\browsers\\chromedriver.exe");
				 driver=new ChromeDriver();
				 
			}
			else if(browsername.equalsIgnoreCase("headlessChrome"))
			{
				//code for headless browser  
			    ChromeOptions options= new ChromeOptions(); 
				options.addArguments("window-size=1400,800");
				//options.addArguments("headless");
				options.setHeadless(true);
				 driver=new ChromeDriver(options);
			}
			else if(browsername.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\user\\eclipse-workspace\\CRM_PRO\\browsers\\geckodriver.exe");
				 driver=new FirefoxDriver();
			}
			else if(browsername.equalsIgnoreCase("headlessFirefox"))
			{
				FirefoxOptions options1=new FirefoxOptions();
				options1.setHeadless(true);
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\user\\eclipse-workspace\\CRM_PRO\\browsers\\geckodriver.exe");
				 driver=new FirefoxDriver(options1);
				 
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			
			driver.get(prop.getProperty("url"));
			}
		
		   
			
		}
		

		
		
		



