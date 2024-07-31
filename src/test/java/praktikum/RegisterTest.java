package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.object.HomePage;
import praktikum.page.object.LoginPage;
import praktikum.page.object.RegisterPage;

import java.time.Duration;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static praktikum.WebDrivers.getDriver;

public class RegisterTest {
    private WebDriver driver;
    private User user;
    private UserApi userApi;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        String email = UUID.randomUUID() + "@yandex.ru";
        String password = String.valueOf(UUID.randomUUID());
        String name = String.valueOf(UUID.randomUUID());

        user = new User(email, password, name);
        userApi = new UserApi();

        homePage = new HomePage(driver);
        homePage.openHomePage();
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
    @DisplayName("Регистрация")
    public void register() {
        homePage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();

        RegisterPage signUpPage = new RegisterPage(driver);
        signUpPage.register(user);

        loginPage.login(user);

        String actualTextOrderButton = homePage.getMakeOrderButtonText();
        String expectedTextOrderButton = "Оформить заказ";
        assertEquals(expectedTextOrderButton, actualTextOrderButton);
    }

    @Test
    @DisplayName("Попытка регистрации с неправильным паролем")
    public void registerWithWrongPassword() {
        homePage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();

        RegisterPage signUpPage = new RegisterPage(driver);
        signUpPage.registerWithWrongPassword(user);

        String actualText = signUpPage.getWrongPasswordText();
        String expectedText = "Некорректный пароль";
        assertEquals(expectedText, actualText);
    }
}
