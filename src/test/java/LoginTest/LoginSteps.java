package LoginTest;

import io.cucumber.java.After;
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
import org.example.loginPage.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import reuse.AllureLog4jListener;
import reuse.BaseTest;

import java.io.IOException;

import static java.lang.invoke.MethodHandles.lookup;

@Listeners(AllureLog4jListener.class)
public class LoginSteps extends BaseTest {
    LoginPage lg;
    private final Logger log = LogManager.getLogger(lookup().lookupClass());

    @Before
    public void setup() throws Exception {
        log.info("Initializing browser...");
        Open_Browser();
        lg = new LoginPage(BaseTestDriver);
        log.info("Browser opened and LoginPage object initialized.");
    }

    @Step("Verifying SauceDemo login page is displayed")
    @Given("I am on the Saucedemo login page")
    public void i_am_on_the_saucedemo_login_page() {
        log.info("Verifying the SauceDemo login page title...");
        Allure.step("Checking login page title");
        String actualTitle = BaseTestDriver.getTitle();
        log.debug("Actual page title: {}", actualTitle);
        Assert.assertEquals(actualTitle, "Swag Labs", "Login page title does not match!");
        log.info("Login page verified successfully.");
    }

    @Step("Entering username: {username} and password")
    @When("I enter the username {string} and the password {string}")
    public void i_enter_the_username_and_the_password(String username, String password) {
        log.info("Entering username: {}", username);
        Allure.step("Entering username: " + username);
        lg.enterUserName(username);
        log.info("Entering password");
        Allure.step("Entering password");
        lg.enterPassword(password);
        log.info("Username and password entered successfully.");
    }

    @Step("Clicking on the login button")
    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        log.info("Clicking on the login button...");
        Allure.step("Clicking on the login button");
        lg.clickLogin();
        log.info("Login button clicked.");
    }

    @Step("Verifying redirection to the inventory page")
    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        log.info("Verifying redirection to the inventory page...");
        Allure.step("Checking if redirected to Inventory page");
        lg.Assertion_page_Title_Name("Products");
        log.info("Successfully redirected to the inventory page.");
    }
    @Description("Take pic for failed Scenario")
    @After
    public void failedScenarioScreen(Scenario scenario) throws IOException {
        failedTestCaseScreen(scenario);
    }
}
