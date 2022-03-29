package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.util.Objects.isNull;

public class Drivers {
    private static WebDriver driver;

    public static WebDriver getChromeDriver() {
        if (isNull(driver)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quiteWebDriver() {
        if (!isNull(driver)) {
            driver.quit();
            driver = null;
        }
    }
}
