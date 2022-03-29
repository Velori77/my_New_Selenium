package myDemoqaComTest;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.JsExecutor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static utils.Drivers.getChromeDriver;
import static utils.Drivers.quiteWebDriver;

public class FirstTestClass {

    private WebDriver driver;
    private JsExecutor jsExecutor;
    private WebDriverWait waiter;

    @BeforeMethod
    public void openPage() {
        driver = getChromeDriver();
        jsExecutor = new JsExecutor(driver);
        waiter = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://demoqa.com/");
    }

    @Test(priority = 1)
    public void checkConformityInputsDataOnThePageTest() {
        String expectedResultName = "Vasilii Brovkin";
        String expectedResultEmail = "vb@ua.com";
        String expectedResultCurrentAddress = "Vinn";
        String expectedResultPermanentAddress = "Ukr";

        WebElement elementElements = driver.findElement(By.xpath("//*[text()='Elements']/.."));
        jsExecutor.scrollIntoView(elementElements);
        elementElements.click();
        driver.findElement(By.xpath("//span[text()='Text Box']")).click();
        driver.findElement(By.id("userName")).sendKeys(expectedResultName);
        driver.findElement(By.id("userEmail")).sendKeys(expectedResultEmail);
        driver.findElement(By.id("currentAddress")).sendKeys(expectedResultCurrentAddress);
        driver.findElement(By.id("permanentAddress")).sendKeys(expectedResultPermanentAddress);
        WebElement submitButton = driver.findElement(By.id("submit"));
        jsExecutor.scrollIntoView(submitButton);

        submitButton.click();

        WebElement Name = driver.findElement(By.id("name"));
        String actualResultName = Name.getText();

        Assert.assertEquals(actualResultName, "Name:" + expectedResultName);


        WebElement Email = driver.findElement(By.id("email"));
        String actualResultEmail = Email.getText();
        Assert.assertEquals(actualResultEmail, "Email:" + expectedResultEmail);

        WebElement CurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        String actualResultCurrentAddress = CurrentAddress.getText();
        Assert.assertEquals(actualResultCurrentAddress, "Current Address :" + expectedResultCurrentAddress);

        WebElement PermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']"));
        String actualResultPermanentAddress = PermanentAddress.getText();
        Assert.assertEquals(actualResultPermanentAddress, "Permananet Address :" + expectedResultPermanentAddress);


    }

    @Test(priority = 2)
    public void checkSelectionWordFileOnTheCheckBoxPageTest() {
        String expectedResultSelected = "You have selected :";
        String expectedResultWordElement = "wordFile";

        WebElement elementElements = driver.findElement(By.xpath("//*[text()='Elements']/.."));
        jsExecutor.scrollIntoView(elementElements);
        elementElements.click();

        driver.findElement(By.xpath("//*[text()='Elements']/..")).click();
        driver.findElement(By.xpath("//span[text()='Check Box']")).click();
        driver.findElement(By.xpath("//*[text()='Home']/../../button")).click();
        driver.findElement(By.xpath("//*[text()='Downloads']/../../button")).click();
        driver.findElement(By.xpath("//span[text()='Word File.doc']")).click();

        WebElement Selected = driver.findElement(By.xpath("//span[text()='You have selected :']"));
        String actualResultSelected = Selected.getText();
        Assertions.assertThat(actualResultSelected).as("Expected text " + expectedResultSelected + " not exist on the Page")
                .isEqualTo(expectedResultSelected);

        WebElement WordElement = driver.findElement(By.xpath("//span[@class='text-success']"));
        String actualResultWordElement = WordElement.getText();
        Assertions.assertThat(actualResultWordElement).as("Expected text " + expectedResultWordElement + " not exist on the Page")
                .isEqualTo(expectedResultWordElement);

    }

