package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test(priority = 1)
	public void addMealToCartTest() throws InterruptedException {
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		this.locationPopUpPage.closeDialog();
		this.mealPage.addMealToCart(4);
		Thread.sleep(500);
		Assert.assertTrue(this.notificationSystemPage.getMessageText().contains("The Following Errors Occurred:"), "[ERROR] Unexpected add to cart message");
		Assert.assertTrue(this.notificationSystemPage.getMessageText().contains("Please Select Location"), "[ERROR] Unexpected add to cart message");
		this.notificationSystemPage.waitUntilMessageDisappears();

		this.locationPopUpPage.getSelectLocationElement().click();
		Thread.sleep(500);
		this.locationPopUpPage.setLocation("City Center - Albany");
		Thread.sleep(500);
		this.mealPage.addMealToCart(4);
		Thread.sleep(500);
		Assert.assertTrue(this.notificationSystemPage.getMessageText().contains("Meal Added To Cart"), "[ERROR] Add to cart failed");
		
	}
	
	@Test(priority = 2)
	public void addMealToFavoriteTest() throws InterruptedException {
		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		this.locationPopUpPage.closeDialog();
		this.mealPage.addToFavourites();
		Thread.sleep(500);
		Assert.assertTrue(this.notificationSystemPage.getMessageText().contains("Please login first"), "[ERROR] Unexpected login message");
		this.notificationSystemPage.waitUntilMessageDisappears();

		driver.navigate().to(baseUrl + "guest-user/login-form");
		Thread.sleep(2000);
		this.loginPage.login(email, password);

		driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(1000);
		this.mealPage.addToFavourites();
		Thread.sleep(1000);
		Assert.assertTrue(this.notificationSystemPage.getMessageText().contains("Product has been added to your favorites"), "[ERROR] Add to favorites failed");
		this.notificationSystemPage.waitUntilMessageDisappears();

	}
	
	@Test(priority = 3)
	public void clearCartTest() throws InterruptedException, IOException {
		driver.get(baseUrl + "meals");
		this.locationPopUpPage.setLocation("City Center - Albany");
		
		File file = new File("data/Data.xlsx");	
		FileInputStream fis = new FileInputStream(file);	
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meals");
		
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			String mealUrl = sheet.getRow(i).getCell(0).getStringCellValue();
			int quantity = (int) sheet.getRow(i).getCell(1).getNumericCellValue();
			
			driver.get(mealUrl);
			this.mealPage.addMealToCart(quantity);
			Thread.sleep(500);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(this.notificationSystemPage.getMessageText().contains("Meal Added To Cart"), "[ERROR] Unexpected add to cart message");
		}
		
		this.cartSummaryPage.cartClearAll();
		Thread.sleep(500);
		Assert.assertTrue(this.notificationSystemPage.getMessageText().contains("All meals removed from Cart successfully"), "[ERROR] Unexpected remove from cart message");
		
		workbook.close();
		
	}
}
