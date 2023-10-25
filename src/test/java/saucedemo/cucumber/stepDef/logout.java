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

public class logout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user login to saucedemo website")
    public void user_login_to_saucedemo_website() {
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

    @When("user navigate left sidebar")
    public void user_navigate_left_sidebar() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    @And("user click logout")
    public void user_click_logout() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @Then("user move to login page")
    public void user_move_to_login_page() {
        Assert.assertEquals("Swag Labs",driver.findElement(By.className("login_logo")).getText());
        System.out.println("Scenario : Logout from saucedemo website");
        System.out.println("You successfully log out from saucedemo website");
        driver.close();
    }
}
