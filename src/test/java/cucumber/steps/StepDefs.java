package cucumber.steps;


import cucumber.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.CartPage;
import pages.MainPage;
import utility.QueryGetter;

import java.nio.charset.StandardCharsets;

public class StepDefs {
    MainPage mainPage;
    CartPage cartPage;

    @Given("open browser on ozon.ru")
    public void firstStep() {
        Hooks.webDriver.get((QueryGetter.getString("target.url")));
    }


    @When("Find item by name")
    public void secondStep() {
        String searchQ = new String(QueryGetter.getString("search.query")
                .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        mainPage = new MainPage(Hooks.webDriver).findBySearch(searchQ);
    }

    @When("Add first item to cart")
    public void thirdStep() {
        mainPage.addToCartItem(1);
        cartPage = mainPage.goToCart();
    }

    @Then("Added item in cart exists")
    public void fourthStep() {
        Assertions.assertEquals(1, cartPage.getCartSize());
    }

    @When("Remove Item from cart")
    public void fifthStep() {
        cartPage.removeAll();
        cartPage = mainPage.goToCart();
    }

    @Then("Cart is empty")
    public void sixthStep() {
        Assertions.assertTrue(cartPage.cartIsEmpty());
    }
}