    @Test(priority = 3)
    public void checkAppearsTextInVariableDynamicClickTest() {
        String expectedResultClickMe = "You have done a dynamic click";
        String expectedResultRightClickMe = "You have done a right click";
        String expectedResultDoubleClick = "You have done a double click";

        WebElement elementElements = driver.findElement(By.xpath("//*[text()='Elements']/.."));
        jsExecutor.scrollIntoView(elementElements);
        elementElements.click();

        WebElement elementButtons = driver.findElement(By.id("item-4"));
        jsExecutor.scrollIntoView(elementButtons);
        elementButtons.click();

        WebElement elementClickMe = driver.findElement(By.xpath("//button[text()='Click Me']"));
        jsExecutor.scrollIntoView(elementClickMe);
        elementClickMe.click();

        Actions actions = new Actions(driver);
        WebElement elementRightClick = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(elementRightClick).build().perform();

        WebElement elementDoubleClick = driver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(elementDoubleClick).build().perform();

        WebElement ClickMe = driver.findElement(By.id("dynamicClickMessage"));
        String actualResultClickMe = ClickMe.getText();
        Assertions.assertThat(actualResultClickMe).as("Expected text " + expectedResultClickMe + " not exist on the Page")
                .isEqualTo(expectedResultClickMe);

        WebElement rightClickMe = driver.findElement(By.id("rightClickMessage"));
        String actualResultRightClickMe = rightClickMe.getText();
        Assertions.assertThat(actualResultRightClickMe).as("Expected text " + expectedResultRightClickMe + " not exist on the Page")
                .isEqualTo(expectedResultRightClickMe);

        WebElement doubleClick = driver.findElement(By.id("doubleClickMessage"));
        String actualResultDoubleClick = doubleClick.getText();
        Assertions.assertThat(actualResultDoubleClick).as("Expected text " + expectedResultDoubleClick + " not exist on the Page")
                .isEqualTo(expectedResultDoubleClick);

    }

