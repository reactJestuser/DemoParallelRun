package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Base {
    public static WebDriver driver = null;
    public static Properties prop = null;
    String path = "/src/test/java/com/util/base.properties";

    /* load properties file and start browser */

    public Base() throws IllegalArgumentException, IOException {
        try {
            prop = new Properties();
            loadPropertyFile(path);
            setupChromeDriver();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void setupChromeDriver() {
        if (driver == null) {

            driver = new ChromeDriver();
            /*
             * SessionId session = ((ChromeDriver) driver).getSessionId();
             * System.out.println("Session id: " + session.toString());
             */
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        }
    }

    // @Parameters({ "url", "username", "password" }) run time arguments
    @BeforeSuite
    public void init() throws IOException {

        try {

            driver.navigate()
                    .to("https://accounts.zoho.com/login?servicename=ZohoOne&newtheme=true&signupurl=https://www.zoho.com/one/signup.html&serviceurl=https%3A%2F%2Fone.zoho.com");
            driver.findElement(By.id("lid")).sendKeys("test_run@gmail.com");
            driver.findElement(By.id("pwd")).sendKeys("welcome1");
            driver.findElement(By.id("signin_submit")).click();

            // store the current session
            Set<Cookie> cookiesInstance1 = driver.manage().getCookies();
            System.out.println("Coockies = " + cookiesInstance1);
        } catch (WebDriverException e) {
            throw e;
        }
    }

    /*
     * @AfterTest public void close_browser() { driver.quit(); }
     */

    public void loadPropertyFile(String text) throws IOException {
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + text);
        prop.load(fis);

        // override loaded properties with system ones (those on the command
        // line set with -D for example)
        // this allows us to set any option (like ipd.test.browser or
        // Landing_url) at runtime
        prop.putAll(System.getProperties());
    }
}
