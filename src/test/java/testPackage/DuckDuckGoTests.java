package testPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesPackage.DuckDuckGoHomePage;

import static org.testng.Assert.*;

public class DuckDuckGoTests {
    private WebDriver driver;
    private DuckDuckGoHomePage homePage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://duckduckgo.com/");
        homePage = new DuckDuckGoHomePage(driver);
    }
// Test for #1
    @Test
    public void testTitleIsNotGoogle() {
        assertNotEquals(homePage.getTitle(), "Google", "Page title should not be Google");
    }
// Test for #2
    @Test
    public void testLogoDisplayed() {
        assertTrue(homePage.isLogoDisplayed(), "Logo should be displayed");
    }
// Test for #3
    @Test
    public void testFirstSearchResultLink() {
        homePage.enterSearch("Selenium WebDriver");
        assertEquals(homePage.getFirstResultLink(), "https://www.selenium.dev/documentation/webdriver/");
    }
    // Test for #4
    @Test
    public void testSecondSearchResultContainsLinkedIn() {
        homePage.enterSearch("Cucumber IO");
        //System.out.println(homePage.getSecondLinkText());
        assertNotEquals(homePage.getSecondLinkText(), "https://www.linkedin.com");
    }
    // Test for #5
    @Test
    public void testFourthSearchTextContainsTestNg() {
        homePage.enterSearch("TestNG");
        //System.out.println(homePage.getSecondLinkText());
        assertEquals(homePage.getFourthLinkText(), "TestNG Tutorial");
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
