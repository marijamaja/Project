package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}

	public WebElement getQuantityField() {
		return driver.findElement(By.name("product_qty"));

	}

	public WebElement getAddToCartButton() {
		return driver.findElement(By.linkText("Add To Cart"));

	}

	public WebElement getFavouritesButton() {
		return driver.findElement(By.id("item_119"));

	}

	public void addMealToCart(int mealQuantity) throws InterruptedException {
		String quantity = Integer.toString(mealQuantity);
		
		this.getQuantityField().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		Thread.sleep(500);
		
		this.getQuantityField().sendKeys(quantity);
		Thread.sleep(500);
		
		this.getAddToCartButton().click();
		
	}
	
	public void addToFavourites() {
		this.getFavouritesButton().click();
		
	}
}