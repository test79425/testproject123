package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.QueryGetter;

public class MainPage {

    private final WebDriver driver;
    @FindBy(css = "input[placeholder]")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@data-widget='searchResultsV2']/div")
    private WebElement itemsTableOnSearchPage;

    private By items = By.xpath("//div[text()=\"В корзину\"]/../..");

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public MainPage findBySearch(String query) {
        searchInput.sendKeys(query);
        searchInput.sendKeys(Keys.RETURN);
        return this;
    }

    public MainPage addToCartItem(int i) {
        addToCart(itemsTableOnSearchPage.findElements(By.xpath("div")).get(i));
        return this;
    }

    private void addToCart(WebElement item) {
        item.findElements(items).get(1).click();
    }

    public CartPage goToCart() {
        driver.get(QueryGetter.getString("cart.url"));
        return new CartPage(driver);
    }
}
