/**
 * 
 */
package com.devOps.helloworld.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
/**
 * 
 *
 */
public class IE {

	private WebDriver driver;
	private static final String NAME = "Admin";
	private static final String EMAIL = "admin@deloittedev.ops";
	private static final String PHONE = "123.456.7890";
	
	// use of stringbuilder to replace the @ is necessary for some browsers (not IE)
	StringBuilder sb = new StringBuilder(EMAIL);
	String emailValue = sb.toString().replace("@", "%40");

	@Before
	public void setUp() {
		// Points maven at the driver necessary for using Internet Explorer
		System.setProperty("webdriver.ie.driver","src\\test\\resources\\drivers\\IEDriverServer.exe");
		
		// IE requires a lot of configuration to test, use of the 'flakiness' method bypasses much of that configuration
		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		driver = new InternetExplorerDriver(ieOptions.introduceFlakinessByIgnoringSecurityDomains());
		
		// Maximizes the browser
		driver.manage().window().maximize();
		// Navigate to the URL
		driver.get("http://10.118.45.4:8080/HelloWorld/");
	  }
	
	@Test
	public void testWebElements() {
		// Find the text input element by its name or xpath
		WebElement element1 = driver.findElement(By.name("name"));
		WebElement element2 = driver.findElement(By.name("email"));
		WebElement element3 = driver.findElement(By.name("phoneNumber"));
		WebElement element4 = driver.findElement(By.xpath("/html/body/form/input[4]"));
		
		// Enter a value for each field		
		element1.sendKeys(NAME);
		element2.sendKeys(EMAIL);
		element3.sendKeys(PHONE);
		
		// Hit "Enter" on the Submit button
		element4.sendKeys(Keys.ENTER);
		
		// gets the URL from the second page, and checks to make sure it matches "http://10.118.45.4:8080/HelloWorld/hello?name="
		// if it does not match, it will wait 10 seconds before failing
		new WebDriverWait(driver, 20).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webD) {
				return webD.getCurrentUrl().startsWith("http://10.118.45.4:8080/HelloWorld/hello?name=");
			}
		});
		
		// asserts that the values entered properly create the new URL for the user
		assertEquals("http://10.118.45.4:8080/HelloWorld/hello?name=" + NAME + "&email=" + EMAIL + "&phoneNumber=" + PHONE,
				driver.getCurrentUrl());
	}

	@After
	public void tearDown() throws Exception {
		// Close the browser
		driver.quit();
	}
}
