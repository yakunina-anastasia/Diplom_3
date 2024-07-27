package praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static praktikum.SiteURL.*;

public class HomePage {
    private final WebDriver driver;
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By accountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By makeBurger = By.xpath(".//h1[text()='Соберите бургер']");
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    private final By bunButton = By.xpath(".//span[text() = 'Булки']");
    public final By bunButtonWorking = By.xpath(".//span[text()='Булки']/parent::div");

    private final By sauceButton = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By saucesButtonWorking = By.xpath(".//div[(contains(@class, 'tab_tab_type_current__2BEPc')) and (contains(span/text(),'Соусы'))]");
    private final By fillingButton = By.xpath(".//span[text()='Начинки']/parent::div");
    public final By fillingsButtonWorking = By.xpath(".//div[(contains(@class, 'tab_tab_type_current__2BEPc')) and (contains(span/text(),'Начинки'))]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Домашняя страница")
    public void openHomePage() {
        driver.get(HOME_PAGE);
    }

    @Step("Нажатие на кнопку входа в акк")
    public void clickLoginButton() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку личного кабинета")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(accountButton));
    }

    @Step("Нажатие на булки")
    public void clickBunButton() {
        driver.findElement(bunButton).click();;

    }

    @Step("Нажатие на соусы")
    public void clickSauceButton() {;
        driver.findElement(sauceButton).click();
    }

    @Step("Нажатие на начинки")
    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    @Step("Проверка текста в поле создания заказа")
    public String getMakeOrderButtonText() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(createOrderButton));
        return driver.findElement(createOrderButton).getText();
    }

    @Step("Проверка текста в поле соберите бургер")
    public void isMakeBurgerDisplayed() {
        driver.findElement(makeBurger).isDisplayed();
    }

    public boolean bunButtonWorking() {
        return driver.findElement(bunButtonWorking).isEnabled();
    }
    public boolean saucesButtonWorking() {
        return driver.findElement(saucesButtonWorking).isEnabled();
    }
    public boolean fillingsButtonWorking() {
        return driver.findElement(fillingsButtonWorking).isEnabled();
    }
}
