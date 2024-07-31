package praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {
    private final WebDriver driver;
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By profileHeader = By.xpath(".//a[text()='Профиль']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку выхода")
    public void clickExitButton() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(profileHeader));
        driver.findElement(exitButton).click();
    }

    @Step("Нажатие на кнопку конструктора")
    public void clickConstructorButton() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(profileHeader));
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на лого")
    public void clickLogo() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(profileHeader));
        driver.findElement(logo).click();
    }

    @Step("Проверка наличия раздела Профиль")
    public void isProfileDisplayed() {
        driver.findElement(profileHeader).isDisplayed();
    }
}
