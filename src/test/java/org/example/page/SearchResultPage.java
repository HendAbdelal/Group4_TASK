package org.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//div[@id='search']//div[@class='a-row']")
    private WebElement NO_RESULTS_HEADER;

    @FindBy(xpath = "//*[@class='a-color-state a-text-bold']")
    private WebElement SEARCH_RESULTS_HEADER;

    @FindBy(xpath = "//*[contains(@class,'a-size-medium a-color-base a-text-normal')]")
    private List<WebElement> SEARCH_RESULTS;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getIncorrectSearchResultText() {
        return NO_RESULTS_HEADER.getText();
    }

    public String getSearchResultHeaderText() {
        return SEARCH_RESULTS_HEADER.getText();
    }

    public boolean isAnyItemInSearchResultContainsWord(String item) {
        return SEARCH_RESULTS.stream()
                .anyMatch(webElement -> webElement.getText().toLowerCase().contains(item));
    }
}
