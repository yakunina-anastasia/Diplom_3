package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.object.ChangePasswordPage;
import praktikum.page.object.HomePage;
import praktikum.page.object.LoginPage;
import praktikum.page.object.RegisterPage;

import java.time.Duration;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static praktikum.WebDrivers.getDriver;

public class LoginTest {
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
    @DisplayName("Вход по кнопке Войти в аккаунт на главной")
    public void login() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        String actualTextOrderButton = mainPage.getMakeOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    public void accountFromAccountButton() {
        mainPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);

        String actualTextOrderButton = mainPage.getMakeOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegister() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();

        RegisterPage signUpPage = new RegisterPage(driver);
        signUpPage.clickLoginButtonSignUpPage();

        loginPage.login(user);

        String actualTextOrderButton = mainPage.getMakeOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Вход после смены пароля")
    public void loginFromChangingPassword() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickResetPassButton();

        ChangePasswordPage resetPasswordPage = new ChangePasswordPage(driver);
        resetPasswordPage.clickLoginButtonAfterReseting();

        loginPage.login(user);

        String actualTextOrderButton = mainPage.getMakeOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }
}
