package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.object.AccountPage;
import praktikum.page.object.HomePage;
import praktikum.page.object.LoginPage;

import java.time.Duration;
import java.util.UUID;

import static praktikum.WebDrivers.getDriver;

public class AccountTest {
    private WebDriver driver;
    private User user;
    private UserApi userApi;
    private HomePage mainPage;

    @Before
    public void setUp() {
        driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        userApi = new UserApi();

        String email = UUID.randomUUID() + "@yandex.ru";
        String password = String.valueOf(UUID.randomUUID());
        String name = String.valueOf(UUID.randomUUID());

        user = new User(email, password, name);
        userApi.makeUser(user);

        mainPage = new HomePage(driver);
        mainPage.openHomePage();
    }

    @After
    public void tearDown() {
        String token = userApi.loginUser(user)
                .extract().body().path("accessToken");
        user.setAccessToken(token);

        if (user.getAccessToken() != null) {
            userApi.deleteUser(user);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Проверь переход по клику на «Личный кабинет»")
    public void account() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        mainPage.clickAccountButton();

        AccountPage personalAccPage = new AccountPage(driver);
        personalAccPage.isProfileDisplayed();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор ")
    public void constructorFromAccount() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        mainPage.clickAccountButton();

        AccountPage personalAccPage = new AccountPage(driver);
        personalAccPage.clickConstructorButton();

        mainPage.isMakeBurgerDisplayed();
    }

    @Test
    @DisplayName("Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers")
    public void constructorFromClickingLogo() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        mainPage.clickAccountButton();

        AccountPage personalAccPage = new AccountPage(driver);
        personalAccPage.clickLogo();

        mainPage.isMakeBurgerDisplayed();
    }

    @Test
    @DisplayName("Проверь выход по кнопке «Выйти» в личном кабинете")
    public void exit() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        mainPage.clickAccountButton();

        AccountPage personalAccPage = new AccountPage(driver);
        personalAccPage.clickExitButton();

        loginPage.isHeaderEntranceDisplayed();
    }
}
