package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.object.HomePage;

import java.time.Duration;
import static praktikum.WebDrivers.getDriver;

public class ConstructorTest {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        homePage = new HomePage(driver);
        homePage.openHomePage();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Открытие меню соусов")
    public void sauce() {
        homePage.clickSauceButton();
        Assert.assertEquals("Соусы", homePage.getTextOfClass());
    }

    @Test
    @DisplayName("Открытие меню булок")
    public void buns() {
        homePage.clickFillingButton();
        homePage.clickBunButton();
        Assert.assertEquals("Булки", homePage.getTextOfClass());
    }

    @Test
    @DisplayName("Открытие меню начинок")
    public void fillings () {
        homePage.clickFillingButton();
        Assert.assertEquals("Начинки", homePage.getTextOfClass());
    }
}
