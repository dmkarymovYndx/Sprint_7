package courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.constants.CourierTestsData.*;
import static ru.yandex.praktikum.constants.Endpoints.*;

@RunWith(Parameterized.class)
public class CourierLoginIncorrectFieldsTests extends CourierActions{

    private final String login;
    private final String password;

    public CourierLoginIncorrectFieldsTests(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {LOGIN_INCORRECT, PASSWORD},
                {LOGIN, PASSWORD_INCORRECT},
                {LOGIN_INCORRECT, PASSWORD_INCORRECT}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }

    @Test
    @DisplayName("Check if Login Courier with incorrect login or password returns 404 status code")
    public void courierLoginWithIncorrectDataReturns404() {
        createCourier(LOGIN, PASSWORD, NAME);
        Response loginResponse = loginCourier(login, password);
        int statusCode = loginResponse.getStatusCode();
        assertEquals("Ошибка: неверный статус-код", 404, statusCode);
    }

    @After
    public void cleanUp() {
        deleteTestCourier(LOGIN, PASSWORD);
    }

}
