package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GenerateDriver {

    public static WebDriver initDriver(String browserType, String url) {
        WebDriver driver = switch (browserType.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Browser \"" + browserType + "\" not supported.");
        };

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);

        return driver;
    }


    public static void cleanDriver(WebDriver driver){
        driver.quit();
    }

}
