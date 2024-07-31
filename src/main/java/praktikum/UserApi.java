package praktikum;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserApi extends SiteURL {
    @Step("Создание пользователя")
    public void makeUser(User user) {
        RestAssured.given()
                .spec(getRequestSpec())
                .body(user)
                .post(REGISTER)
                .then();
    }

    @Step("Вход авторизованного пользователя")
    public ValidatableResponse loginUser(User user) {
        return RestAssured.given()
                .spec(getRequestSpec())
                .body(user)
                .post(LOGIN)
                .then();
    }

    @Step("Удаление авторизованного пользователя")
    public void deleteUser(User user) {
        given().spec(getRequestSpec())
                .header("accessToken", user.getAccessToken())
                .when()
                .delete(USER)
                .then();
    }
}
