package ProductDetailsTest;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.ProductDetails.ProductPage;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Listeners;
import reuse.AllureLog4jListener;
import reuse.BaseTest;
import java.io.IOException;

import static java.lang.invoke.MethodHandles.lookup;

@Listeners(AllureLog4jListener.class)
public class ProductSteps extends BaseTest {
    ProductPage productPage;
    SoftAssert softAssert;
    private final Logger log = LogManager.getLogger(lookup().lookupClass());

    @Before
    public void setup() {
        log.info("Initializing ProductPage...");
        productPage = new ProductPage(BaseTestDriver);
        log.info("ProductPage initialized successfully.");
    }

    @Step("Verifying user is logged in and on the Products page")
    @Given("I am logged in with valid credentials")
    public void i_am_logged_in_with_valid_credentials() {
        log.info("Verifying that the user is on the Products page...");
        Allure.step("Checking if user is on the Products page");
        productPage.Assertion_page_Title_Name("Products");
        log.info("Successfully logged in and on the Products page.");
    }

    @Step("Clicking on product: {productName} in inventory")
    @When("I click on a product {string} on the inventory page")
    public void i_click_on_a_product_on_the_inventory_page(String productName) {
        log.info("Clicking on product: {}", productName);
        Allure.step("Clicking on product: " + productName);
        productPage.clickOnProduct(productName);
        log.info("Product clicked successfully.");
    }

    @Step("Verifying the product details page is displayed")
    @Then("I should see the product details page")
    public void i_should_see_the_product_details_page() {
        log.info("Verifying the product details page...");
        Allure.step("Checking if product details page is displayed");
        productPage.assertDetailsPage();
        log.info("Product details page verified successfully.");
    }

    @Step("Verifying product details: name, description, price, and image")
    @Then("I should see the product name, description, price, and image")
    public void i_should_see_the_product_name_description_price_and_image() {
        log.info("Validating product details (name, description, price)...");
        Allure.step("Validating product details");
        softAssert = new SoftAssert();
        String name = productPage.getProductName();
        String desc = productPage.getProductDescription();
        String price = productPage.getProductPrice();

        softAssert.assertFalse(name.isEmpty(), "Product name should not be empty");
        softAssert.assertFalse(desc.isEmpty(), "Product description should not be empty");
        softAssert.assertFalse(price.isEmpty(), "Product price should not be empty");

        log.debug("Product Name: {}", name);
        log.debug("Product Description: {}", desc);
        log.debug("Product Price: {}", price);

        Allure.step("Product Name: " + name);
        Allure.step("Product Description: " + desc);
        Allure.step("Product Price: " + price);

        softAssert.assertAll();
        log.info("Product details validation completed successfully.");
    }

    @Step("Opening slide menu and selecting 'All Items'")
    @Then("Click on view all items from slide menu")
    public void Click_on_view_all_items_from_slide_menu() {
        log.info("Opening slide menu...");
        Allure.step("Opening slide menu");
        productPage.clickOpenMenu();
        log.info("Clicking on 'All Items'...");
        Allure.step("Clicking on 'All Items'");
        productPage.clickAllItems();
        log.info("'All Items' clicked successfully.");
    }

    @Step("Navigating to product details page for: {productName}")
    @Given("I am on the product {string} details page")
    public void i_am_on_the_product_details_page(String productName) {
        log.info("Navigating to product details page for: {}", productName);
        Allure.step("Navigating to product details page: " + productName);
        productPage.clickOnProduct(productName);
        log.info("Successfully navigated to product details page.");
    }

    @Step("Clicking 'Add to Cart' button")
    @When("I click on the Add to Cart button")
    public void i_click_on_the_add_to_cart_button() {
        log.info("Clicking 'Add to Cart' button...");
        Allure.step("Clicking 'Add to Cart' button");
        productPage.clickAddToCart();
        log.info("Product added to cart.");
    }

    @Step("Verifying the product is added to the cart")
    @Then("the product should be added to the cart")
    public void the_product_should_be_added_to_the_cart() {
        log.info("Verifying if the product was added to the cart...");
        Allure.step("Checking if product was added to the cart");
        softAssert = new SoftAssert();
        softAssert.assertTrue(productPage.isProductInCart(), "Product was not added to the cart!");

        if (productPage.isProductInCart()) {
            log.info("Product successfully added to the cart.");
            Allure.step("Product successfully added to the cart.");
        } else {
            log.error("Product was NOT added to the cart.");
            Allure.step("Product was NOT added to the cart.");
        }

        softAssert.assertAll();

        // Returning to the inventory page
        productPage.clickOpenMenu();
        productPage.clickAllItems();
    }
    @Description("Take pic for failed Scenario")
    @After
    public void failedScenarioScreen(Scenario scenario) throws IOException {
        failedTestCaseScreen(scenario);
    }
}
