package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        Assert.assertTrue(homePage.saucesButtonWorking());
    }

    @Test
    @DisplayName("Открытие меню булок")
    public void buns() {
        Assert.assertTrue(homePage.bunButtonWorking());
    }

    @Test
    @DisplayName("Открытие меню начинок")
    public void fillings () {
        homePage.clickFillingButton();
        Assert.assertTrue(homePage.fillingsButtonWorking());
    }
}
