package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}
	
	public WebElement getEditProfileButton() {
		return driver.findElement(By.xpath("//div[@class='action-profile']/a[2]"));
		
	}

	public WebElement getFirstNameField() {
		return driver.findElement(By.name("user_first_name"));

	}

	public WebElement getLastNameField() {
		return driver.findElement(By.name("user_last_name"));

	}

	public WebElement getEmailField() {
		return driver.findElement(By.name("user_email"));

	}

	public WebElement getAddressField() {
		return driver.findElement(By.name("user_address"));

	}

	public WebElement getPhoneNumberField() {
		return driver.findElement(By.name("user_phone"));

	}

	public WebElement getZipCodeField() {
		return driver.findElement(By.name("user_zip"));

	}

	public Select getCountryField() {
		WebElement selectCountry = driver.findElement(By.name("user_country_id"));
		Select select = new Select(selectCountry);
		return select;

	}

	public Select getStateField() {
		WebElement selectState = driver.findElement(By.name("user_state_id"));
		Select select = new Select(selectState);
		return select;

	}

	public Select getCityField() {
		WebElement selectCity = driver.findElement(By.name("user_city"));
		Select select = new Select(selectCity);
		return select;

	}

	public WebElement getSaveButton() {
		return driver.findElement(By.xpath("//input[@class ='btn btn--primary block-on-mobile']"));

	}

	public WebElement getUploadImageButton() {
		return driver.findElement(By.xpath("//*[@class='upload uploadFile-Js']"));
		
	}

	public WebElement getUploadImageField() {
		return driver.findElement(By.xpath("//input [@type = \"file\"]"));
		
	}

	public void uploadImg(String imagePath) {
		js.executeScript("arguments[0].click();", this.getUploadImageButton());
		this.getUploadImageField().sendKeys(imagePath);
		
	}

	public WebElement getRemoveImageButton() {
		return driver.findElement(By.className("remove"));

	}

	public void removeImage() {
		js.executeScript("arguments[0].click();", this.getRemoveImageButton());

	}

	public void changePersonalInformation (String firstName, String lastName, String address, String phoneNumber,
			String zipCode, String country, String state, String city) throws InterruptedException {

		this.getFirstNameField().clear();
		this.getFirstNameField().sendKeys(firstName);

		this.getLastNameField().clear();
		this.getLastNameField().sendKeys(lastName);

		this.getAddressField().clear();
		this.getAddressField().sendKeys(address);

		this.getPhoneNumberField().clear();
		this.getPhoneNumberField().sendKeys(phoneNumber);

		this.getZipCodeField().clear();
		this.getZipCodeField().sendKeys(zipCode);
		
		this.getCountryField().selectByVisibleText(country);
		Thread.sleep(500);
		
		this.getStateField().selectByVisibleText(state);
		Thread.sleep(500);
		
		this.getCityField().selectByVisibleText(city);
		Thread.sleep(500);
		
		this.getSaveButton().submit();
		Thread.sleep(1000);

	}
}