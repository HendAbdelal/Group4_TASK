package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DeliverToTest {

    @Test
    public void deliverFromAmazon() throws InterruptedException {

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\hends\\IdeaProjects\\Group4\\src\\test\\resources\\webdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.amazon.com/");

        driver.findElement((By.id("glow-ingress-line2"))).click();
        driver.findElement(By.id("GLUXZipUpdateInput")).sendKeys("99801" + Keys.ENTER);
        driver.findElement((By.cssSelector(".a-popover-footer #GLUXConfirmClose"))).click();

        Thread.sleep(1000);

        Assert.assertTrue(driver.findElement((By.id("glow-ingress-line2"))).getText().contains("Juneau 99801"));

        driver.close();
    }

    @Test
    public void deliverFromCountries() throws InterruptedException{

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\hends\\IdeaProjects\\Group4\\src\\test\\resources\\webdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.amazon.com/");

        driver.findElement((By.id("glow-ingress-line2"))).click();
        driver.findElement((By.id("GLUXCountryValue"))).click();
        WebElement e = driver.findElement(By.id("GLUXCountryList"));
        Thread.sleep(1000);
        Select s = new Select(e);
        s.selectByVisibleText("Poland");
        String st = s.getFirstSelectedOption().getText();
        Assert.assertEquals(st,"Poland");
        driver.close();



    }
    @Test
    public void verifyShipping() throws InterruptedException {

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\hends\\IdeaProjects\\Group4\\src\\test\\resources\\webdriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.amazon.com/");
        driver.findElement((By.id("glow-ingress-line2"))).click();
        driver.findElement((By.id("GLUXCountryValue"))).click();
        WebElement e = driver.findElement(By.id("GLUXCountryList"));
        Select s = new Select(e);
        s.selectByVisibleText("Spain");
        String st = s.getFirstSelectedOption().getText();
        Assert.assertEquals(st,"Spain");
        driver.findElement(By.name("glowDoneButton")).click();

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#desktop-grid-2 a[aria-label=\"Video Games\"]")).click();
        driver.findElements(By.cssSelector("[class*=\"s-product-image-container\"] a")).get(0).click();
        String stt=driver.findElement(By.id("contextualIngressPtLabel")).getText();
        Assert.assertEquals(stt,"Deliver to Spain");
        driver.close();
    }


}