    @Test(priority = 4)
    public void checkOpenedFrameWithTextTest() {
        String expectedResultInNewWindow = "This is a sample page";
        WebElement webElement = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));

        jsExecutor.scrollIntoView(webElement);
        webElement.click();
        driver.findElement(By.xpath("//span[text()='Browser Windows']")).click();
        driver.findElement(By.id("tabButton")).click();
        List<String> windows = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        String actualResultInNewWindow = driver.findElement(By.id("sampleHeading")).getText();

        Assertions.assertThat(expectedResultInNewWindow).as("Expected text " + expectedResultInNewWindow + " not exist on the Page")
                .isEqualTo(actualResultInNewWindow);
    }

    @Test(priority = 5)
    public void checkAlertDisappearTest() {
        String expectedResultOpenedText = "You clicked a button";
        WebElement webElement = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));

        jsExecutor.scrollIntoView(webElement);
        webElement.click();

        //Click Alerts
        driver.findElement(By.xpath("//span[text()='Alerts']")).click();
        //Click button Click Me

        By by = By.id("alertButton");
        waiter.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).click();
        waiter.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String actualResultOpenedText = alert.getText();

        Assertions.assertThat(expectedResultOpenedText).as("Expected text " + expectedResultOpenedText + " not exist on the Page")
                .isEqualTo(actualResultOpenedText);

    }

    @Test(priority = 6)
    public void checkAlertAppearanceAfterWaitingTest() {
        String expectedResultOpenedText = "This alert appeared after 5 seconds";
        WebElement webElement = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));

        jsExecutor.scrollIntoView(webElement);
        webElement.click();

        //Click Alerts
        By byAlerts = By.xpath("//span[text()='Alerts']");
        waiter.until(ExpectedConditions.presenceOfElementLocated(byAlerts));
        driver.findElement(byAlerts).click();

        //Click button Click Me
        By by = By.id("timerAlertButton");
        waiter.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).click();
        waiter.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String actualResultOpenedText = alert.getText();

        Assertions.assertThat(expectedResultOpenedText).as("Expected text " + expectedResultOpenedText + " not exist on the Page")
                .isEqualTo(actualResultOpenedText);
    }

    @Test(priority = 7)
    public void checkingExistingInBigSquareTest() {
        String expectedResultOpenedText = "This is a sample page";

        WebElement webElement = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));

        jsExecutor.scrollIntoView(webElement);
        webElement.click();

        //Click Frames
        By byFrames = By.xpath("//span[text()='Frames']");
        waiter.until(ExpectedConditions.presenceOfElementLocated(byFrames));
        driver.findElement(byFrames).click();

        //Switch to iFrame
        WebElement iFrame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iFrame);
        String actualResultOpenedText = driver.findElement(By.id("sampleHeading")).getText();

        Assertions.assertThat(actualResultOpenedText).isEqualTo(expectedResultOpenedText);

    }

    @Test(priority = 8)
    public void checkAppearanceAndDisappearanceModalTest() {
        String expectedResultTitleSmallModal = "Small Modal";
        String expectedResultTextSmallModal = "This is a small modal. It has very less content";

        WebElement webElement = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));

        jsExecutor.scrollIntoView(webElement);
        webElement.click();

        //Click Modal Dialogs
        By byModalDialogs = By.xpath("//span[text()='Modal Dialogs']");
        waiter.until(ExpectedConditions.presenceOfElementLocated(byModalDialogs)).click();

        driver.findElement(By.id("showSmallModal")).click();

        String actualSmallModal = driver.findElement(By.id("example-modal-sizes-title-sm")).getText();
        Assertions.assertThat(actualSmallModal).isEqualTo(expectedResultTitleSmallModal);

        String actualBodyText = driver.findElement(By.className("modal-body")).getText();
        Assertions.assertThat(actualBodyText).isEqualTo(expectedResultTextSmallModal);

        //Click Close
        driver.findElement(By.id("closeSmallModal")).click();

        By modalWindow = By.xpath("//div[@role='dialog']");
        waiter.until(ExpectedConditions.numberOfElementsToBe(modalWindow, 0));
        Assertions.assertThat(driver.findElements(modalWindow).size()).isEqualTo(0);

    }

    @Test(priority = 9)
    public void checkFillingAndDevastationProgressBarTest() {
        String expectedResultHighProgressBar = "100%";
        String expectedResultLowProgressBar = "0%";

        //Scroll and Click Widgets
        WebElement elementWidgets = driver.findElement(By.xpath("//h5[text()='Widgets']"));
        jsExecutor.scrollIntoView(elementWidgets);
        elementWidgets.click();
        //Scroll and Click Progress Bar
        WebElement elementProgressBar = driver.findElement(By.xpath("//span[text()='Progress Bar']"));
        jsExecutor.scrollIntoView(elementProgressBar);
        elementProgressBar.click();
        //Click Start
        driver.findElement(By.id("startStopButton")).click();
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("resetButton")));

        By byXpath = By.xpath("//div[@role='progressbar']");
        String progressHighBarText = driver.findElement(byXpath).getText();
        Assertions.assertThat(progressHighBarText).isEqualTo(expectedResultHighProgressBar);
        //Click Reset
        driver.findElement(By.id("resetButton")).click();
        String progressLowBarText = driver.findElement(byXpath).getText();
        Assertions.assertThat(progressLowBarText).isEqualTo(expectedResultLowProgressBar);

    }

    @Test(priority = 10)
    public void checkActivenessAndInactivenessTabsTest() {
        List<WebElement> activeListElements = new ArrayList<>();
        List<WebElement> inactiveListElements = new ArrayList<>();

        //Scroll and Click Widgets
        WebElement elementWidgets = driver.findElement(By.xpath("//h5[text()='Widgets']"));
        jsExecutor.scrollIntoView(elementWidgets);
        elementWidgets.click();
        //Scroll and Click Tabs
        WebElement elementTabs = driver.findElement(By.xpath("//span[text()='Tabs']"));
        jsExecutor.scrollIntoView(elementTabs);
        elementTabs.click();
        // need to resolve
        /*List<WebElement> allTabs = driver.findElements(By.cssSelector("[role='tab']"));
        for (WebElement webElement : allTabs) {
            if
        }*/


    }


    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        quiteWebDriver();
    }
}

