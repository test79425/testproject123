package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    By removeAllButton = By.cssSelector("div[data-widget=\"controls\"] > span");
    By items = By.xpath("//span[contains(text(),'В избранное')]");
    By confirmRemoveButton = By.xpath("//div[contains(text(),'Удалить')]/../..");
    By emptyCartLabel = By.xpath("//h1[contains(text(),'пуста')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage removeAll() {
        driver.findElement(removeAllButton).click();
        driver.findElement(confirmRemoveButton).click();
        return this;
    }

    public int getCartSize() {
        return driver.findElements(items).size();
    }

    public boolean cartIsEmpty() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(200));
            wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartLabel));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }
}
