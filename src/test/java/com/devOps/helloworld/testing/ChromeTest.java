/**
 * 
 */
package com.devOps.helloworld.testing;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import org.junit.*;

/**
 * 
 *
 */
public class ChromeTest {

	private WebDriver driver;
	private static final String FIRSTNAME = "Admin";
	private static final String LASTNAME = "Istrator";
	private static final String USERNAME = "Professor1";
	private static final String PASSWORD = "password_:(";
	private static final String ADDRESS1 = "123 Not Main St";
	private static final String ADDRESS2 = "#1";
	private static final String CITY = "Quahog";
	private static final String STATE = "MA";
	private static final String ZIPCODE = "10101";
	private static final String COUNTRY = "USA";
	private static final String TITLE = "Professor";
	private static final String EMAIL = "admin@deloittedev.ops";
	private static final String PHONE = "123.456.7890";
	
	// use of stringbuilder to replace the @ is necessary for some browsers (not IE)
	StringBuilder sb = new StringBuilder(EMAIL);
	String emailValue = sb.toString().replace("@", "%40");

	@Before
	public void setUp() {
		// Points maven at the gecko driver necessary for using firefox
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		// Launch a new Chrome instance
		driver = new ChromeDriver();
		// Maximize the browser window
		driver.manage().window().maximize();
		// Navigate to HelloWorld
		driver.get("http://localhost:8080/HelloWorld/"); //for testing on server
		//driver.get("http://10.118.45.4:8080/HelloWorld/"); //for testing on local machine
	}

	@Test
	public void testWebElements() {
		// Find the text input element by its name or xpath
		WebElement element1 = driver.findElement(By.name("firstName"));
		WebElement element2 = driver.findElement(By.name("lastName"));
		WebElement element3 = driver.findElement(By.name("userName"));
		WebElement element4 = driver.findElement(By.name("password1"));
		WebElement element16 = driver.findElement(By.name("password2"));
		WebElement element5 = driver.findElement(By.name("address1"));
		WebElement element6 = driver.findElement(By.name("address2"));
		WebElement element7 = driver.findElement(By.name("city"));
		WebElement element8 = driver.findElement(By.name("state"));
		WebElement element9 = driver.findElement(By.name("zip"));
		WebElement element10 = driver.findElement(By.name("country"));
		WebElement element11 = driver.findElement(By.name("email"));
		WebElement element12 = driver.findElement(By.name("phoneNumber"));
		WebElement element13 = driver.findElement(By.name("title"));
		//WebElement element4 = driver.findElement(By.xpath("/html/body/form/input[4]"));
		WebElement element14 = driver.findElement(By.className("btn")); //finds the first item of type btn
		WebElement element15 = driver.findElement(By.className("btn")); //also finds the first item of type btn

		// Enter a value for each field
		element1.sendKeys(FIRSTNAME);
		element2.sendKeys(LASTNAME);
		element3.sendKeys(USERNAME);
		element4.sendKeys(PASSWORD);
		element16.sendKeys(PASSWORD);
		element5.sendKeys(ADDRESS1);
		element6.sendKeys(ADDRESS2);
		element7.sendKeys(CITY);
		element8.sendKeys(STATE);
		element9.sendKeys(ZIPCODE);
		element10.sendKeys(COUNTRY);
		element11.sendKeys(EMAIL);
		element12.sendKeys(PHONE);
		element13.sendKeys(TITLE);

		// Click the Submit button
		//element15.sendKeys(Keys.ENTER);		

		// gets the URL from the second page, and checks to make sure it matches "http://10.118.45.4:8080/HelloWorld/hello?name="
		// if it does not match, it will wait 10 seconds before failing
//		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
//			public Boolean apply(WebDriver webD) {
////				return webD.getCurrentUrl().startsWith("http://localhost:8080/HelloWorld/hello?name=");
//				return webD.getCurrentUrl().startsWith("http://localhost:8080/HelloWorld/hello");
//			}
//		});
		
		//Had to change NAME to FIRSTNAME, however that feature seems to be deprecated
		
		// asserts that the values entered properly create the new URL for the user
//		assertEquals("http://localhost:8080/HelloWorld/hello?name=" + FIRSTNAME + "&email=" + emailValue + "&phoneNumber=" + PHONE,
//				driver.getCurrentUrl());
	}

	@After
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}
}
