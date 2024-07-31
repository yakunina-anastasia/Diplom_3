package praktikum;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SiteURL {
    public static final String HOME_PAGE = "https://stellarburgers.nomoreparties.site";
    protected static final String REGISTER = "https://stellarburgers.nomoreparties.site/api/auth/register";
    protected static final String LOGIN = "https://stellarburgers.nomoreparties.site/api/auth/login";
    protected static final String USER = "https://stellarburgers.nomoreparties.site/api/auth/user";


    protected RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(HOME_PAGE)
                .build();
    }
}
