package org.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    private final static String URL = "https://www.amazon.com/";

    @FindBy(id = "twotabsearchtextbox")
    private WebElement SEARCH_FIELD;

    @FindBy(id = "nav-search-submit-button")
    private WebElement SEARCH_BUTTON;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage openPage() {
        driver.get(URL);
        return this;
    }

    public SearchResultPage searchItem(String item) {
        SEARCH_FIELD.sendKeys(item);
        SEARCH_BUTTON.click();
        return new SearchResultPage(driver);
    }
}
