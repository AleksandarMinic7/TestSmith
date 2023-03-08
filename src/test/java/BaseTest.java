import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BaseTest {

    WebDriver driver;
    protected SoftAssert softAssert;
    WebDriverWait wait;

    @BeforeMethod (alwaysRun = true)
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup(); // do selenium 4.6.0 verzije


        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        //driver.manage().timeouts().implicitWait(Duration.ofSeconds(5));
        softAssert = new SoftAssert();
        Utils.waitForSeconds(3); //.sleep(3000);
        // driver.manage().window().maximize();
        //driver.get(("https://demowebshop.tricentis.com/"));
        driver.get(("https://practicesoftwaretesting.com/#/"));
    }



//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }

    protected WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        //  return driver.findElement(locator);
    }

    protected void typeIn(By locator, String text) {

        getElement(locator).sendKeys(text);
    }

    protected void clickOnElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    protected String getTextFromElement(By locator) {

        return getElement(locator).getText();
    }

    protected String getColorFromElement(By locator, String cssValue) {
        return getElement(locator).getCssValue(cssValue);
    }

    protected boolean isElementVisible (By locator){
        List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        //List list1 = List.of(null, 4, "Pera");
        if (!list.isEmpty() && (list.get(0)!=null)){
            return list.get(0).isDisplayed();
        }


        // WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return false;
    }

    protected void clickOnRandomElement(By locator) {
        Random random = new Random();
        List<WebElement> list = driver.findElements(locator);
        int randomElement = random.nextInt(list.size());
        list.get(randomElement).click();
    }


    public void hover(By locator){
        Actions actions = new Actions(driver);
        WebElement element = getElement(locator);
        actions.moveToElement(element)
                .build()
                .perform();

    }

    public void hoverAndClick(By locator){
        Actions actions = new Actions(driver);
        WebElement element = getElement(locator);
        actions.moveToElement(element)
                .click()
                .build()
                .perform();
    }
}
