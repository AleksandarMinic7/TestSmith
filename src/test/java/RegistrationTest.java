import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest{



    By signInButton = By.cssSelector("a[href*='#/aut']");
    By accRegButton = By.cssSelector("a[href*='#/auth/reg']");
    By firstNameField = By.xpath("//input[@id='first_name']");
    By lastNameField = By.xpath("//input[@id='last_name']");;
    By dateOfBirthField = By.xpath("//input[@id='dob']");
    By addressField = By.xpath("//input[@id='address']");
    By postcodeField = By.xpath("//input[@id='postcode']");
    By cityField = By.xpath("//input[@id='city']");
    By stateField = By.xpath("//input[@id='state']");
    By countryField = By.xpath("//select[@id='country']");
    By phoneField = By.xpath("//input[@id='phone']");
    By emailField = By.xpath("//input[@id='email']");
    By passwordField = By.xpath("//input[@id='password']");
    By registerButton = By.xpath("//button[@class='btnSubmit mb-3']");
    Select se;
    By logInEmailField = By.xpath("//input[@id='email']");
    By logInPasswordField = By.xpath("//input[@data-test='password']");
    By confirmationText1 = By.xpath("//app-overview/h1");
    By confirmationText2 = By.xpath("//app-overview/p");
    By loggedInUserNameField = By.cssSelector("a[id*='user']");
    By signOutButton = By.cssSelector("a[data-test*='nav-sign-out']");
    By logInSubmit = By.xpath("//input[@data-test='login-submit']");



    @Test
    public void registerNewUser() throws InterruptedException {

        clickOnElement(signInButton);
        Thread.sleep(3000);

        clickOnElement(accRegButton);
        Thread.sleep(3000);

        typeIn(firstNameField, "Jovan");
        typeIn(lastNameField, "Ilic");
//        clickOnElement(dateOfBirthField);
        typeIn(dateOfBirthField, "11291992");
        typeIn(addressField, "Stanoja Glavasa, 24");
        typeIn(postcodeField, "11000");
        typeIn(cityField, "Belgrade");
        typeIn(stateField, "Central Serbia");

        se = new Select(driver.findElement(By.xpath("//select[@id='country']")));
        se.selectByValue("RS");

        typeIn(phoneField, "381123123123123");
        typeIn(emailField, "JovanIlic@email.com");
        typeIn(passwordField, "password123!");

        clickOnElement(registerButton);

        Thread.sleep(3000);

        typeIn(logInEmailField, "JovanIlic@email.com");
        typeIn(logInPasswordField, "password123!");
        clickOnElement(logInSubmit);

        String expectedText1 = "My account";
        String expectedText2 = "Here you can manage your profile, favorites and orders.";

        String actualText1 = getTextFromElement(confirmationText1);
        String actualtext2 = getTextFromElement(confirmationText2);
//        isElementVisible(loggedInUserNameField);
//        isElementVisible(signOutButton);

        Assert.assertEquals(actualText1, expectedText1, "Tekst 1 nije isti!");
        Assert.assertEquals(actualtext2, expectedText2, "Tekst 2 nije isti!");

        Assert.assertTrue(isElementVisible(loggedInUserNameField));

        clickOnElement(loggedInUserNameField);
        Assert.assertTrue(isElementVisible(signOutButton));




    }


    @Test
    public void logInTest() throws InterruptedException{

        clickOnElement(signInButton);
        Thread.sleep(3000);

        typeIn(logInEmailField, "JovanIlic@email.com");
        typeIn(logInPasswordField, "password123!");
        clickOnElement(logInSubmit);

        String expectedText1 = "My account";
        String expectedText2 = "Here you can manage your profile, favorites and orders.";

        String actualText1 = getTextFromElement(confirmationText1);
        String actualtext2 = getTextFromElement(confirmationText2);
//        isElementVisible(loggedInUserNameField);
//        isElementVisible(signOutButton);

        Assert.assertEquals(actualText1, expectedText1, "Tekst 1 nije isti!");
        Assert.assertEquals(actualtext2, expectedText2, "Tekst 2 nije isti!");

        Assert.assertFalse(isElementVisible(loggedInUserNameField), "Ime usera je prisutno!");

        clickOnElement(loggedInUserNameField);
        Assert.assertTrue(isElementVisible(signOutButton), "Log out dugme nije pronadjeno!");
    }














































}
