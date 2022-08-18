package org.example;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchFieldToTest {
    private WebDriver driver;
    private static final String NO_RESULTS_HEADER = "//div[@id='search']//div[@class='a-row']";
    private static final By SEARCH_FIELD = By.id("twotabsearchtextbox");
    private static final By SEARCH_BUTTON = By.id("nav-search-submit-button");
    private static final By SEARCH_RESULTS_HEADER = By.xpath("//*[@class='a-color-state a-text-bold']");
    private static final String INCORRECT_INFORMATION = "guihjkbgrsxcfhhbpppppp";
    private static final String NO_RESULTS_FOR = "No results for ";
    private static final String LAPTOP = "laptop";
    private static final String EXPECTED_HEADER = "\"" + LAPTOP + "\"";
    private static final String SEARCH_RESULT = "//*[contains(@class,\"a-size-medium a-color-base a-text-normal\")]";


    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
    }

    @Before
    public void setUpTest() {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");

    }

    @Test
    public void incorrectInput() {

        performSearchByText(INCORRECT_INFORMATION);
        WebElement resultsArea = driver.findElement(By.xpath(NO_RESULTS_HEADER));
        Assert.assertEquals(NO_RESULTS_FOR + INCORRECT_INFORMATION + ".", resultsArea.getText());
    }

    @Test
    public void searchResultIsPresent() {
        performSearchByText(LAPTOP);
        WebElement resultForLaptopSearch = driver.findElement(SEARCH_RESULTS_HEADER);
        Assert.assertEquals(EXPECTED_HEADER, resultForLaptopSearch.getText());
    }

    @Test
    public void foundElementsContainsWord() {
        performSearchByText(LAPTOP);
        List<WebElement> resultsForLaptop = driver.findElements(By.xpath(SEARCH_RESULT));
        Assert.assertTrue(NO_RESULTS_FOR + LAPTOP,
                resultsForLaptop.stream().anyMatch(webElement -> webElement.getText().toLowerCase().contains(LAPTOP)));
    }

    @After
    public void endTest() {
        driver.close();
        driver.quit();
    }

    private void performSearchByText(String laptop) {
        WebElement searchField = driver.findElement(SEARCH_FIELD);
        searchField.sendKeys(laptop);
        WebElement searchButton = driver.findElement(SEARCH_BUTTON);
        searchButton.click();
    }
}
