package praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.User;

public class ChangePasswordPage {
    private final WebDriver driver;
    private final By email = By.xpath(".//label[text()='Email']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    private final By resetPasswordHeader = By.xpath(".//h2[text()='Восстановление пароля']");
    private final By resetButton = By.xpath(".//button[text()='Восстановить']");

    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Смена пароля")
    public void resetPassword(User user) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(resetPasswordHeader));
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(resetButton).click();
    }

    @Step("Вход после смены пароля")
    public void clickLoginButtonAfterReseting() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }
}
