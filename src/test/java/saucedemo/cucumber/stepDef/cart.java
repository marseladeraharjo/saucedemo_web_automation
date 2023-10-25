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

public class cart {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user already on product page")
    public void user_already_on_product_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);

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

    @When("user click add to cart")
    public void user_click_add_to_cart() {
        driver.findElements(By.className("btn_inventory")).get(0).click();
    }

    @Then("the shopping cart badge display the number of products added")
    public void the_shopping_cart_badge_display_the_number_of_products_added() {
        Boolean shoppingCartBadgeIsDisplayed = driver.findElement(By.className("shopping_cart_badge")).isDisplayed();
        Assert.assertEquals(true, shoppingCartBadgeIsDisplayed);
        System.out.println("Added product : " + driver.findElement(By.className("shopping_cart_badge")).getText());
        driver.close();
    }
}
