package testPackage;

import org.example.engine.Bot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pagesPackage.DuckDuckGoHomePage;

import static org.testng.Assert.*;

public class DuckDuckGoTests {
    private WebDriver driver;
    private DuckDuckGoHomePage homePage;
    private Bot bot;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setBrowserVersion("134");

        driver = new ChromeDriver(options);
        bot = new Bot(driver);
        homePage = new DuckDuckGoHomePage(bot);

        driver.get("https://duckduckgo.com/");
    }

    @Test
    public void testTitleIsNotGoogle() {
        assertNotEquals(homePage.getTitle(), "Google", "Page title should not be Google");
    }

    @Test
    public void testLogoDisplayed() {
        assertTrue(homePage.isLogoDisplayed(), "Logo should be displayed");
    }

    @Test
    public void testFirstSearchResultLink() {
        homePage.enterSearch("Selenium WebDriver");
        assertEquals(
                homePage.getFirstResultLink(),
                "https://www.selenium.dev/documentation/webdriver/",
                "First link should be Selenium docs"
        );
    }

    @Test
    public void testSecondSearchResultContainsLinkedIn() {
        homePage.enterSearch("Cucumber IO");
        String secondLink = homePage.getSecondLinkText();
        assertNotEquals(secondLink, "https://www.linkedin.com", "Second link should not be LinkedIn");
    }

    @Test
    public void testFourthSearchTextContainsTestNg() {
        homePage.enterSearch("TestNG");
        String fourthText = homePage.getFourthLinkText();
        assertEquals(fourthText, "TestNG Tutorial", "Fourth result text should be 'TestNG Tutorial'");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
