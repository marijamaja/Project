package tests;

import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopUpPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait waiter;
	protected SoftAssert softAssert;

	protected String baseUrl;
	protected String email;
	protected String password;
	protected String locationName;

	protected LocationPopUpPage locationPopUpPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;

	@BeforeMethod
	public void setUp() throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		this.waiter = new WebDriverWait(driver, 15);
		this.js = (JavascriptExecutor) driver;

		this.baseUrl = "http://demo.yo-meals.com/";
		this.email = "customer@dummyid.com";
		this.password = "12345678a";

		this.locationPopUpPage = new LocationPopUpPage(driver, waiter, js);
		this.loginPage = new LoginPage(driver, waiter, js);
		this.profilePage = new ProfilePage(driver, waiter, js);
		this.notificationSystemPage = new NotificationSystemPage(driver, waiter, js);
		this.authPage = new AuthPage(driver, waiter, js);
		this.mealPage = new MealPage(driver, waiter, js);
		this.cartSummaryPage = new CartSummaryPage(driver, waiter, js);
		this.searchResultPage = new SearchResultPage(driver, waiter, js);

	}

	@AfterMethod
	public void takeScreenshot(ITestResult result) throws IOException, InterruptedException {
		
		if (ITestResult.FAILURE == result.getStatus()) {

			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			String screenshotDate = new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm-ss'.png'").format(new Date());
			FileHandler.copy(source, new File("./screenshots/" + result.getName() + " - " + screenshotDate + ".png"));
		}
		Thread.sleep(1000);
		driver.quit();
		
	}
}
