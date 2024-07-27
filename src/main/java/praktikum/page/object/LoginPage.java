package praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.User;

public class LoginPage {
    private final WebDriver driver;
    private final By entranceHeader = By.xpath(".//h2[text()='Вход']");
    private final By email = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='text']");
    private final By password = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='password']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By changePasswordButton = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Вход")
    public void login(User user) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(password).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку регистрации")
    public void clickRegisterButton() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Нажатие на кнопку восстановления пароля")
    public void clickResetPassButton() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(changePasswordButton));
        driver.findElement(changePasswordButton).click();
    }

    @Step("Отображается ли страница вход")
    public void isHeaderEntranceDisplayed() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(entranceHeader));
        driver.findElement(entranceHeader).isDisplayed();
    }
}
