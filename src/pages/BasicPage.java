package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicPage {

	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;

	public BasicPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		this.driver = driver;
		this.waiter = waiter;
		this.js = js;

	}


}
