package tests;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.MainPage;

import java.nio.charset.StandardCharsets;
import java.time.Duration;


public class OzonTest {

    static WebDriver webDriver = null;

    @BeforeAll
    static void setUp()  {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
        webDriver.manage().window().maximize();
    }

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

    @Test
    public void simpleTest() {
        webDriver.get((QueryGetter.getString("target.url")));
        String searchQ = new String(QueryGetter.getString("search.query")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        MainPage mainPage = new MainPage(webDriver).findBySearch(searchQ);
        mainPage.AddToCartItem(1);
        CartPage cartPage = mainPage.goToCart();
        Assertions.assertEquals(1, cartPage.getCartSize());
        cartPage.removeAll();
        Assertions.assertTrue(cartPage.cartIsEmpty());
    }
}
