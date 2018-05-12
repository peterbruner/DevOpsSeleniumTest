/**
 * 
 */
package com.devOps.helloworld.testing;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChromeTest {

	private WebDriver driver;
	private User user1 = new User("Bruce", "Wayne", "b@tman", "cave", "1 Wayne way", "", "Gotham", "NY?", "10108", "USA", "CEO", "bruce@wayneenterprises.com", "555.555.5555");
	private User user2 = new User("Peter", "Parker", "$piderman", "labratory", "123 Queens Ave", "", "New York City", "NY", "10108", "USA", "Student", "peter@empirestate.edu", "555.555.5555");
	private User user3 = new User("Admin", "Istrator", "Professor1", "password_:(", "123 Not Main St", "#1", "Quahog", "MA", "10101", "USA", "Professor", "admin@deloittedev.ops", "123.456.7890");
	private ArrayList<User> users = new ArrayList<User>();
	private DbTesting dbTest = new DbTesting();
	// use of stringbuilder to replace the @ is necessary for some browsers (not IE)
	StringBuilder sb = new StringBuilder("");
	String emailValue = sb.toString().replace("@", "%40");
		
	@Before
	public void setUp() throws Exception {
		
		// Points maven at the gecko driver necessary for using firefox
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		// Launch a new Chrome instance
		driver = new ChromeDriver();
		// Maximize the browser window
		driver.manage().window().maximize();
		// Navigate to HelloWorld
		//driver.get("http://localhost:8080/HelloWorld/"); //for testing on server
		driver.get("http://10.118.45.4:8080/HelloWorld/"); //for testing on local machine
		//Adds the pre-populated users to the arraylist 'users'
		Collections.addAll(users, user1, user2, user3);
		//Adds all database entries into the arraylist 'dbUsers'
		ArrayList<User> dbUsers = dbTest.readData();
		//displays the number of database entries prior to the test
		System.out.println("Number of users in the database before the test: " + dbUsers.size());
	}
	
	@Test
	//named to be the first @Test alphabetically for junit to run in alpha order
	public void testOneWebElements() throws InterruptedException, ClassNotFoundException {
		
		// Maps all the elements on the page according to their 'name' tag
		WebElement textBoxFirstName = driver.findElement(By.name("firstName"));
		WebElement textBoxLastName = driver.findElement(By.name("lastName"));
		WebElement textBoxUserName = driver.findElement(By.name("userName"));
		WebElement textBoxPassword1 = driver.findElement(By.name("password1"));
		WebElement textBoxPassword2 = driver.findElement(By.name("password2"));
		WebElement textBoxAddress1 = driver.findElement(By.name("address1"));
		WebElement textBoxAddress2 = driver.findElement(By.name("address2"));
		WebElement textBoxCity = driver.findElement(By.name("city"));
		WebElement textBoxState = driver.findElement(By.name("state"));
		WebElement textBoxZip = driver.findElement(By.name("zip"));
		WebElement textBoxCountry = driver.findElement(By.name("country"));
		WebElement textBoxEmail = driver.findElement(By.name("email"));
		WebElement textBoxPhoneNumber = driver.findElement(By.name("phoneNumber"));
		WebElement textBoxTitle = driver.findElement(By.name("title"));
		WebElement buttonRegister = driver.findElement(By.name("btnRegister")); //register button
		WebElement buttonReset = driver.findElement(By.name("btnReset")); //reset button
		WebElement buttonAdmin = driver.findElement(By.name("btnSubmit")); //admin button
		//WebElement element = driver.findElement(By.xpath("/html/body/form/input[4]"));

		Actions action = new Actions(driver);
		//WebElement we = driver.findElement(By.xpath("html/body/div[13]/ul/li[4]/a"));
		//action.moveToElement(we).moveToElement(webdriver.findElement(By.xpath("/expression-here"))).click().build().perform();
		//action.moveToElement(buttonReset).perform();
		

		// Enters a value for each field, resets the fields, then enters the values again and submits them. Intentional pauses for audience to see what's occuring
		for (int loop = 0; loop < users.size()-1; loop++)
		{
			textBoxFirstName.sendKeys(users.get(loop).getFirstName());
			textBoxLastName.sendKeys(users.get(loop).getLastName());
			textBoxUserName.sendKeys(users.get(loop).getUserName());
			textBoxPassword1.sendKeys(users.get(loop).getPassword());
			textBoxPassword2.sendKeys(users.get(loop).getPassword());
			textBoxAddress1.sendKeys(users.get(loop).getAddress1());
			textBoxAddress2.sendKeys(users.get(loop).getAddress2());
			textBoxCity.sendKeys(users.get(loop).getCity());
			textBoxState.sendKeys(users.get(loop).getState());
			textBoxZip.sendKeys(users.get(loop).getZipcode());
			textBoxCountry.sendKeys(users.get(loop).getCountry());
			textBoxEmail.sendKeys(users.get(loop).getEmail());
			textBoxPhoneNumber.sendKeys(users.get(loop).getPhoneNumber());
			textBoxTitle.sendKeys(users.get(loop).getTitle());
			
			// Pause for audience to see result
			Thread.sleep(1000);
			// Logic to demonstrate use of different buttons on the page
			if (loop+1 < users.size()-1) {
				//action.moveToElement(buttonReset).build().perform(); //doesnt show cursor move
				buttonReset.click();
			}
			else {
				buttonRegister.click();
			}
		}
		// Pause for audience to see result
		Thread.sleep(1000);

		// gets the URL from the second page, and checks to make sure it matches "http://10.118.45.4:8080/HelloWorld/hello"
		// if it does not match, it will wait 5 seconds before failing
		new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webD) {
//				return webD.getCurrentUrl().startsWith("http://localhost:8080/HelloWorld/hello"); 
				return webD.getCurrentUrl().startsWith("http://10.118.45.4:8080/HelloWorld/hello");
			}
		});
		
//		assertEquals("http://localhost:8080/HelloWorld/hello", driver.getCurrentUrl());
		assertEquals("http://10.118.45.4:8080/HelloWorld/hello", driver.getCurrentUrl());
	}
	
	@Test
	//named to be the second @Test alphabetically for junit to run in alpha order
	public void testTwoDatabaseEntries() throws Exception {
		ArrayList<User> dbUsers = dbTest.readData();
		
		Assert.assertEquals(users.get(users.size()-2).getUserName(), dbUsers.get(dbUsers.size()-1).getUserName());
		Assert.assertEquals(users.get(users.size()-2).getFirstName(), dbUsers.get(dbUsers.size()-1).getFirstName());
		Assert.assertEquals(users.get(users.size()-2).getLastName(), dbUsers.get(dbUsers.size()-1).getLastName());
		System.out.println(users.get(users.size()-2).getUserName() + "  " + dbUsers.get(dbUsers.size()-1).getUserName());
	}

	@After
	public void tearDown() throws Exception {
		// Adds all database entries into the arraylist 'dbUsers' and prints the number of entries
		ArrayList<User> dbUsers = dbTest.readData();
		// Demonstrates difference in number of entries 
		System.out.println("Number of users in the database after the test: " + dbUsers.size());
		// Shows the highest UserId in the database
		System.out.println("Most recently used UserId is: " + dbUsers.get(dbUsers.size()-1).getUserId());
		
		// Close the browser
		driver.quit();
	}
}
