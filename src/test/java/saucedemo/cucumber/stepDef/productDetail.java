package saucedemo.cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class productDetail {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user on product page")
    public void user_on_product_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);

        // verify that current page is a login page
        Boolean loginButtonIsDiplayed = driver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals(true, loginButtonIsDiplayed);

        // input using valid username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        // input using valid password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        // click the Login button
        driver.findElement(By.id("login-button")).click();

        // verify that current page is a products page
        String titleText = driver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products", titleText);
    }

    @When("user click product name")
    public void user_click_product_name() {
        driver.findElements(By.className("inventory_item_name")).get(0).click();
    }

    @Then("user can view product detail")
    public void user_can_view_product_detail() {
        Boolean productDetailIsDisplayed = driver.findElement(By.className("inventory_details")).isDisplayed();
        Assert.assertEquals(true, productDetailIsDisplayed);
        driver.close();
    }
}
