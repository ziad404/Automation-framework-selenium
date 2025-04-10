package pagesPackage;

import org.example.engine.Bot;
import org.openqa.selenium.By;
import java.util.List;
import java.util.stream.Collectors;

public class DuckDuckGoHomePage {
    private final Bot bot;

    private final By searchField = By.id("searchbox_input");
    private final By searchButton = By.className("searchbox_iconWrapper__qAk7y");
    private final By logo = By.xpath("//section[contains(@class,'header_headerLeft')]/a/img");
    private final By resultLinks = By.cssSelector("a[data-testid='result-title-a']");

    public DuckDuckGoHomePage(Bot bot) {
        this.bot = bot;
    }

    public String getTitle() {
        return bot.getDriver().getTitle();
    }

    public boolean isLogoDisplayed() {
        return bot.isDisplayed(logo);
    }

    public void enterSearch(String text) {
        bot.sendKeys(searchField, text).click(searchButton);
    }

    public String getFirstResultLink() {
        return bot.getAttribute(resultLinks, "href");
    }

    public String getFirstResultText() {
        return bot.getText(resultLinks);
    }

    public String getSecondLinkText() {
        List<String> allLinks = bot.getDriver().findElements(resultLinks)
                .stream().map(e -> e.getAttribute("href")).collect(Collectors.toList());
        return allLinks.size() > 1 ? allLinks.get(1) : null;
    }

    public String getFourthLinkText() {
        List<String> allTexts = bot.getDriver().findElements(resultLinks)
                .stream().map(e -> e.getText()).collect(Collectors.toList());
        return allTexts.size() > 3 ? allTexts.get(3) : null;
    }
}
