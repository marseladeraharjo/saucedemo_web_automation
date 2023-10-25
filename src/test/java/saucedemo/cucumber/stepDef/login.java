package saucedemo.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user on login page")
    public void user_on_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);

        // verify that current page is a login page
        Boolean loginButtonIsDiplayed = driver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertEquals(true, loginButtonIsDiplayed);
    }

    @When("user input valid username")
    public void user_input_valid_username() {
        // input using valid username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("user input valid password")
    public void user_input_valid_password() {
        // input using valid password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("user click login button")
    public void user_click_login_button() {
        // click the Login button
        driver.findElement(By.id("login-button")).click();
    }

    @Then("user move to products page")
    public void user_move_to_products_page() {
        // verify that current page is a products page
        String titleText = driver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals("Products", titleText);
        driver.close();
    }

    @When("user input invalid username")
    public void userInputInvalidUsername() {
        // input using invalid username
        driver.findElement(By.id("user-name")).sendKeys("anonymous_user");
    }

    @And("user input invalid password")
    public void userInputInvalidPassword() {
        // input using invalid password
        driver.findElement(By.id("password")).sendKeys("12345");
    }

    @Then("user get error message")
    public void userGetErrorMessage() {
        // verify that error message is displayed
        Boolean errorMessageIsDiplayed = driver.findElement(By.className("error-button")).isDisplayed();
        Assert.assertEquals( true, errorMessageIsDiplayed);
        driver.close();
    }
}
