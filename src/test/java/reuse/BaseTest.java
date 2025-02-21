package reuse;

import WebDriverMangeFactory.GetWebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;

@Listeners(AllureLog4jListener.class)
public class BaseTest extends GetWebDriver {
    public static final Logger log = LogManager.getLogger(lookup().lookupClass());
    public static WebDriver BaseTestDriver;
    protected static String browser;
    protected static String url;
    protected static String mode;

    @Description("Open Browser")
    public void Open_Browser() {
        log.info("Starting test Setup execution");
        ConfigLoader configLoader = new ConfigLoader("./src/main/resources/config.properties");

        browser = configLoader.getProperty("browser");
        mode = configLoader.getProperty("mode");
        url = configLoader.getProperty("url");

        log.info("Configurations:");
        log.info("Browser: {}", browser);
        log.info("Mode: {}", mode);
        log.info("URL: {}", url);

        try {
            log.info("Launching browser: {} in mode: {}", browser, mode);
            BaseTestDriver = launchBrowser(browser, mode);
            BaseTestDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            BaseTestDriver.get(url);
            log.info("Successfully launched browser and navigated to URL");
        } catch (Exception e) {
            log.error("An error occurred while initializing the browser: {}", browser, e);
        }
    }

    @Description("Close the browser")
    public void Close_browser() {
        if (BaseTestDriver != null) {
            log.info("Closing the browser instance");
            BaseTestDriver.quit();
            log.info("Browser instance closed successfully");
        } else {
            log.warn("Attempted to close the browser but BaseTestDriver is null");
        }
    }

    @Description("Take pic for failed Scenario")
    public void failedTestCaseScreen(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            log.warn("Test scenario failed: {}", scenario.getName());
            try {
                File screenshot = Screenshots.takeshots(BaseTestDriver, ".\\screenshots\\" + scenario.getName() + ".png");
                Screenshots.takeshots(BaseTestDriver, scenario.getName());

                log.info("Screenshot taken for failed scenario: {}", scenario.getName());
                Allure.addAttachment("Page Screenshot", FileUtils.openInputStream(screenshot));
                Allure.addAttachment("Retry Analyzer Backlog", FileUtils.openInputStream(new File("./logs/RetryError.csv")));
            } catch (IOException e) {
                log.error("Error while taking screenshot for failed scenario: {}", scenario.getName(), e);
            }
        }
    }
}
