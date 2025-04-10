package org.example.engine;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class Bot {
    private final WebDriver driver;
    private final Wait<WebDriver> wait;

    public Bot(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NotFoundException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public Bot sendKeys(By by, String text) {
        wait.until(d -> {
            WebElement el = d.findElement(by);
            el.clear();
            el.sendKeys(text);
            return true;
        });
        return this;
    }

    public Bot click(By by) {
        wait.until(d -> {
            WebElement el = d.findElement(by);
            el.click();
            return true;
        });
        return this;
    }

    public boolean isDisplayed(By by) {
        return wait.until(d -> d.findElement(by).isDisplayed());
    }

    public String getText(By by) {
        return wait.until(d -> d.findElement(by).getText());
    }

    public String getAttribute(By by, String attribute) {
        return wait.until(d -> d.findElement(by).getAttribute(attribute));
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
