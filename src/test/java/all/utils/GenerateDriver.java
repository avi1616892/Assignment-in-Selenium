package all.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GenerateDriver {

    private static final String DEFAULT_CONFIG_PATH = "./RunParams.json"; // Default path to JSON file

    /**
     * Initializes WebDriver using configuration from JSON file.
     *
     * @param configFilePath the path to the JSON configuration file
     * @return initialized WebDriver
     */
    public static WebDriver initDriverFromConfig(String configFilePath) {
        // Use JsonUtils to load browser type and URL from JSON
        String browserType = JsonUtils.readJsonFromFile("browser");  // Updated key from "browserType" to "browser"
        String url = JsonUtils.readJsonFromFile("url");

        if (browserType == null || url == null) {
            throw new IllegalArgumentException("Missing required parameters in JSON file: " + configFilePath);
        }

        // Initialize WebDriver based on the extracted browser type and URL
        return initDriver(browserType, url);
    }

    /**
     * Initializes WebDriver based on browser type and navigates to the given URL.
     *
     * @param browserType the browser type (chrome, firefox, edge)
     * @param url         the URL to navigate to
     * @return initialized WebDriver
     */
    public static WebDriver initDriver(String browserType, String url) {
        WebDriver driver = switch (browserType.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                yield new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                yield new FirefoxDriver(firefoxOptions);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--inprivate");
                yield new EdgeDriver(edgeOptions);
            }
            default -> throw new IllegalArgumentException("Browser \"" + browserType + "\" not supported.");
        };

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url); // Navigate to the URL from the JSON

        return driver;
    }

    /**
     * Cleans up the WebDriver instance.
     *
     * @param driver the WebDriver instance
     */
    public static void cleanDriver(WebDriver driver) {
        if (driver != null) {
            driver.close(); // Close the current window
            driver.quit();  // Quit the WebDriver to close the browser
        }
    }

}
