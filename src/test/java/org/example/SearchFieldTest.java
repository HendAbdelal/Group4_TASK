package org.example;

import org.example.page.MainPage;
import org.example.page.SearchResultPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SearchFieldTest {
    private WebDriver driver;

    private static final String INCORRECT_INFORMATION = "guihjkbgrsxcfhhbpppppp";
    private static final String NO_RESULTS_FOR = "No results for ";
    private static final String LAPTOP = "laptop";
    private static final String EXPECTED_HEADER = "\"" + LAPTOP + "\"";


    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdriver/chromedriver.exe");
    }

    @Before
    public void setUpTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void incorrectInput() {
        MainPage mainPage = new MainPage(driver);
        String incorrectSearchValue = mainPage.openPage().searchItem(INCORRECT_INFORMATION).getIncorrectSearchResultText();
        Assert.assertEquals(NO_RESULTS_FOR + INCORRECT_INFORMATION + ".", incorrectSearchValue);
    }

    @Test
    public void searchResultIsPresent() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage().searchItem(LAPTOP);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        Assert.assertEquals(EXPECTED_HEADER, searchResultPage.getSearchResultHeaderText());
    }

    @Test
    public void foundElementsContainsWord() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage().searchItem(LAPTOP);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        Assert.assertTrue("Error: on search results"
                , searchResultPage.isAnyItemInSearchResultContainsWord(LAPTOP));
    }

    @After
    public void endTest() {
        driver.close();
        driver.quit();
    }
}
