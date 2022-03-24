package myDemoqaComTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import javax.naming.Name;
import java.time.Duration;

public class FirstTestClass {

    private WebDriver driver;

    @BeforeMethod
    public void setUpBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
        WebElement elementElement = driver.findElement(By.xpath("//*[text()='Elements']/.."));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementElement);
        elementElement.click();
        newWait();
    }

    public void newWait() {
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {

            e.printStackTrace();
        }
    }


    @Test(priority = 1)
    public void checkConformityInputsDataOnThePage() {
        String expectedResultName = "Vasilii Brovkin";
        String expectedResultEmail = "vb@ua.com";
        String expectedResultCurrentAddress = "Vinn";
        String expectedResultPermanentAddress = "Ukr";

        driver.findElement(By.xpath("//span[text()='Text Box']")).click();
        newWait();
        driver.findElement(By.id("userName")).sendKeys(expectedResultName);
        driver.findElement(By.id("userEmail")).sendKeys(expectedResultEmail);
        driver.findElement(By.id("currentAddress")).sendKeys(expectedResultCurrentAddress);
        driver.findElement(By.id("permanentAddress")).sendKeys(expectedResultPermanentAddress);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(By.id("submit")).click();
        newWait();

        WebElement Name = driver.findElement(By.id("name"));
        String actualResultName = Name.getText();

        Assert.assertEquals(actualResultName,"Name:"+ expectedResultName);



        WebElement Email = driver.findElement(By.id("email"));
        String actualResultEmail = Email.getText();
        Assert.assertEquals(actualResultEmail,"Email:"+ expectedResultEmail);

        WebElement CurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        String actualResultCurrentAddress = CurrentAddress.getText();
        Assert.assertEquals(actualResultCurrentAddress,"Current Address :"+ expectedResultCurrentAddress);

        WebElement PermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']"));
        String actualResultPermanentAddress = PermanentAddress.getText();                                             
        Assert.assertEquals(actualResultPermanentAddress,"Permananet Address :"+ expectedResultPermanentAddress);


    }

    @Test(priority = 2)
    public void checkSelectionWordFileOnTheCheckBoxPage() {
        String expectedResultSelected = "You have selected :";
        String expectedResultWordElement = "wordFile";

        driver.findElement(By.xpath("//*[text()='Elements']/..")).click();
        newWait();
        driver.findElement(By.xpath("//span[text()='Check Box']")).click();
        newWait();
        driver.findElement(By.xpath("//*[text()='Home']/../../button")).click();
        newWait();
        driver.findElement(By.xpath("//*[text()='Downloads']/../../button")).click();
        newWait();
        driver.findElement(By.xpath("//span[text()='Word File.doc']")).click();
        newWait();

        WebElement Selected = driver.findElement(By.xpath("//span[text()='You have selected :']"));
        String actualResultSelected = Selected.getText();
        Assertions.assertThat(actualResultSelected).as("Expected text "+expectedResultSelected+" not exist on the Page")
                .isEqualTo(expectedResultSelected);

        WebElement WordElement = driver.findElement(By.xpath("//span[@class='text-success']"));
        String actualResultWordElement = WordElement.getText();
        Assertions.assertThat(actualResultWordElement).as("Expected text "+expectedResultWordElement+" not exist on the Page")
                .isEqualTo(expectedResultWordElement);



    }
    @Test(priority = 3)
    public void checkExistDynamicClick() {
        String expectedResultClickMe = "You have done a dynamic click";
        String expectedResultRightClickMe = "You have done a right click";
        String expectedResultDoubleClick = "You have done a double click";

        driver.findElement(By.xpath("//*[text()='Elements']/..")).click();
        newWait();

        WebElement elementButtons = driver.findElement(By.id("item-4"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementButtons);
        elementButtons.click();
        newWait();

        WebElement elementClickMe = driver.findElement(By.xpath("//button[@id='83PSV']/.."));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementClickMe);
        elementClickMe.click();
        newWait();
        // I think mistakes are here!

        Actions actions = new Actions(driver);
        WebElement elementRightClick = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(elementRightClick).perform();
        newWait();
        WebElement elementDoubleClick = driver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(elementDoubleClick).perform();;
        newWait();


        WebElement ClickMe = driver.findElement(By.id("dynamicClickMessage"));
        String actualResultClickMe = ClickMe.getText();
        Assertions.assertThat(actualResultClickMe).as("Expected text "+expectedResultClickMe+" not exist on the Page")
                .isEqualTo(expectedResultClickMe);

        WebElement RightClickMe = driver.findElement(By.id("rightClickMessage"));
        String actualResultRightClickMe = RightClickMe.getText();
        Assertions.assertThat(actualResultRightClickMe).as("Expected text "+expectedResultRightClickMe+" not exist on the Page")
                .isEqualTo(expectedResultRightClickMe);

        WebElement DoubleClick = driver.findElement(By.id("doubleClickMessage"));
        String actualResultDoubleClick = DoubleClick.getText();
        Assertions.assertThat(actualResultDoubleClick).as("Expected text "+expectedResultDoubleClick+" not exist on the Page")
                .isEqualTo(expectedResultDoubleClick);


    }
    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}

