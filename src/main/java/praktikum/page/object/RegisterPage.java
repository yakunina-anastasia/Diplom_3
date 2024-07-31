package praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import praktikum.User;

public class RegisterPage {
    private final WebDriver driver;
    private final By name = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[1]");
    private final By email = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[2]");
    private final By password = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[3]");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    private final By wrongPasswordText = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Регистрация")
    public void register(User user) {
        driver.findElement(name).sendKeys(user.getName());
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(password).sendKeys(user.getPassword());
        driver.findElement(registerButton).click();
    }

    @Step("Нажатие на вход в аккаунт")
    public void clickLoginButtonSignUpPage() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Попытка регистрации с неправильным паролем")
    public void registerWithWrongPassword(User user) {
        driver.findElement(name).sendKeys(user.getName());
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(password).sendKeys("12345");
        driver.findElement(registerButton).click();
        driver.findElement(wrongPasswordText).isDisplayed();
    }

    @Step("Получение текста ошибки")
    public String getWrongPasswordText() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(wrongPasswordText));
        return driver.findElement(wrongPasswordText).getText();
    }
}
