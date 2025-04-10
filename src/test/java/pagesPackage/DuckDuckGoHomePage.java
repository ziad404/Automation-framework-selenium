package pagesPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class DuckDuckGoHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By searchField = By.id("searchbox_input");
    private By searchButton = By.className("searchbox_iconWrapper__qAk7y");
    private By logo = By.xpath("//section[contains(@class,'header_headerLeft')]/a/img");
    private By firstResultLink = By.cssSelector("a[data-testid='result-title-a']");
    private By resultLinks  = By.cssSelector("a[data-testid='result-title-a']");
    public DuckDuckGoHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,  Duration.ofSeconds(10)); // 10 seconds timeout

    }
    public String getSecondLinkText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultLinks));
        List<WebElement> links = driver.findElements(resultLinks);
       return links.get(1).getAttribute("href");
    }
    public String getFourthLinkText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultLinks));
        List<WebElement> links = driver.findElements(resultLinks);
        return links.get(6).getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
        return driver.findElement(logo).isDisplayed();
    }

    public void enterSearch(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchButton).click();
    }
    public String getFirstResultLink() {
        return driver.findElement(firstResultLink).getAttribute("href");
    }
    public String getSecondResultLink() {
        return driver.findElement(firstResultLink).getText();
    }
}
